package com.energyxxer.commodore.commands.bossbar.set;

import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.types.defaults.BossbarReference;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class BossbarSetNameCommand extends BossbarSetCommand {
    private final TextComponent name;

    public BossbarSetNameCommand(BossbarReference reference, TextComponent name) {
        super(reference);
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
