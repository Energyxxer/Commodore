package com.energyxxer.commodore.selector.advancement;

public class AdvancementCompletionEntry implements AdvancementArgumentEntry {

    private String advancementName;
    private boolean value;

    public AdvancementCompletionEntry(String advancementName, boolean value) {
        this.advancementName = advancementName;
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return advancementName + "=" + value;
    }
}
