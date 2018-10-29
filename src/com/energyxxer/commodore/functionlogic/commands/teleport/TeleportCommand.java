package com.energyxxer.commodore.functionlogic.commands.teleport;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.commands.teleport.destination.TeleportDestination;
import com.energyxxer.commodore.functionlogic.commands.teleport.facing.TeleportFacing;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TeleportCommand implements Command {
    private Entity victim;
    private TeleportDestination destination;
    private TeleportFacing facing;

    public TeleportCommand(TeleportDestination destination) {
        this(null, destination);
    }

    public TeleportCommand(Entity victim, TeleportDestination destination) {
        this(victim, destination, null);
    }

    public TeleportCommand(TeleportDestination destination, TeleportFacing facing) {
        this(null, destination, facing);
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
        if(victim != null) embeddables.add(victim);
        embeddables.addAll(destination.getEmbeddables());
        if(facing != null) embeddables.addAll(facing.getEmbeddables());

        StringBuilder sb = new StringBuilder("tp ");
        if(victim != null) sb.append("\be# ");
        sb.append(destination.getRaw());
        if(facing != null) {
            sb.append(' ');
            sb.append(facing.getRaw());
        }

        String str = sb.toString();

        for(int i = 0; i < embeddables.size(); i++) {
            str = str.replaceFirst("\be#","\be" + i);
        }

        return new CommandResolution(execContext, str, embeddables);
    }
}
