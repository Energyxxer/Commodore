package com.energyxxer.commodore.util;

import java.util.Objects;

public class Negatable<T> {
    public boolean negated;
    public T value;

    public Negatable(T value, boolean negated) {
        this.negated = negated;
        this.value = value;
    }

    @Override
    public String toString() {
        return (negated ? "!" : "") + value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Negatable<?> negatable = (Negatable<?>) o;
        return negated == negatable.negated &&
                Objects.equals(value, negatable.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(negated, value);
    }
}
