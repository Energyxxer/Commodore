package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;

import static com.energyxxer.commodore.types.TypeAssert.assertBossbar;

public class ExecuteStoreBossbar extends ExecuteStore {
    private final Type bossbar;
    private final BossbarVariable variable;

    public enum BossbarVariable {
        MAX, VALUE
    }

    public ExecuteStoreBossbar(Type bossbar, BossbarVariable variable) {
        this(StoreValue.DEFAULT, bossbar, variable);
    }

    public ExecuteStoreBossbar(StoreValue storeValue, Type bossbar, BossbarVariable variable) {
        super(storeValue);
        this.bossbar = bossbar;
        this.variable = variable;

        assertBossbar(bossbar);
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "bossbar " + bossbar + " " + variable.toString().toLowerCase());
    }

    @Override
    public boolean isIdempotent() {
        return true;
    }

    @Override
    public boolean isSignificant() {
        return true;
    }

    @Override
    public boolean isAbsolute() {
        return true;
    }
}
