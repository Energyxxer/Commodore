package com.energyxxer.commodore.commands.drop;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.commands.CommandDelegateResolution;
import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DropCommand implements Command {
    private final DropDestination destination;
    private final DropSource source;

    public DropCommand(DropDestination destination, DropSource source) {
        this.destination = destination;
        this.source = source;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {

        StringBuilder sb = new StringBuilder("drop ");
        ArrayList<CommandEmbeddable> embeddables = new ArrayList<>();
        CommandDelegateResolution destinationResolution = destination.resolve();
        CommandDelegateResolution sourceResolution = source.resolve();

        sb.append(destinationResolution.attachment.replace("\be#", "\be" + embeddables.size()));
        embeddables.addAll(destinationResolution.embeddables);

        sb.append(' ');

        sb.append(sourceResolution.attachment.replace("\be#", "\be" + embeddables.size()));
        embeddables.addAll(sourceResolution.embeddables);

        return new CommandResolution(execContext, sb.toString(), embeddables);
    }
}

interface DropDestination {
    CommandDelegateResolution resolve();
}

interface DropSource {
    CommandDelegateResolution resolve();
}