package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;

public class TitleTimesCommand extends TitleCommand {
    private Entity player;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    public TitleTimesCommand(Entity player, int fadeIn, int stay, int fadeOut) {
        this.player = player;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "title " + player.getSelectorAs(sender) + " times " + fadeIn + " " + stay + " " + fadeOut;
    }
}
