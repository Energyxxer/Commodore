package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class GameruleSetCommand extends GameruleCommand {

    private String gamerule;
    private String value;

    public GameruleSetCommand(String gamerule, Object value) {
        this.gamerule = gamerule;
        this.value = String.valueOf(value);
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "gamerule " + gamerule + " " + value;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "gamerule " +gamerule + " " + value);
    }
}
