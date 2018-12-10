package com.energyxxer.commodore.functionlogic.commands.effect;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.util.StatusEffect;
import org.jetbrains.annotations.NotNull;

public class EffectGiveCommand extends EffectCommand {
    @NotNull
    private final StatusEffect effect;

    public EffectGiveCommand(@NotNull Entity entity, @NotNull StatusEffect effect) {
        super(entity);
        this.effect = effect;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "effect give \be0 " + effect, entity);
    }
}
