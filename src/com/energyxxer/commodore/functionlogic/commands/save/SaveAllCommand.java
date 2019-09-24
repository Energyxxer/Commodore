package com.energyxxer.commodore.functionlogic.commands.save;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class SaveAllCommand implements Command {

    private final boolean flush;

    public SaveAllCommand() {
        this(false);
    }

    public SaveAllCommand(boolean flush) {
        this.flush = flush;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "save-all" + (flush ? " flush" : ""));
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("server_commands");
    }
}
