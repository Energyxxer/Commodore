package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class HasItemArgument implements ComplexSelectorArgument {

    @NotNull
    public ArrayList<HasItemArgumentEntry> entries = new ArrayList<>();

    public void assertNotEmpty() {
        if(entries.isEmpty())
            throw new CommodoreException(CommodoreException.Source.SELECTOR_ERROR, "hasitem argument cannot be empty!");
    }

    @Override
    public @NotNull String getArgumentString() {
        StringBuilder sb = new StringBuilder("hasitem=");
        if (entries.size() == 1) {
            // no list
            sb.append(entries.get(0).toString());
        } else {
            sb.append("[");
            for (int i = 0; i < entries.size(); i++) {
                sb.append(entries.get(i).toString());
                if (i < entries.size() - 1) sb.append(',');
            }
            sb.append("]");
        }
        return sb.toString();
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public @NotNull HasItemArgument clone() {
        HasItemArgument copy = new HasItemArgument();
        copy.entries = new ArrayList<>(entries.size());
        for (HasItemArgumentEntry entry : entries) {
            copy.entries.add(entry.clone());
        }
        return copy;
    }

    @Override
    public @NotNull String getKey() {
        return "hasitem";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    @Override
    public String toString() {
        return getArgumentString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasItemArgument that = (HasItemArgument) o;
        return Objects.equals(entries, that.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries);
    }

    @Override
    public ComplexSelectorArgument merge(ComplexSelectorArgument overwriting) {
        HasItemArgument newArg = this.clone();
        newArg.entries.addAll(((HasItemArgument) overwriting).entries);
        return newArg;
    }
}