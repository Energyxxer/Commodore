package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LootCommand implements Command {
    private final LootDestination destination;
    private final LootSource source;

    public LootCommand(LootDestination destination, LootSource source) {
        this.destination = destination;
        this.source = source;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {

        StringBuilder sb = new StringBuilder("loot ");
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

    public interface LootDestination {
        CommandDelegateResolution resolve();
    }

    public interface LootSource {
        CommandDelegateResolution resolve();
    }

}
