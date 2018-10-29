package com.energyxxer.commodore.util;

public class BaseSelectorProperty<T> {

    public enum EnforcementType {
        ENFORCED,
        DEFAULT
    }
    private final T value;
    private final EnforcementType enforcementType;

    public BaseSelectorProperty(T value, EnforcementType enforcementType) {
        this.value = value;
        this.enforcementType = enforcementType;
    }

    public T getValue() {
        return value;
    }

    public boolean isEnforced() {
        return enforcementType == EnforcementType.ENFORCED;
    }
}
