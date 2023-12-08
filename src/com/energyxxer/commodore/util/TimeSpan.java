package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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

        assertFinite(amount, "amount");
    }

    public void assertNonNegative() {
        if(amount < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Time amount must be non-negative", amount);
    }

    public double getTicks() {
        return amount * units.ticksInUnit;
    }

    public double getSeconds() {
        return amount / Units.SECONDS.ticksInUnit * units.ticksInUnit;
    }

    public double getDays() {
        return amount / Units.DAYS.ticksInUnit * units.ticksInUnit;
    }

    public TimeSpan deriveUnit(Units unit) {
        double convertedAmount = this.amount / unit.ticksInUnit * this.units.ticksInUnit;
        return new TimeSpan(convertedAmount, unit);
    }

    @Override
    public String toString() {
        return CommandUtils.numberToPlainString(amount) + units.suffix;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TimeSpan && ((TimeSpan) obj).getTicks() == this.getTicks();
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(this.getTicks());
    }
}
