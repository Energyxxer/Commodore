package com.energyxxer.commodore.functionlogic.inspection;

import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.functionlogic.commands.execute.SubCommandResult;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CommandResolution {
    private final @NotNull ArrayList<@NotNull CommandResolutionLine> lines = new ArrayList<>();

    public CommandResolution(@NotNull ExecutionContext execContext, @NotNull String raw) {
        lines.add(new CommandResolutionLine(execContext, raw));
    }

    public CommandResolution(@NotNull CommandResolutionLine... lines) {
        this(Arrays.asList(lines));
    }

    public CommandResolution(@NotNull Collection<@NotNull CommandResolutionLine> lines) {
        this.lines.addAll(lines);
    }

    static String resolveModifiers(@NotNull ExecutionContext execContext, @NotNull ArrayList<@NotNull ExecuteModifier> modifiers) {
        ArrayList<ExecuteModifier> alreadyResolved = new ArrayList<>();

        HashMap<ExecuteModifier, String> resolved = new HashMap<>();

        for(int i = 0; i < modifiers.size(); i++) {
            ExecuteModifier modifier = modifiers.get(i);
            if(!alreadyResolved.contains(modifier)) {
                ExecutionContext subExecContext = new ExecutionContext(execContext.getOriginalSender(), modifiers.subList(0, i));
                SubCommandResult result = modifier.getSubCommand(subExecContext);
                String raw = result.getRaw();
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
