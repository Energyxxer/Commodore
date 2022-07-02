package com.energyxxer.commodore.functionlogic.selector.arguments.advancement;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class AdvancementCriterionGroupEntry implements AdvancementArgumentEntry {

    @NotNull
    private final String advancementName;
    @NotNull
    public final List<@NotNull AdvancementCriterionEntry> criteria = new ArrayList<>();

    public AdvancementCriterionGroupEntry(@NotNull String advancementName) {
        this.advancementName = advancementName;
    }

    public void addCriterion(@NotNull AdvancementCriterionEntry criterion) {
        criteria.add(criterion);
    }

    public void addCriteria(@NotNull AdvancementCriterionEntry... criteria) {
        addCriteria(Arrays.asList(criteria));
    }

    public void addCriteria(@NotNull Collection<@NotNull AdvancementCriterionEntry> criteria) {
        this.criteria.addAll(criteria);
    }

    @NotNull
    @Override
    public String getAdvancementName() {
        return advancementName;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        StringBuilder sb = new StringBuilder(advancementName);
        sb.append('=');
        sb.append('{');

        Iterator<AdvancementCriterionEntry> it = criteria.iterator();

        while(it.hasNext()) {
            sb.append(it.next().getArgumentString());
            if(it.hasNext()) sb.append(',');
        }

        sb.append('}');

        return sb.toString();
    }

    @NotNull
    @Override
    public AdvancementCriterionGroupEntry clone() {
        AdvancementCriterionGroupEntry copy = new AdvancementCriterionGroupEntry(advancementName);
        this.criteria.forEach(c -> copy.addCriterion(c.clone()));
        return copy;
    }
}
