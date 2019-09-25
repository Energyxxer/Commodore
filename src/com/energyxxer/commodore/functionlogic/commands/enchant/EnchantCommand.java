package com.energyxxer.commodore.functionlogic.commands.enchant;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertEnchantment;

public class EnchantCommand implements Command {
    @NotNull
    private final Entity entity;
    @NotNull
    private final Type enchantment;
    private final int level;

    public EnchantCommand(@NotNull Entity entity, @NotNull Type enchantment) {
        this(entity, enchantment, 1);
    }

    public EnchantCommand(@NotNull Entity entity, @NotNull Type enchantment, int level) {
        this.entity = entity;
        this.enchantment = enchantment;
        this.level = level;

        assertEnchantment(enchantment);

        if(level < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Level must not be less than 0, found " + level, level);
        String maxLevel = enchantment.getProperty("max_level");
        if(maxLevel != null && level > Integer.parseInt(maxLevel)) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, level + " is higher than the maximum level of " + maxLevel + " supported by enchantment " + enchantment, level);
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "enchant " + entity + " " + enchantment + " " + level);
    }

    @Override
    public void assertAvailable() {
        entity.assertAvailable();
    }
}
