package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LimitArgument implements SelectorArgument {
    public final int limit;

    public LimitArgument(int limit) {
        if(limit <= 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Limit must be at least 1: " + limit, limit);
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

    @Override
    public Integer getLimit() {
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
        if(selector.getBase().getLimit().isEnforced()) throw new CommodoreException(CommodoreException.Source.SELECTOR_ERROR, "Limit is not applicable for the " + selector.getBase() + " selector type", selector);
        SelectorArgument.super.assertCompatibleWith(selector);
    }

    @Override
    public String toString() {
        return getArgumentString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LimitArgument that = (LimitArgument) o;
        return limit == that.limit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit);
    }
}
