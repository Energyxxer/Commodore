package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.PredicateReference;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class PredicateArgument implements SelectorArgument {

    @Nullable
    private final Type predicate;
    private final boolean negated;

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
        return "predicate=" + (negated ? "!" : "") + (predicate != null ? predicate : "");
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
}
