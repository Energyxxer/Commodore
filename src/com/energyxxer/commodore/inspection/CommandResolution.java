package com.energyxxer.commodore.inspection;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.commands.execute.SubCommandResult;
import com.energyxxer.commodore.entity.Entity;

import java.util.*;

public class CommandResolution {
    private final ArrayList<CommandResolutionLine> lines = new ArrayList<>();

    public CommandResolution(ExecutionContext execContext, String raw, Collection<CommandEmbeddable> embeddables) {
        lines.add(new CommandResolutionLine(execContext, raw, embeddables));
    }

    public CommandResolution(ExecutionContext execContext, String raw, CommandEmbeddable... embeddables) {
        this(execContext, raw, Arrays.asList(embeddables));
    }

    public CommandResolution(CommandResolutionLine... lines) {
        this(Arrays.asList(lines));
    }

    public CommandResolution(Collection<CommandResolutionLine> lines) {
        this.lines.addAll(lines);
    }

    /*
     * Really weak code; prone to infinite loops if some knobhead makes two self-referencing entity/modifier pairs
     **/
    static String resolveModifiers(ExecutionContext execContext, ArrayList<ExecuteModifier> modifiers) {
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

    static String embed(String raw, String pattern, String replacement) {
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
        StringBuilder sb = new StringBuilder();

        for(CommandResolutionLine line : lines) {
            sb.append(line.construct());
            sb.append('\n');
        }

        if(sb.length() > 0) sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

    public ArrayList<CommandResolutionLine> getLines() {
        return lines;
    }
}
