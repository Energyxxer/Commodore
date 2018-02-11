package com.energyxxer.commodore.commands.stopsound;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.commands.playsound.PlaySoundCommand;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class StopSoundCommand implements Command {

    private final Entity player;
    private final PlaySoundCommand.Source source;
    private final String sound;

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

        player.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "stopsound \be0" + (source != null ? " " + source.toString().toLowerCase() + (sound != null ? " " + sound : "") : (sound != null ? " * " + sound : "")), player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
