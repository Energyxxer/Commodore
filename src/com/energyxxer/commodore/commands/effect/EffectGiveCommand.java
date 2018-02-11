package com.energyxxer.commodore.commands.effect;

import com.energyxxer.commodore.effect.StatusEffect;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class EffectGiveCommand extends EffectCommand {
    private final StatusEffect effect;

    public EffectGiveCommand(Entity entity, StatusEffect effect) {
        super(entity);
        this.effect = effect;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "effect give \be0 " + effect, entity);
    }
}
