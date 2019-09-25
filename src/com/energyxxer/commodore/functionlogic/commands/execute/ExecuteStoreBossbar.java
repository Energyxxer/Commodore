package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

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
        return new SubCommandResult(execContext, this.getStarter() + "bossbar " + bossbar + " " + variable.toString().toLowerCase(Locale.ENGLISH));
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

    @NotNull
    public Type getBossbar() {
        return bossbar;
    }

    @NotNull
    public BossbarVariable getVariable() {
        return variable;
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("bossbars");
    }
}
