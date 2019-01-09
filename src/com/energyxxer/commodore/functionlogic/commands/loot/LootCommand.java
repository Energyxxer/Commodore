package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class LootCommand implements Command {
    @NotNull
    private final LootDestination destination;
    @NotNull
    private final LootSource source;

    public LootCommand(@NotNull LootDestination destination, @NotNull LootSource source) {
        this.destination = destination;
        this.source = source;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {

        StringBuilder sb = new StringBuilder("loot ");
        CommandDelegateResolution destinationResolution = destination.resolve();
        CommandDelegateResolution sourceResolution = source.resolve();

        sb.append(destinationResolution.attachment);

        sb.append(' ');

        sb.append(sourceResolution.attachment);

        return new CommandResolution(execContext, sb.toString());
    }

    public interface LootDestination {
        @NotNull
        CommandDelegateResolution resolve();
    }

    public interface LootSource {
        @NotNull
        CommandDelegateResolution resolve();
    }

}
