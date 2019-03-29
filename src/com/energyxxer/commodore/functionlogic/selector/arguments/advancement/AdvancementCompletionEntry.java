package com.energyxxer.commodore.functionlogic.selector.arguments.advancement;

import org.jetbrains.annotations.NotNull;

public class AdvancementCompletionEntry implements AdvancementArgumentEntry {

    @NotNull
    private final String advancementName;
    private final boolean value;

    public AdvancementCompletionEntry(@NotNull String advancementName, boolean value) {
        this.advancementName = advancementName;
        this.value = value;
    }

    @NotNull
    @Override
    public String getAdvancementName() {
        return advancementName;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return advancementName + "=" + value;
    }

    @NotNull
    @Override
    public AdvancementCompletionEntry clone() {
        return new AdvancementCompletionEntry(advancementName, value);
    }
}
