package com.energyxxer.commodore.functionlogic.commands.attribute;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AttributeModifierRemoveCommand extends AttributeCommand {
    protected UUID uuid;

    public AttributeModifierRemoveCommand(@NotNull Entity target, @NotNull Type type, @NotNull UUID uuid) {
        super(target, type);
        this.uuid = uuid;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getStart() + "modifier remove " + uuid);
    }

}
