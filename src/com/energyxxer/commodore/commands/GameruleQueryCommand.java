package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class GameruleQueryCommand extends GameruleCommand {

    private String gamerule;

    public GameruleQueryCommand(String gamerule) {
        this.gamerule = gamerule;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "gamerule " + gamerule;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "gamerule " + gamerule);
    }
}
