package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.PredicateReference;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class PredicateArgument implements SelectorArgument {

    @Nullable
    public final Type predicate;
    public final boolean negated;

    public PredicateArgument(@Nullable Type predicate) {
        this(predicate, false);
    }

    public PredicateArgument(@Nullable Type predicate, boolean negated) {
        this.predicate = predicate;
        this.negated = negated;

        if(predicate != null) assertType(predicate, PredicateReference.CATEGORY);
    }

    @Override
    public @NotNull String getArgumentString() {
        return "predicate=" + (negated ? "!" : "") + (predicate != null ? predicate.toSafeString() : "");
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public @NotNull SelectorArgument clone() {
        return new PredicateArgument(predicate, negated);
    }

    @Override
    public @NotNull String getKey() {
        return "predicate";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("selector.predicate");
    }

    @Override
    public String toString() {
        return getArgumentString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PredicateArgument that = (PredicateArgument) o;
        return negated == that.negated &&
                Objects.equals(predicate, that.predicate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predicate, negated);
    }
}
