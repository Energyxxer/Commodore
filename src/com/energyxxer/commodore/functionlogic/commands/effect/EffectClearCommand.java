package com.energyxxer.commodore.functionlogic.commands.effect;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertEffect;

public class EffectClearCommand extends EffectCommand {
    private final Type type;

    public EffectClearCommand(Entity entity) {
        this(entity, null);
    }

    public EffectClearCommand(Entity entity, Type type) {
        super(entity);
        this.type = type;

        if(type != null) assertEffect(type);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "effect clear \be0" + ((type != null) ? " " + type : ""), entity);
    }
}
