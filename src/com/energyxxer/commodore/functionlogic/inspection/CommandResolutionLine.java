package com.energyxxer.commodore.functionlogic.inspection;

import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CommandResolutionLine {
    @NotNull
    final String raw;
    @NotNull
    final ArrayList<CommandEmbeddable> embeddables = new ArrayList<>();
    @NotNull
    final ArrayList<CommandEmbeddableResolution> embeddableResolutions = new ArrayList<>();
    @NotNull
    final ExecutionContext execContext;

    public CommandResolutionLine(@NotNull ExecutionContext execContext, @NotNull String raw, @NotNull CommandEmbeddable... embeddables) {
        this(execContext, raw, Arrays.asList(embeddables));
    }

    public CommandResolutionLine(@NotNull ExecutionContext execContext, @NotNull String raw, @Nullable Collection<@NotNull CommandEmbeddable> embeddables) {
        this.raw = raw;
        this.execContext = execContext;
        if(embeddables != null) this.embeddables.addAll(embeddables);
    }

    @NotNull
    String construct() {
        ArrayList<ExecuteModifier> modifiers = execContext.getModifiers();

        StringBuilder sb = new StringBuilder();

        for(CommandEmbeddable embeddable : embeddables) {
            CommandEmbeddableResolution resolution = embeddable.resolveEmbed(new ExecutionContext(execContext.getOriginalSender(), modifiers));
            modifiers.addAll(resolution.getNewModifiers());
            embeddableResolutions.add(resolution);
        }

        String chainedCommand = raw;

        for(int i = 0; i < embeddableResolutions.size(); i++) {
            chainedCommand = CommandResolution.embed(chainedCommand, "\be" + i, embeddableResolutions.get(i).getEmbedString());
        }

        if(!modifiers.isEmpty()) {
            sb.append("execute ");
            sb.append(CommandResolution.resolveModifiers(execContext, modifiers));
            if(chainedCommand.length() > 0) {
                sb.append(" run ");
            }
        }
        sb.append(chainedCommand);

        return sb.toString();
    }
}
