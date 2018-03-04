package com.energyxxer.commodore.score;

import java.util.Objects;

public class MacroScoreHolder {
    private final String descriptor;

    public MacroScoreHolder() {
        this("");
    }

    public MacroScoreHolder(String descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public String toString() {
        return "$[" + descriptor + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MacroScoreHolder that = (MacroScoreHolder) o;
        return Objects.equals(descriptor, that.descriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptor);
    }
}
