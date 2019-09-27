package com.energyxxer.commodore.functionlogic.commands.bossbar.set;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarSetNameCommand extends BossbarSetCommand {
    @NotNull
    private final TextComponent name;

    public BossbarSetNameCommand(@NotNull Type bossbar, @NotNull TextComponent name) {
        super(bossbar);
        this.name = name;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        String base = getBase() + "name ";

        String displayName = name.toString();
        return new CommandResolution(execContext, base + displayName);
    }

    @Override
    public void assertAvailable() {
        super.assertAvailable();
        name.assertAvailable();
    }
}
