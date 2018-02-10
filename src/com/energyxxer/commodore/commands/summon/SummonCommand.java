package com.energyxxer.commodore.commands.summon;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
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
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "summon " + type + (pos != null ? " " + pos + (data != null ? " " + data.toHeadlessString() : "") : ""));
    }
}
