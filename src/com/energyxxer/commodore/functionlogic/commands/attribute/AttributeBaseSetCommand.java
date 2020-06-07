package com.energyxxer.commodore.functionlogic.commands.attribute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class AttributeBaseSetCommand extends AttributeCommand {
    protected double value;

    public AttributeBaseSetCommand(@NotNull Entity target, @NotNull Type type, double value) {
        super(target, type);
        this.value = value;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getStart() + "base set " + CommandUtils.numberToPlainString(value));
    }
}
