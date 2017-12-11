package com.energyxxer.commodore.selector.advancement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdvancementCriterionGroupEntry implements AdvancementArgumentEntry {

    private String advancementName;
    private List<AdvancementCriterionEntry> criteria = new ArrayList<>();

    public AdvancementCriterionGroupEntry(String advancementName) {
        this.advancementName = advancementName;
    }

    public boolean addCriterion(AdvancementCriterionEntry advancementCriterionEntry) {
        return criteria.add(advancementCriterionEntry);
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
}
