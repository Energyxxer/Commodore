package com.energyxxer.commodore.functionlogic.commands;

import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteCommand;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.module.settings.ModuleSettingsManager;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class RawCommand implements Command {
    @NotNull
    private String command;

    public RawCommand(@NotNull String command) {
        this.command = command;
    }

    @NotNull
    public String getCommand() {
        return command;
    }

    public void setCommand(@NotNull String command) {
        this.command = command;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        if(ModuleSettingsManager.getActive().CONVERT_RAW_OLD_EXECUTES.getValue() && command.startsWith("execute @") && VersionFeatureManager.getBoolean("execute_modifiers", false)) {
            return new CommandResolution(execContext, ExecuteCommand.convert(command));
        }
        return new CommandResolution(execContext, command);
    }
}
