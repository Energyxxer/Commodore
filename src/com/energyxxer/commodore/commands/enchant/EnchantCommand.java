package com.energyxxer.commodore.commands.enchant;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.defaults.EnchantmentType;
import org.jetbrains.annotations.NotNull;

public class EnchantCommand implements Command {
    private final Entity entity;
    private final EnchantmentType enchantment;
    private final int level;

    public EnchantCommand(Entity entity, EnchantmentType enchantment) {
        this(entity, enchantment, 1);
    }

    public EnchantCommand(Entity entity, EnchantmentType enchantment, int level) {
        this.entity = entity;
        this.enchantment = enchantment;
        this.level = level;

        if(level < 0) throw new IllegalArgumentException("Level must not be less than 0, found " + level);
        String maxLevel = enchantment.getProperty("max_level");
        if(maxLevel != null && level > Integer.parseInt(maxLevel)) throw new IllegalArgumentException(level + " is higher than the maximum level of " + maxLevel + " supported by enchantment " + enchantment);
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "enchant \be0 " + enchantment + " " + level, entity);
    }
}
