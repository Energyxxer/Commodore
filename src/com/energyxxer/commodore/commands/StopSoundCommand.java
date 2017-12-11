package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;

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
}
