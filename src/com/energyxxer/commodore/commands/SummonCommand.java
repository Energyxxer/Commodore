package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.nbt.TagCompound;
import com.energyxxer.commodore.types.EntityType;

public class SummonCommand implements Command {

    private EntityType type;
    private CoordinateSet pos;
    private TagCompound data;

    public SummonCommand(EntityType type) {
        this(type, null);
    }

    public SummonCommand(EntityType type, CoordinateSet pos) {
        this(type, pos, null);
    }

    public SummonCommand(EntityType type, CoordinateSet pos, TagCompound data) {
        this.type = type;
        this.pos = pos;
        this.data = data;

        if(!type.getProperty("spawnable").equals("true")) throw new IllegalArgumentException("Entity '" + type + "' is not a valid spawnable entity");
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "summon " + type + (pos != null ? " " + pos + (data != null ? " " + data.toHeadlessString() : "") : "");
    }
}
