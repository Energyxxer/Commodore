package com.energyxxer.commodore.functionlogic.commands.playsound;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class PlaySoundCommand implements Command {

    public enum Source {
        AMBIENT, BLOCK, HOSTILE, MASTER, MUSIC, NEUTRAL, PLAYER, RECORD, VOICE, WEATHER
    }

    private final String sound;
    private final Source source;
    private final Entity player;
    private final CoordinateSet location;
    private final float maxVolume;
    private final float pitch;
    private final float minVolume;

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

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "playsound " +
                sound + " " +
                source.toString().toLowerCase() + " " +
                "\be0" +
                ((location != null) ?
                        " " + location +
                                ((maxVolume != -1) ?
                                        " " + CommandUtils.numberToPlainString(maxVolume) +
                                                ((pitch != -1) ?
                                                        " " + CommandUtils.numberToPlainString(pitch) +
                                                                ((minVolume != -1) ?
                                                                        " " + CommandUtils.numberToPlainString(minVolume)
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
