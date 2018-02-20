package com.energyxxer.commodore.inspection;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.commands.execute.SubCommandResult;
import com.energyxxer.commodore.entity.Entity;

import java.util.*;

public class CommandResolution {
    private final ExecutionContext execContext;
    private final String raw;
    private final ArrayList<CommandEmbeddable> embeddables = new ArrayList<>();

    public CommandResolution(ExecutionContext execContext, String raw, Collection<CommandEmbeddable> embeddables) {
        this.execContext = execContext;
        this.raw = raw;
        if(embeddables != null) this.embeddables.addAll(embeddables);
    }

    public CommandResolution(ExecutionContext execContext, String raw, CommandEmbeddable... embeddables) {
        this(execContext, raw, Arrays.asList(embeddables));
    }

    /**
     * Really weak code; prone to infinite loops if some knobhead makes two self-referencing entity/modifier pairs
     **/
    private String resolveModifiers(ArrayList<ExecuteModifier> modifiers) {
        ArrayList<ExecuteModifier> alreadyResolved = new ArrayList<>();

        HashMap<ExecuteModifier, String> resolved = new HashMap<>();

        for(int i = 0; i < modifiers.size(); i++) {
            ExecuteModifier modifier = modifiers.get(i);
            if(!alreadyResolved.contains(modifier)) {
                ExecutionContext subExecContext = new ExecutionContext(execContext.getOriginalSender(), modifiers.subList(0, i));
                SubCommandResult result = modifier.getSubCommand(subExecContext);
                String raw = result.getRaw();
                for(int j = 0; j < result.getEmbeddables().size(); j++) {
                    CommandEmbeddable embeddable = result.getEmbeddables().get(j);
                    if(embeddable instanceof Entity) {
                        embeddable = ((Entity) embeddable).resolveFor(subExecContext);
                    }
                    if(embeddable instanceof EntityResolution) {
                        modifiers.addAll(i, ((EntityResolution) embeddable).getModifiers());
                        i -= ((EntityResolution) embeddable).getModifiers().size() + 1;
                    }
                    //TODO: This probably needs to re-create the subExecContext to accurately resolve the next Entity
                    // I'm too sleepy to attempt that now
                    raw = embed(raw, "\be" + j, embeddable.toString());
                }
                alreadyResolved.add(modifier);
                resolved.put(modifier, raw);
            }
        }

        StringBuilder sb = new StringBuilder();

        Iterator<ExecuteModifier> it = modifiers.iterator();
        while(it.hasNext()) {
            sb.append(resolved.get(it.next()));
            if(it.hasNext()) sb.append(' ');
        }

        return sb.toString();
    }

    private String embed(String raw, String pattern, String replacement) {
        int fromIndex = 0;
        int index;
        while((index = raw.indexOf(pattern, fromIndex)) >= 0) {
            int escapes = 0;
            for(int i = index + pattern.length(); i < raw.length(); i++) {
                if(raw.charAt(i) == '\r') escapes++;
                else break;
            }
            String escapedReplacement = replacement;
            for(int i = 0; i < escapes; i++) {
                escapedReplacement = CommandUtils.escape(escapedReplacement);
            }

            raw = raw.substring(0, index) + escapedReplacement + raw.substring(index + pattern.length() + escapes);
            fromIndex += escapedReplacement.length();
        }
        return raw;
    }

    public String construct() {
        ArrayList<ExecuteModifier> modifiers = new ArrayList<>(this.execContext.getModifiers());

        for(int i = 0; i < embeddables.size(); i++) {
            CommandEmbeddable embeddable = embeddables.get(i);
            if(embeddable instanceof Entity) {
                EntityResolution resolution = ((Entity) embeddable).resolveFor(new ExecutionContext(execContext.getOriginalSender(), modifiers));

                modifiers.addAll(resolution.getModifiers());
                embeddables.set(i, resolution);
            }
        }

        String chainedCommand = raw;

        for(int i = 0; i < embeddables.size(); i++) {
            chainedCommand = embed(chainedCommand, "\be" + i, embeddables.get(i).toString());
        }

        StringBuilder sb = new StringBuilder();

        if(!modifiers.isEmpty()) {
            sb.append("execute ");
            sb.append(resolveModifiers(modifiers));
            sb.append(" run ");
        }
        sb.append(chainedCommand);

        return sb.toString();
    }
}
