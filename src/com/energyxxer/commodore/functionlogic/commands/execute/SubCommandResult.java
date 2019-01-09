package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class SubCommandResult {
    @NotNull
    private final ExecutionContext execContext;
    @NotNull
    private final String raw;

    public SubCommandResult(@NotNull ExecutionContext execContext, @NotNull String raw) {
        this.execContext = execContext;
        this.raw = raw;
    }

    @NotNull
    public ExecutionContext getExecContext() {
        return execContext;
    }

    @NotNull
    public String getRaw() {
        return raw;
    }
}
