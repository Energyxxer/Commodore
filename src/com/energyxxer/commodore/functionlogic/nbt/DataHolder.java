package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

public interface DataHolder {

    @NotNull
    String resolve();

    void assertSingle();

    void assertAvailable();

    @NotNull
    String getTextComponentKey();

    @NotNull
    String getTextComponentValue();
}
