package com.energyxxer.commodore.functionlogic.commands.datapack;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DataPackListCommand extends DataPackCommand {
    public enum Filter {
        AVAILABLE, ENABLED
    }

    @Nullable
    private final Filter filter;

    public DataPackListCommand() {
        this(null);
    }

    public DataPackListCommand(@Nullable Filter filter) {
        this.filter = filter;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "datapack list" + ((filter != null) ? " " + filter.toString().toLowerCase() : ""));
    }

}
