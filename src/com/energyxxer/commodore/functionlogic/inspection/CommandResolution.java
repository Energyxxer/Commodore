package com.energyxxer.commodore.functionlogic.inspection;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.functionlogic.commands.execute.SubCommandResult;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CommandResolution {
    private final @NotNull ArrayList<@NotNull CommandResolutionLine> lines = new ArrayList<>();

    public CommandResolution(@NotNull ExecutionContext execContext, @NotNull String raw, @NotNull Collection<@NotNull CommandEmbeddable> embeddables) {
        lines.add(new CommandResolutionLine(execContext, raw, embeddables));
    }

    public CommandResolution(@NotNull ExecutionContext execContext, @NotNull String raw, @NotNull CommandEmbeddable... embeddables) {
        this(execContext, raw, Arrays.asList(embeddables));
    }

    public CommandResolution(@NotNull CommandResolutionLine... lines) {
        this(Arrays.asList(lines));
    }

    public CommandResolution(@NotNull Collection<@NotNull CommandResolutionLine> lines) {
        this.lines.addAll(lines);
    }

    /*
     * Really weak code; prone to infinite loops if some knobhead makes two self-referencing entity/modifier pairs
     **/
    static String resolveModifiers(@NotNull ExecutionContext execContext, @NotNull ArrayList<@NotNull ExecuteModifier> modifiers) {
        ArrayList<ExecuteModifier> alreadyResolved = new ArrayList<>();

        HashMap<ExecuteModifier, String> resolved = new HashMap<>();

        for(int i = 0; i < modifiers.size(); i++) {
            ExecuteModifier modifier = modifiers.get(i);
            if(!alreadyResolved.contains(modifier)) {
                ExecutionContext subExecContext = new ExecutionContext(execContext.getOriginalSender(), modifiers.subList(0, i));
                SubCommandResult result = modifier.getSubCommand(subExecContext);
                String raw = result.getRaw();
                int offset = 0;
                for(int j = 0; j < result.getEmbeddables().size(); j++) {
                    CommandEmbeddable embeddable = result.getEmbeddables().get(j);
                    CommandEmbeddableResolution resolution = embeddable.resolveEmbed(subExecContext);
                    modifiers.addAll(i + offset, resolution.getNewModifiers());
                    offset += resolution.getNewModifiers().size();
                    raw = embed(raw, "\be" + j, resolution.getEmbedString());
                    //TODO: This probably needs to re-create the subExecContext to accurately resolve the next Entity
                    // I'm too sleepy to attempt that now
                }
                if(offset != 0) i--;
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

    static String embed(@NotNull String raw, @NotNull String pattern, @NotNull String replacement) {
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

    @NotNull
    public String construct() {
        StringBuilder sb = new StringBuilder();

        for(CommandResolutionLine line : lines) {
            sb.append(line.construct());
            sb.append('\n');
        }

        if(sb.length() > 0) sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

    @NotNull
    public ArrayList<CommandResolutionLine> getLines() {
        return lines;
    }
}
