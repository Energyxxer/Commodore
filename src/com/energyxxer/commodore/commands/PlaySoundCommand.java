package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;

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
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "playsound " +
                sound + " " +
                source.toString().toLowerCase() + " " +
                player.getSelectorAs(sender) +
                ((location != null) ?
                        " " + location +
                                ((maxVolume != -1) ?
                                        " " + maxVolume +
                                                ((pitch != -1) ?
                                                        " " + pitch +
                                                                ((minVolume != -1) ?
                                                                        " " + minVolume
                                                                        : "")
                                                        : "")
                                        : "")
                        : "");
    }
}
