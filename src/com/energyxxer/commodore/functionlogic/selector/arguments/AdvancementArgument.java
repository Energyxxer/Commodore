package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.selector.arguments.advancement.AdvancementArgumentEntry;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class AdvancementArgument implements ComplexSelectorArgument {

    @NotNull
    public final List<@NotNull AdvancementArgumentEntry> entries = new ArrayList<>();

    public AdvancementArgument() {
    }

    public void addEntry(@NotNull AdvancementArgumentEntry entry) {
        entries.add(entry);
    }

    public void addEntries(@NotNull AdvancementArgumentEntry... entries) {
        addEntries(Arrays.asList(entries));
    }

    private void addEntries(@NotNull Collection<@NotNull AdvancementArgumentEntry> entries) {
        this.entries.addAll(entries);
    }

    @NotNull
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
        return true;
    }

    @NotNull
    @Override
    public AdvancementArgument clone() {
        AdvancementArgument copy = new AdvancementArgument();
        this.entries.forEach(e -> copy.addEntry(e.clone()));
        return copy;
    }

    @NotNull
    @Override
    public String getKey() {
        return "advancements";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    @Override
    public ComplexSelectorArgument merge(ComplexSelectorArgument overwriting) {
        AdvancementArgument newArg = this.clone();
        newArg.entries.addAll(((AdvancementArgument) overwriting).entries);
        return newArg;
    }

    @Override
    public String toString() {
        return getArgumentString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvancementArgument that = (AdvancementArgument) o;
        return entries.equals(that.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries);
    }
}
