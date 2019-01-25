package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class NBTTextComponent extends TextComponent {
    @NotNull
    private final NBTPath path;
    @NotNull
    private final Object toPrint;
    private final boolean interpret;

    public NBTTextComponent(NBTPath path, CoordinateSet block) {
        this(path, block, false);
    }

    public NBTTextComponent(NBTPath path, CoordinateSet block, boolean interpret) {
        this(path, (Object) block, interpret);
    }

    public NBTTextComponent(NBTPath path, Entity entity) {
        this(path, entity, false);
    }

    public NBTTextComponent(NBTPath path, Entity entity, boolean interpret) {
        this(path, (Object) entity, interpret);
    }

    private NBTTextComponent(@NotNull NBTPath path, @NotNull Object toPrint, boolean interpret) {
        this.path = path;
        this.toPrint = toPrint;
        this.interpret = interpret;
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextStyle parentStyle) {
        String baseProperties = this.getBaseProperties(parentStyle);

        String extra = (toPrint instanceof Entity ? "\"entity\"" : "\"block\"") + ":\"" + CommandUtils.escape(toPrint.toString()) + "\"";
        if(interpret) extra += ",\"interpret\":true";
        return "{\"nbt\":\"" + CommandUtils.escape(path.toString()) + "\"," +
                extra +
                (baseProperties != null ? "," + baseProperties : "") +
                '}';
    }
}
