package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.PredicateReference;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class ExecuteConditionPredicate extends ExecuteCondition {

    @NotNull
    private Type predicate;

    public ExecuteConditionPredicate(@NotNull ConditionType flowController, @NotNull Type predicate) {
        super(flowController);
        this.predicate = predicate;

        assertType(predicate, PredicateReference.CATEGORY);
    }

    @Override
    public @NotNull SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "predicate " + predicate.toSafeString());
    }

    @Override
    public boolean isIdempotent() {
        return false;
    }

    @Override
    public boolean isSignificant() {
        return false;
    }

    @Override
    public boolean isAbsolute() {
        return false;
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("modifier.condition_predicate");
    }
}
