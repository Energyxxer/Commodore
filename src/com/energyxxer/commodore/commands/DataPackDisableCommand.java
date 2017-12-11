package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;

public class DataPackDisableCommand extends DataPackCommand {

    private String pack;

    public DataPackDisableCommand(String pack) {
        this.pack = pack;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "datapack disable " + pack;
    }
}
