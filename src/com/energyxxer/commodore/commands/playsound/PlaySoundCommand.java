package com.energyxxer.commodore.commands.playsound;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class PlaySoundCommand implements Command {

    public enum Source {
        AMBIENT, BLOCK, HOSTILE, MASTER, MUSIC, NEUTRAL, PLAYER, RECORD, VOICE, WEATHER
    }

    private String sound;
    private Source source;
    private Entity player;
    private CoordinateSet location;
    private float maxVolume;
    private float pitch;
    private float minVolume;

    public PlaySoundCommand(String sound, Source source, Entity player) {
        this(sound, source, player, null);
    }

    public PlaySoundCommand(String sound, Source source, Entity player, CoordinateSet location) {
        this(sound, source, player, location, -1);
    }

    public PlaySoundCommand(String sound, Source source, Entity player, CoordinateSet location, float maxVolume) {
        this(sound, source, player, location, maxVolume, -1);
    }

    public PlaySoundCommand(String sound, Source source, Entity player, CoordinateSet location, float maxVolume, float pitch) {
        this(sound, source, player, location, maxVolume, pitch, -1);
    }

    public PlaySoundCommand(String sound, Source source, Entity player, CoordinateSet location, float maxVolume, float pitch, float minVolume) {
        this.sound = sound;
        this.source = source;
        this.player = player;
        this.location = location;
        this.maxVolume = maxVolume;
        this.pitch = pitch;
        this.minVolume = minVolume;

        player.assertPlayer();
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "playsound " +
                sound + " " +
                source.toString().toLowerCase() + " " +
                "\be0" +
                ((location != null) ?
                        " " + location +
                                ((maxVolume != -1) ?
                                        " " + CommandUtils.toString(maxVolume) +
                                                ((pitch != -1) ?
                                                        " " + CommandUtils.toString(pitch) +
                                                                ((minVolume != -1) ?
                                                                        " " + CommandUtils.toString(minVolume)
                                                                        : "")
                                                        : "")
                                        : "")
                        : ""), player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
