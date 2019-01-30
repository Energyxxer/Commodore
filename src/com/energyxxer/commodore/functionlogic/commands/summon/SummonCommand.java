package com.energyxxer.commodore.functionlogic.commands.summon;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.energyxxer.commodore.types.TypeAssert.assertEntity;
import static com.energyxxer.commodore.types.TypeAssert.assertStandalone;

public class SummonCommand implements Command {

    @NotNull
    private final Type type;
    @Nullable
    private final CoordinateSet pos;
    @Nullable
    private final TagCompound data;

    public SummonCommand(@NotNull Type type) {
        this(type, null);
    }

    public SummonCommand(@NotNull Type type, @Nullable CoordinateSet pos) {
        this(type, pos, null);
    }

    public SummonCommand(@NotNull Type type, @Nullable CoordinateSet pos, @Nullable TagCompound data) {
        this.type = type;
        this.pos = pos;
        this.data = data;

        assertEntity(type);
        assertStandalone(type);
        if(!type.getProperty("spawnable").equals("true")) throw new CommodoreException(CommodoreException.Source.TYPE_ERROR, "Entity '" + type + "' is not a valid spawnable entity", type);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "summon " + type + (pos != null ? " " + pos + (data != null ? " " + data.toHeadlessString() : "") : ""));
    }
}
