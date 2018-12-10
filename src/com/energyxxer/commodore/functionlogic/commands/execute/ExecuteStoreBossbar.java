package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertBossbar;

public class ExecuteStoreBossbar extends ExecuteStore {
    @NotNull
    private final Type bossbar;
    @NotNull
    private final BossbarVariable variable;

    public enum BossbarVariable {
        MAX, VALUE
    }

    public ExecuteStoreBossbar(@NotNull Type bossbar, @NotNull BossbarVariable variable) {
        this(StoreValue.DEFAULT, bossbar, variable);
    }

    public ExecuteStoreBossbar(@NotNull StoreValue storeValue, @NotNull Type bossbar, @NotNull BossbarVariable variable) {
        super(storeValue);
        this.bossbar = bossbar;
        this.variable = variable;

        assertBossbar(bossbar);
    }

    @NotNull
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
