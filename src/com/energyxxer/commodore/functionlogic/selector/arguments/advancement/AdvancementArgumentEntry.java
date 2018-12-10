package com.energyxxer.commodore.functionlogic.selector.arguments.advancement;

import org.jetbrains.annotations.NotNull;

public interface AdvancementArgumentEntry extends Cloneable {
    @NotNull
    String getArgumentString();

    @NotNull
    AdvancementArgumentEntry clone();
}
