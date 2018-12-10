package com.energyxxer.commodore.functionlogic.commands;

import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class CommandDelegateResolution {
    @NotNull
    public String attachment;
    @NotNull
    public List<CommandEmbeddable> embeddables;

    public CommandDelegateResolution(@NotNull String attachment, @NotNull CommandEmbeddable... embeddables) {
        this.attachment = attachment;
        this.embeddables = Arrays.asList(embeddables);
    }
}
