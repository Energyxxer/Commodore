package com.energyxxer.commodore.commands.tellraw;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.textcomponents.TextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TellrawCommand implements Command {
    private Entity player;
    private TextComponent message;

    public TellrawCommand(Entity player, TextComponent message) {
        this.player = player;
        this.message = message;

        if(!player.isPlayer()) throw new IllegalArgumentException("Provided entity '" + player + "' includes non-player entities, expected only players");
    }

    @Override
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
