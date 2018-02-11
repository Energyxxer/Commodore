package com.energyxxer.commodore.commands.bossbar;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.types.BossbarReference;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class BossbarCreateCommand extends BossbarCommand {
    private final BossbarReference bossbar;
    private final TextComponent name;

    public BossbarCreateCommand(BossbarReference bossbar) {
        this(bossbar, null);
    }

    public BossbarCreateCommand(BossbarReference bossbar, TextComponent name) {
        this.bossbar = bossbar;
        this.name = name;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        String base = "bossbar create " + bossbar + " ";

        if(name != null) {
            String displayName = name.toString();
            Collection<CommandEmbeddable> embeddables = name.getEmbeddables();
            for(int i = 0; i < embeddables.size(); i++) {
                displayName = displayName.replace("\be#","\be" + i);
            }
            return new CommandResolution(execContext, base + displayName, embeddables);
        }

        return new CommandResolution(execContext, base + "\"" + CommandUtils.escape(bossbar.toString()));
    }
}
