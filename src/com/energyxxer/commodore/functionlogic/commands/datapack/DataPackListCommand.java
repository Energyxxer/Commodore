package com.energyxxer.commodore.functionlogic.commands.datapack;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class DataPackListCommand extends DataPackCommand {
    public enum Filter {
        AVAILABLE, ENABLED
    }

    private final Filter filter;

    public DataPackListCommand() {
        this(null);
    }

    public DataPackListCommand(Filter filter) {
        this.filter = filter;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "datapack list" + ((filter != null) ? " " + filter.toString().toLowerCase() : ""));
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
