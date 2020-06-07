package com.energyxxer.commodore.functionlogic.commands.attribute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class AttributeGetCommand extends AttributeCommand {
    protected double scale;

    public AttributeGetCommand(@NotNull Entity target, @NotNull Type type) {
        this(target, type, 1);
    }

    public AttributeGetCommand(@NotNull Entity target, @NotNull Type type, double scale) {
        super(target, type);
        this.scale = scale;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getStart() + "get" + ((scale == 1) ? "" : CommandUtils.numberToPlainString(scale)));
    }
}
