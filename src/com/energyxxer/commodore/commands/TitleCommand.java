package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.textcomponents.TextComponent;

public class TitleCommand implements Command {
    public enum Region {
        TITLE, SUBTITLE, ACTIONBAR
    }

    private Entity player;
    private Region region;
    private TextComponent text;

    public TitleCommand(Entity player, Region region, TextComponent text) {
        this.player = player;
        this.region = region;
        this.text = text;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "title " + player.getSelectorAs(sender) + " " + region.toString().toLowerCase() + " " + text;
    }
}
