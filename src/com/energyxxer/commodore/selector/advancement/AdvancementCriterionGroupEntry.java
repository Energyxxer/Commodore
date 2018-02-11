package com.energyxxer.commodore.selector.advancement;

import java.util.*;

public class AdvancementCriterionGroupEntry implements AdvancementArgumentEntry {

    private final String advancementName;
    private final List<AdvancementCriterionEntry> criteria = new ArrayList<>();

    public AdvancementCriterionGroupEntry(String advancementName) {
        this.advancementName = advancementName;
    }

    public void addCriterion(AdvancementCriterionEntry criterion) {
        criteria.add(criterion);
    }

    public void addCriteria(AdvancementCriterionEntry... criteria) {
        addCriteria(Arrays.asList(criteria));
    }

    public void addCriteria(Collection<AdvancementCriterionEntry> criteria) {
        this.criteria.addAll(criteria);
    }

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

    @Override
    public AdvancementCriterionGroupEntry clone() {
        AdvancementCriterionGroupEntry copy = new AdvancementCriterionGroupEntry(advancementName);
        this.criteria.forEach(c -> copy.addCriterion(c.clone()));
        return copy;
    }
}
