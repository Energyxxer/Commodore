package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class DataPackDisableCommand extends DataPackCommand {

    private String pack;

    public DataPackDisableCommand(String pack) {
        this.pack = pack;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "datapack disable " + pack;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "datapack disable " + pack);
    }
}
