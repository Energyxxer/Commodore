package com.energyxxer.commodore.functionlogic.commands.playsound;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class PlaySoundCommand implements Command {

    public enum Source {
        AMBIENT, BLOCK, HOSTILE, MASTER, MUSIC, NEUTRAL, PLAYER, RECORD, VOICE, WEATHER
    }

    @NotNull
    private final String sound;
    @NotNull
    private final Source source;
    @Nullable
    private final Entity player;
    @Nullable
    private final CoordinateSet location;
    private final float maxVolume;
    private final float pitch;
    private final float minVolume;

    public PlaySoundCommand(@NotNull String sound, @NotNull Source source) {
        this(sound, source, null, null);
    }

    public PlaySoundCommand(@NotNull String sound, @NotNull Source source, @Nullable Entity player) {
        this(sound, source, player, null);
    }

    public PlaySoundCommand(@NotNull String sound, @NotNull Source source, @Nullable Entity player, @Nullable CoordinateSet location) {
        this(sound, source, player, location, 1);
    }

    public PlaySoundCommand(@NotNull String sound, @NotNull Source source, @Nullable Entity player, @Nullable CoordinateSet location, float maxVolume) {
        this(sound, source, player, location, maxVolume, 1);
    }

    public PlaySoundCommand(@NotNull String sound, @NotNull Source source, @Nullable Entity player, @Nullable CoordinateSet location, float maxVolume, float pitch) {
        this(sound, source, player, location, maxVolume, pitch, 0);
    }

    public PlaySoundCommand(@NotNull String sound, @NotNull Source source, @Nullable Entity player, @Nullable CoordinateSet location, float maxVolume, float pitch, float minVolume) {
        this.sound = sound;
        this.source = source;
        this.player = player;
        this.location = location;
        this.maxVolume = maxVolume;
        this.pitch = pitch;
        this.minVolume = minVolume;
        assertFinite(maxVolume, "maxVolume");
        assertFinite(pitch, "pitch");
        assertFinite(minVolume, "minVolume");

        if(maxVolume < 0.0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Max volume must not be less than 0.0, found " + maxVolume, maxVolume, "MAX_VOLUME");
        if(pitch < 0.0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Max volume must not be less than 0.0, found " + pitch, pitch, "PITCH");
        if(pitch > 2.0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Max volume must not be more than 2.0, found " + pitch, pitch, "PITCH");
        if(minVolume < 0.0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Min volume must not be less than 0.0, found " + minVolume, minVolume, "MIN_VOLUME");
        if(minVolume > 1.0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Min volume must not be more than 1.0, found " + minVolume, minVolume, "MIN_VOLUME");

        if(player != null) {
            player.assertPlayer();
            player.assertEntityFriendly();
        }
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "playsound " +
                sound + " " +
                (VersionFeatureManager.getBoolean("command.playsound.channel", true) ? (source.toString().toLowerCase(Locale.ENGLISH) + " ") : "") +
                (player != null ? player : new Selector(Selector.BaseSelector.SENDER)) +
                ((location != null || maxVolume != 1 || pitch != 1 || minVolume != 0) ?
                        " " + (location != null ? location : new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE)) +
                                ((maxVolume != 1 || pitch != 1 || minVolume != 0) ?
                                        " " + CommandUtils.numberToPlainString(maxVolume) +
                                                ((pitch != 1 || minVolume != 0) ?
                                                        " " + CommandUtils.numberToPlainString(pitch) +
                                                                ((minVolume != 0) ?
                                                                        " " + CommandUtils.numberToPlainString(minVolume)
                                                                        : "")
                                                        : "")
                                        : "")
                        : ""));
    }

    @Override
    public void assertAvailable() {
        if(player != null) player.assertAvailable();
    }
}
