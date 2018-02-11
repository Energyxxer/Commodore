package com.energyxxer.commodore.commands.teleport;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.commands.teleport.destination.TeleportDestination;
import com.energyxxer.commodore.commands.teleport.facing.TeleportFacing;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TeleportCommand implements Command {
    private Entity victim;
    private TeleportDestination destination;
    private TeleportFacing facing;

    public TeleportCommand(Entity victim, TeleportDestination destination) {
        this(victim, destination, null);
    }

    public TeleportCommand(Entity victim, TeleportDestination destination, TeleportFacing facing) {
        this.victim = victim;
        this.destination = destination;
        this.facing = facing;
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        ArrayList<ScoreboardAccess> accesses = new ArrayList<>(destination.getScoreboardAccesses());
        if(facing != null) accesses.addAll(facing.getScoreboardAccesses());
        return accesses;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        ArrayList<CommandEmbeddable> embeddables = new ArrayList<>();
        embeddables.add(victim);
        embeddables.addAll(destination.getEmbeddables());
        if(facing != null) embeddables.addAll(facing.getEmbeddables());

        String str = "tp \be# " + destination.getRaw();
        if(facing != null) str += " " + facing.getRaw();

        for(int i = 0; i < embeddables.size(); i++) {
            str = str.replaceFirst("\be#","\be" + i);
        }

        return new CommandResolution(execContext, str, embeddables);
    }
}
