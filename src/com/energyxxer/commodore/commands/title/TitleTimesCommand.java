package com.energyxxer.commodore.commands.title;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class TitleTimesCommand extends TitleCommand {
    private int fadeIn;
    private int stay;
    private int fadeOut;

    public TitleTimesCommand(Entity player, int fadeIn, int stay, int fadeOut) {
        super(player);
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "title \be0 times " + fadeIn + " " + stay + " " + fadeOut, player);
    }
}
