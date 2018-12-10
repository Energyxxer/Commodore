package com.energyxxer.commodore.functionlogic.selector.arguments.advancement;

import org.jetbrains.annotations.NotNull;

public class AdvancementCriterionEntry {

    @NotNull
    private final String criterionName;
    private final boolean value;

    public AdvancementCriterionEntry(@NotNull String criterionName, boolean value) {
        this.criterionName = criterionName;
        this.value = value;
    }

    @NotNull
    public String getArgumentString() {
        return criterionName + "=" + value;
    }

    @Override
    public AdvancementCriterionEntry clone() {
        return new AdvancementCriterionEntry(criterionName, value);
    }
}
