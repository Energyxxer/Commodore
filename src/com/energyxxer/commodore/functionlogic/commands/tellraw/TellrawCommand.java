package com.energyxxer.commodore.functionlogic.commands.tellraw;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import com.energyxxer.commodore.textcomponents.TextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TellrawCommand implements Command {
    private final Entity player;
    private final TextComponent message;

    public TellrawCommand(Entity player, TextComponent message) {
        this.player = player;
        this.message = message;

        player.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        String raw = message.toString();
        Collection<CommandEmbeddable> embeddables = message.getEmbeddables();
        for(int i = 1; i <= embeddables.size(); i++) {
            raw = raw.replaceFirst("\be#", "\be" + i);
        }
        ArrayList<CommandEmbeddable> allEmbeddables = new ArrayList<>(embeddables);
        allEmbeddables.add(0, player);
        return new CommandResolution(execContext, "tellraw \be0 " + raw, allEmbeddables);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        ArrayList<ScoreboardAccess> accesses = new ArrayList<>(player.getScoreboardAccesses());
        accesses.addAll(message.getScoreboardAccesses());
        return accesses;
    }
}
