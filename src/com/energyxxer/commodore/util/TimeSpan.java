package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

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
        assertFinite(amount, "amount");
    }

    public double getTicks() {
        return amount * units.ticksInUnit;
    }

    public double getSeconds() {
        return amount * units.ticksInUnit / Units.SECONDS.ticksInUnit;
    }

    public double getDays() {
        return amount * units.ticksInUnit / Units.DAYS.ticksInUnit;
    }

    @Override
    public String toString() {
        return CommandUtils.numberToPlainString(amount) + units.suffix;
    }

}
