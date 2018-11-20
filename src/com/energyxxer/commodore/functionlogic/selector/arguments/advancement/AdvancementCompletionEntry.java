package com.energyxxer.commodore.functionlogic.selector.arguments.advancement;

public class AdvancementCompletionEntry implements AdvancementArgumentEntry {

    private final String advancementName;
    private final boolean value;

    public AdvancementCompletionEntry(String advancementName, boolean value) {
        this.advancementName = advancementName;
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return advancementName + "=" + value;
    }

    @Override
    public AdvancementCompletionEntry clone() {
        return new AdvancementCompletionEntry(advancementName, value);
    }
}
