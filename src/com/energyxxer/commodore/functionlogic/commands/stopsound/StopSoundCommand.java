package com.energyxxer.commodore.functionlogic.commands.stopsound;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.commands.playsound.PlaySoundCommand;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class StopSoundCommand implements Command {

    private final Entity player;
    private final PlaySoundCommand.Source source;
    private final String sound;

    public StopSoundCommand(Entity player) {
        this(player, null, null);
    }

    public StopSoundCommand(Entity player, PlaySoundCommand.Source source) {
        this(player, source, null);
    }

    public StopSoundCommand(Entity player, String sound) {
        this(player, null, sound);
    }

    public StopSoundCommand(Entity player, PlaySoundCommand.Source source, String sound) {
        this.player = player;
        this.source = source;
        this.sound = sound;

        player.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "stopsound \be0" +
                (source != null ?
                        sound != null ?
                                " " + source.toString().toLowerCase() + " " + sound
                                :
                                " " + source.toString().toLowerCase()
                        :
                        sound != null ?
                                " * " + sound
                                :
                                ""
                ), player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
