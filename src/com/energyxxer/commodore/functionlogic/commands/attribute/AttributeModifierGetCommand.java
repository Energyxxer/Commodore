package com.energyxxer.commodore.functionlogic.commands.attribute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AttributeModifierGetCommand extends AttributeCommand {
    protected UUID uuid;
    protected double scale;

    public AttributeModifierGetCommand(@NotNull Entity target, @NotNull Type type, @NotNull UUID uuid) {
        this(target, type, uuid, 1);
    }

    public AttributeModifierGetCommand(@NotNull Entity target, @NotNull Type type, @NotNull UUID uuid, double scale) {
        super(target, type);
        this.uuid = uuid;
        this.scale = scale;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getStart() + "modifier value get " + uuid + (scale == 1 ? "" : " " + CommandUtils.numberToPlainString(scale)));
    }
}
