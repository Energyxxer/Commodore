package com.energyxxer.commodore.functionlogic.selector.arguments.advancement;

public class AdvancementCriterionEntry {

    private final String criterionName;
    private final boolean value;

    public AdvancementCriterionEntry(String criterionName, boolean value) {
        this.criterionName = criterionName;
        this.value = value;
    }

    public String getArgumentString() {
        return criterionName + "=" + value;
    }

    @Override
    public AdvancementCriterionEntry clone() {
        return new AdvancementCriterionEntry(criterionName, value);
    }
}
