package com.energyxxer.commodore.functionlogic.commands.playsound;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaySoundCommand implements Command {

    public enum Source {
        AMBIENT, BLOCK, HOSTILE, MASTER, MUSIC, NEUTRAL, PLAYER, RECORD, VOICE, WEATHER
    }

    @NotNull
    private final String sound;
    @NotNull
    private final Source source;
    @NotNull
    private final Entity player;
    @Nullable
    private final CoordinateSet location;
    private final float maxVolume;
    private final float pitch;
    private final float minVolume;

    public PlaySoundCommand(@NotNull String sound, @NotNull Source source, @NotNull Entity player) {
        this(sound, source, player, null);
    }

    public PlaySoundCommand(@NotNull String sound, @NotNull Source source, @NotNull Entity player, @Nullable CoordinateSet location) {
        this(sound, source, player, location, -1);
    }

    public PlaySoundCommand(@NotNull String sound, @NotNull Source source, @NotNull Entity player, @Nullable CoordinateSet location, float maxVolume) {
        this(sound, source, player, location, maxVolume, -1);
    }

    public PlaySoundCommand(@NotNull String sound, @NotNull Source source, @NotNull Entity player, @Nullable CoordinateSet location, float maxVolume, float pitch) {
        this(sound, source, player, location, maxVolume, pitch, -1);
    }

    public PlaySoundCommand(@NotNull String sound, @NotNull Source source, @NotNull Entity player, @Nullable CoordinateSet location, float maxVolume, float pitch, float minVolume) {
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
                player +
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
                        : ""));
    }

}
