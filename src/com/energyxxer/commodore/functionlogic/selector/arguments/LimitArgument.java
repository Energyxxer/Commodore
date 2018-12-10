package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import org.jetbrains.annotations.NotNull;

public class LimitArgument implements SelectorArgument {
    private int limit;

    public LimitArgument(int limit) {
        if(limit <= 0) throw new IllegalArgumentException("Limit must be greater than zero: " + limit);
        this.limit = limit;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "limit=" + limit;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public LimitArgument clone() {
        return new LimitArgument(limit);
    }

    public int getLimit() {
        return limit;
    }

    @NotNull
    @Override
    public String getKey() {
        return "limit";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    @Override
    public void assertCompatibleWith(@NotNull Selector selector) {
        if(selector.getBase().getLimit().isEnforced()) throw new IllegalArgumentException("Limit is not applicable for the " + selector.getBase() + " selector type");
        SelectorArgument.super.assertCompatibleWith(selector);
    }
}
