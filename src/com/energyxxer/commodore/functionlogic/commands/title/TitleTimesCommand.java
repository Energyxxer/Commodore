package com.energyxxer.commodore.functionlogic.commands.title;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class TitleTimesCommand extends TitleCommand {
    private final int fadeIn;
    private final int stay;
    private final int fadeOut;

    public TitleTimesCommand(@NotNull Entity player, int fadeIn, int stay, int fadeOut) {
        super(player);
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;

        if(fadeIn < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Fade-in must not be less than 0, found" + fadeIn, fadeIn, "FADE_IN");
        if(stay < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Stay must not be less than 0, found" + stay, stay, "STAY");
        if(fadeOut < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Fade-out must not be less than 0, found" + fadeOut, fadeOut, "FADE_OUT");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "title " + player + " times " + fadeIn + " " + stay + " " + fadeOut);
    }
}
