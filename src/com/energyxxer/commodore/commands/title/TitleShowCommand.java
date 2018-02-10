package com.energyxxer.commodore.commands.title;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.textcomponents.TextComponent;

public class TitleShowCommand extends TitleCommand {
    public enum Display {
        TITLE, SUBTITLE, ACTIONBAR
    }

    private Display display;
    private TextComponent text;

    public TitleShowCommand(Entity player, Display display, TextComponent text) {
        super(player);
        this.display = display;
        this.text = text;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "title \be0 " + display.toString().toLowerCase() + " " + text, player);
    }
}
