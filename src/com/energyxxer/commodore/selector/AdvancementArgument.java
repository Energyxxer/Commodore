package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.selector.advancement.AdvancementArgumentEntry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdvancementArgument implements SelectorArgument {

    private List<AdvancementArgumentEntry> entries = new ArrayList<>();

    public AdvancementArgument() {
    }

    public boolean addEntry(AdvancementArgumentEntry advancementArgumentEntry) {
        return entries.add(advancementArgumentEntry);
    }

    @Override
    public String getArgumentString() {
        StringBuilder sb = new StringBuilder("advancements={");

        Iterator<AdvancementArgumentEntry> it = entries.iterator();

        while (it.hasNext()) {
            sb.append(it.next().getArgumentString());
            if (it.hasNext()) sb.append(',');
        }

        sb.append('}');

        return sb.toString();
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }
}
