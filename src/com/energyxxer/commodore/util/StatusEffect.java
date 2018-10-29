package com.energyxxer.commodore.util;

import com.energyxxer.commodore.types.Type;

import java.util.Objects;

import static com.energyxxer.commodore.util.StatusEffect.ParticleVisibility.HIDDEN;
import static com.energyxxer.commodore.util.StatusEffect.ParticleVisibility.VISIBLE;
import static com.energyxxer.commodore.types.TypeAssert.assertEffect;

/**
 * Represents a status effect that can be applied directly, through NBT or through a potion.
 * */
public class StatusEffect {
    /**
     * The default duration of status effects applied via the <code>effect give</code> command.
     * */
    public static final int DEFAULT_DURATION = 30 * 20;
    /**
     * The default amplifier of status effects applied via the <code>effect give</code> command.
     * */
    public static final int DEFAULT_AMPLIFIER = 0;
    /**
     * The default particle visibility of status effects applied via the <code>effect give</code> command.
     * */
    public static final ParticleVisibility DEFAULT_VISIBILITY = VISIBLE;

    /**
     * Dictates how particles caused by this status effect should be displayed.
     * */
    public enum ParticleVisibility {
        /**
         * Specifies that particles should be fully opaque. Such are the particles from the default potion items.
         * */
        VISIBLE,
        /**
         * Specifies that particles should be translucent. Such are the particles from effects applied by beacons.
         * */
        AMBIENT,
        /**
         * Specifies that the status effect should not produce any particles.
         * */
        HIDDEN
    }

    /**
     * The effect type of this status effect.
     * */
    private Type effect;
    /**
     * The duration of this status effect once it's applied, in ticks.
     * */
    private int duration;
    /**
     * The amplifier of this status effect. That is, the level of the effect minus one.
     * */
    private int amplifier;
    /**
     * The visibility of the particles of this effect when applied.
     * */
    private ParticleVisibility visibility;

    /**
     * Creates a status effect of the given type, with default duration, amplifier and visibility.
     *
     * @param effect The type of effect of this status effect.
     *
     * @throws com.energyxxer.commodore.types.IllegalTypeException If the given type isn't of the category Effect.
     * */
    public StatusEffect(Type effect) {
        this(effect, DEFAULT_DURATION);
    }

    /**
     * Creates a status effect of the given type and duration, with default amplifier and visibility.
     *
     * @param effect The type of effect of this status effect.
     * @param duration The duration of this status effect in ticks.
     *
     * @throws com.energyxxer.commodore.types.IllegalTypeException If the given type isn't of the category Effect.
     * */
    public StatusEffect(Type effect, int duration) {
        this(effect, duration, DEFAULT_AMPLIFIER);
    }

    /**
     * Creates a status effect of the given type, duration and amplifier, with default visibility.
     *
     * @param effect The type of effect of this status effect.
     * @param duration The duration of this status effect in ticks.
     * @param amplifier The amplifier of this status effect; that is, the level of the effect minus one.
     *
     * @throws com.energyxxer.commodore.types.IllegalTypeException If the given type isn't of the category Effect.
     * */
    public StatusEffect(Type effect, int duration, int amplifier) {
        this(effect, duration, amplifier, DEFAULT_VISIBILITY);
    }

    /**
     * Creates a status effect of the given type, duration, amplifier and visibility.
     *
     * @param effect The type of effect of this status effect.
     * @param duration The duration of this status effect in ticks.
     * @param amplifier The amplifier of this status effect; that is, the level of the effect minus one.
     * @param visibility The visibility of particles produced by this status effect.
     *
     * @throws com.energyxxer.commodore.types.IllegalTypeException If the given type isn't of the category Effect.
     * */
    public StatusEffect(Type effect, int duration, int amplifier, ParticleVisibility visibility) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.visibility = visibility;

        assertEffect(effect);
    }

    /**
     * Retrieves this status effect's type.
     *
     * @return The type of this status effect.
     * */
    public Type getEffect() {
        return effect;
    }

    /**
     * Changes this status effect's type.
     *
     * @param effect The new type for this effect.
     * */
    public void setEffect(Type effect) {
        this.effect = effect;
    }

    /**
     * Retrieves this status effect's duration.
     *
     * @return The duration for this status effect, in ticks.
     * */
    public int getDuration() {
        return duration;
    }

    /**
     * Changes this status effect's duration.
     *
     * @param duration The new duration for this status effect, in ticks.
     * */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Retrieves this status effect's amplifier.
     *
     * @return The amplifier for this status effect.
     * */
    public int getAmplifier() {
        return amplifier;
    }

    /**
     * Changes this status effect's amplifier.
     *
     * @param amplifier The new amplifier for this status effect.
     * */
    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    /**
     * Retrieves this status effect's visibility.
     *
     * @return The visibility for this status effect.
     * */
    public ParticleVisibility getVisibility() {
        return visibility;
    }

    /**
     * Changes this status effect's visibility.
     *
     * @param visibility The new visibility for this status effect.
     * */
    public void setVisibility(ParticleVisibility visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return effect.toString() + " " + (duration / 20) + " " + amplifier + ((visibility == HIDDEN) ? " true" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEffect that = (StatusEffect) o;
        return duration == that.duration &&
                amplifier == that.amplifier &&
                Objects.equals(effect, that.effect) &&
                visibility == that.visibility;
    }

    @Override
    public int hashCode() {
        return Objects.hash(effect, duration, amplifier, visibility);
    }
}
