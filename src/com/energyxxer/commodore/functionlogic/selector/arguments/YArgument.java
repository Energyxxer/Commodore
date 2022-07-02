
package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class YArgument implements SelectorArgument {

    public final double value;

    public YArgument(double value) {
        this.value = value;
        assertFinite(value, "value");
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "y=" + CommandUtils.numberToPlainString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public YArgument clone() {
        return new YArgument(value);
    }

    @NotNull
    @Override
    public String getKey() {
        return "y";
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
        YArgument yArgument = (YArgument) o;
        return Double.compare(yArgument.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
