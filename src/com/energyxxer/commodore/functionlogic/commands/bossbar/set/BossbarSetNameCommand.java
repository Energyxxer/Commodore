package com.energyxxer.commodore.functionlogic.commands.bossbar.set;

import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

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
        Collection<CommandEmbeddable> embeddables = name.getEmbeddables();
        for(int i = 0; i < embeddables.size(); i++) {
            displayName = displayName.replace("\be#","\be" + i);
        }
        return new CommandResolution(execContext, base + displayName, embeddables);
    }
}
