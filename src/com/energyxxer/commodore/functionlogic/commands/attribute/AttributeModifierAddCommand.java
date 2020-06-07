package com.energyxxer.commodore.functionlogic.commands.attribute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.UUID;

public class AttributeModifierAddCommand extends AttributeCommand {
    public enum Operation {
        ADD, MULTIPLY, MULTIPLY_BASE
    }

    protected UUID uuid;
    protected String name;
    protected double value;
    protected Operation operation;

    public AttributeModifierAddCommand(@NotNull Entity target, @NotNull Type type, @NotNull UUID uuid, @NotNull String name, double value, @NotNull AttributeModifierAddCommand.Operation operation) {
        super(target, type);
        this.uuid = uuid;
        this.name = name;
        this.value = value;
        this.operation = operation;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getStart() + "modifier add " + uuid + " " + CommandUtils.quoteIfNecessary(name) + " " + CommandUtils.numberToPlainString(value) + " " + operation.toString().toLowerCase(Locale.ENGLISH));
    }
}
