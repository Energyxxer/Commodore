package com.energyxxer.commodore.functionlogic.commands.title;

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
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "title " + player + " times " + fadeIn + " " + stay + " " + fadeOut);
    }
}
