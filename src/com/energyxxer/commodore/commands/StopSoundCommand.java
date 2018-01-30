package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class StopSoundCommand implements Command {

    private Entity player;
    private PlaySoundCommand.Source source;
    private String sound;

    public StopSoundCommand(Entity player) {
        this(player, null);
    }

    public StopSoundCommand(Entity player, PlaySoundCommand.Source source) {
        this(player, source, null);
    }

    public StopSoundCommand(Entity player, PlaySoundCommand.Source source, String sound) {
        this.player = player;
        this.source = source;
        this.sound = sound;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "stopsound " + player.getSelectorAs(sender) + (source != null ? " " + source.toString().toLowerCase() + (sound != null ? " " + sound : "") : (sound != null ? " * " + sound : ""));
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "stopsound \be0" + (source != null ? " " + source.toString().toLowerCase() + (sound != null ? " " + sound : "") : (sound != null ? " * " + sound : "")), player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
