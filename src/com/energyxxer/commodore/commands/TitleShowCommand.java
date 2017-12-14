package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.textcomponents.TextComponent;

public class TitleShowCommand extends TitleCommand {
    public enum Display {
        TITLE, SUBTITLE, ACTIONBAR
    }

    private Entity player;
    private Display display;
    private TextComponent text;

    public TitleShowCommand(Entity player, Display display, TextComponent text) {
        this.player = player;
        this.display = display;
        this.text = text;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "title " + player.getSelectorAs(sender) + " " + display.toString().toLowerCase() + " " + text;
    }
}
