package com.energyxxer.commodore.commands.summon;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.nbt.TagCompound;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertEntity;

public class SummonCommand implements Command {

    private final Type type;
    private final CoordinateSet pos;
    private final TagCompound data;

    public SummonCommand(Type type) {
        this(type, null);
    }

    public SummonCommand(Type type, CoordinateSet pos) {
        this(type, pos, null);
    }

    public SummonCommand(Type type, CoordinateSet pos, TagCompound data) {
        this.type = type;
        this.pos = pos;
        this.data = data;

        assertEntity(type);

        if(!type.getProperty("spawnable").equals("true")) throw new IllegalArgumentException("Entity '" + type + "' is not a valid spawnable entity");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "summon " + type + (pos != null ? " " + pos + (data != null ? " " + data.toHeadlessString() : "") : ""));
    }
}
