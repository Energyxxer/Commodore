package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class XArgument implements SelectorArgument {

    public final double value;

    public XArgument(double value) {
        this.value = value;
        assertFinite(value, "value");
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "x=" + CommandUtils.numberToPlainString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public XArgument clone() {
        return new XArgument(value);
    }

    @NotNull
    @Override
    public String getKey() {
        return "x";
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
        XArgument xArgument = (XArgument) o;
        return Double.compare(xArgument.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
