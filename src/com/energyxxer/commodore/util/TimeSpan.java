package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import org.jetbrains.annotations.NotNull;

public class TimeSpan {
    public enum Units {
        TICKS("t", 1), SECONDS("s", 20), DAYS("d", 24000);

        public final String suffix;
        public final int ticksInUnit;

        Units(String suffix, int ticksInUnit) {
            this.suffix = suffix;
            this.ticksInUnit = ticksInUnit;
        }
    }

    public final double amount;
    @NotNull
    public final Units units;

    public TimeSpan(int amount) {
        this(amount, Units.TICKS);
    }

    public TimeSpan(double amount, @NotNull Units units) {
        this.amount = amount;
        this.units = units;

        if(amount < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Time amount must be non-negative", amount);
    }

    public int getTicks() {
        return (int) Math.round(amount * units.ticksInUnit);
    }

    @Override
    public String toString() {
        return CommandUtils.numberToPlainString(amount) + units.suffix;
    }
}
