package com.energyxxer.commodore.selector.advancement;

public class AdvancementCriterionEntry implements AdvancementArgumentEntry {

    private String criterionName;
    private boolean value;

    public AdvancementCriterionEntry(String criterionName, boolean value) {
        this.criterionName = criterionName;
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return criterionName + "=" + value;
    }

    @Override
    public AdvancementCriterionEntry clone() {
        return new AdvancementCriterionEntry(criterionName, value);
    }
}
