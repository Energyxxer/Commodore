package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.inspection.CommandEmbeddable;

import java.util.Arrays;
import java.util.List;

public class CommandDelegateResolution {
    public String attachment;
    public List<CommandEmbeddable> embeddables;

    public CommandDelegateResolution(String attachment, CommandEmbeddable... embeddables) {
        this.attachment = attachment;
        this.embeddables = Arrays.asList(embeddables);
    }
}
