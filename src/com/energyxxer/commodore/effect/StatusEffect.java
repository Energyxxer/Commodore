package com.energyxxer.commodore.effect;

import com.energyxxer.commodore.types.defaults.EffectType;

import java.util.Objects;

import static com.energyxxer.commodore.effect.StatusEffect.ParticleVisibility.HIDDEN;
import static com.energyxxer.commodore.effect.StatusEffect.ParticleVisibility.VISIBLE;

public class StatusEffect {

    public static final int DEFAULT_DURATION = 30 * 20;
    public static final int DEFAULT_AMPLIFIER = 0;
    public static final ParticleVisibility DEFAULT_VISIBILITY = VISIBLE;

    public enum ParticleVisibility {
        VISIBLE, AMBIENT, HIDDEN
    }

    private EffectType effect;
    private int duration; // in ticks
    private int amplifier;
    private ParticleVisibility visibility;

    public StatusEffect(EffectType effect) {
        this(effect, 30 * 20);
    }

    public StatusEffect(EffectType effect, int duration) {
        this(effect, duration, 0);
    }

    public StatusEffect(EffectType effect, int duration, int amplifier) {
        this(effect, duration, amplifier, VISIBLE);
    }

    public StatusEffect(EffectType effect, int duration, int amplifier, ParticleVisibility visibility) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.visibility = visibility;
    }

    public EffectType getEffect() {
        return effect;
    }

    public void setEffect(EffectType effect) {
        this.effect = effect;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    public ParticleVisibility getVisibility() {
        return visibility;
    }

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
