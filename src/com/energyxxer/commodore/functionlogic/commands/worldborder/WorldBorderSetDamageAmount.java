package com.energyxxer.commodore.functionlogic.commands.worldborder;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderSetDamageAmount extends WorldBorderCommand {
    private final double damagePerBlock;

    public WorldBorderSetDamageAmount(double damagePerBlock) {
        this.damagePerBlock = damagePerBlock;
        if(damagePerBlock < 0.0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Damage per block must not be less than 0.0, found " + damagePerBlock, damagePerBlock, "DAMAGE_PER_BLOCK");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder damage amount " + CommandUtils.numberToPlainString(damagePerBlock));
    }
}
