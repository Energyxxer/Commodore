package com.energyxxer.commodore.functionlogic.commands.bossbar;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.energyxxer.commodore.types.TypeAssert.assertBossbar;

public class BossbarAddCommand extends BossbarCommand {
    @NotNull
    private final Type bossbar;
    @Nullable
    private final TextComponent name;

    public BossbarAddCommand(@NotNull Type bossbar) {
        this(bossbar, null);
    }

    public BossbarAddCommand(@NotNull Type bossbar, @Nullable TextComponent name) {
        this.bossbar = bossbar;
        this.name = name;

        assertBossbar(bossbar);
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        String base = "bossbar add " + bossbar.toSafeString() + " ";

        if(name != null) {
            String displayName = name.toString();
            return new CommandResolution(execContext, base + displayName);
        }

        return new CommandResolution(execContext, base + "\"" + CommandUtils.escape(bossbar.toString()) + "\"");
    }

    @Override
    public void assertAvailable() {
        super.assertAvailable();
        if(name != null) name.assertAvailable();
    }
}
