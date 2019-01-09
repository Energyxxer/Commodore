package com.energyxxer.commodore.functionlogic.commands.title;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.textcomponents.TextComponent;
import org.jetbrains.annotations.NotNull;

public class TitleShowCommand extends TitleCommand {
    public enum Display {
        TITLE, SUBTITLE, ACTIONBAR
    }

    @NotNull
    private final Display display;
    @NotNull
    private final TextComponent message;

    public TitleShowCommand(@NotNull Entity player, @NotNull Display display, @NotNull TextComponent message) {
        super(player);
        this.display = display;
        this.message = message;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        String raw = message.toString();
        return new CommandResolution(execContext, "title " + player + " " + display.toString().toLowerCase() + " " + raw);
    }

}
