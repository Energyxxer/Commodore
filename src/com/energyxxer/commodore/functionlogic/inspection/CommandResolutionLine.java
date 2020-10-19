package com.energyxxer.commodore.functionlogic.inspection;

import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteModifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandResolutionLine {
    @NotNull
    final String raw;
    @NotNull
    final ExecutionContext execContext;

    public CommandResolutionLine(@NotNull ExecutionContext execContext, @NotNull String raw) {
        this.raw = raw;
        this.execContext = execContext;
    }

    @NotNull
    String construct() {
        ArrayList<ExecuteModifier> modifiers = execContext.getModifiers();

        StringBuilder sb = new StringBuilder();

        String chainedCommand = raw;

        if(!modifiers.isEmpty()) {
            sb.append("execute ");
            sb.append(CommandResolution.resolveModifiers(modifiers));
            if(chainedCommand.length() > 0) {
                sb.append(" run ");
            }
        }
        sb.append(chainedCommand);

        return sb.toString();
    }
}
