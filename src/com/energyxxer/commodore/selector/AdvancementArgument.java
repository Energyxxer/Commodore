package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.selector.advancement.AdvancementArgumentEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AdvancementArgument implements SelectorArgument {

    private List<AdvancementArgumentEntry> entries = new ArrayList<>();

    public AdvancementArgument() {
    }

    public void addEntry(AdvancementArgumentEntry entry) {
        entries.add(entry);
    }

    public void addEntries(AdvancementArgumentEntry... entries) {
        addEntries(Arrays.asList(entries));
    }

    private void addEntries(Collection<AdvancementArgumentEntry> entries) {
        this.entries.addAll(entries);
    }

    @Override
    public String getArgumentString() {
        StringBuilder sb = new StringBuilder("advancements={");

        Iterator<AdvancementArgumentEntry> it = entries.iterator();

        while(it.hasNext()) {
            sb.append(it.next().getArgumentString());
            if(it.hasNext()) sb.append(',');
        }

        sb.append('}');

        return sb.toString();
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public AdvancementArgument clone() {
        AdvancementArgument copy = new AdvancementArgument();
        this.entries.forEach(e -> copy.addEntry(e.clone()));
        return copy;
    }
}
