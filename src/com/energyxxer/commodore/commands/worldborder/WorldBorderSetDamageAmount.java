package com.energyxxer.commodore.commands.worldborder;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderSetDamageAmount extends WorldBorderCommand {
    private final int damagePerBlock;

    public WorldBorderSetDamageAmount(int damagePerBlock) {
        this.damagePerBlock = damagePerBlock;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder damage amount " + damagePerBlock);
    }
}
