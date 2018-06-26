package com.energyxxer.commodore.inspection;

import com.energyxxer.commodore.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CommandResolutionLine {
    final String raw;
    final ArrayList<CommandEmbeddable> embeddables = new ArrayList<>();
    final ExecutionContext execContext;

    public CommandResolutionLine(ExecutionContext execContext, String raw, CommandEmbeddable... embeddables) {
        this(execContext, raw, Arrays.asList(embeddables));
    }

    public CommandResolutionLine(ExecutionContext execContext, String raw, Collection<CommandEmbeddable> embeddables) {
        this.raw = raw;
        this.execContext = execContext;
        if(embeddables != null) this.embeddables.addAll(embeddables);
    }

    String construct() {
        ArrayList<ExecuteModifier> modifiers = new ArrayList<>(execContext.getModifiers());

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < embeddables.size(); i++) {
            CommandEmbeddable embeddable = embeddables.get(i);
            if(embeddable instanceof Entity) {
                EntityResolution resolution = ((Entity) embeddable).resolveFor(new ExecutionContext(execContext.getOriginalSender(), modifiers));

                modifiers.addAll(resolution.getModifiers());
                embeddables.set(i, resolution);
            }
        }

        String chainedCommand = raw;

        for(int i = 0; i < embeddables.size(); i++) {
            chainedCommand = CommandResolution.embed(chainedCommand, "\be" + i, embeddables.get(i).toString());
        }

        if(!modifiers.isEmpty()) {
            sb.append("execute ");
            sb.append(CommandResolution.resolveModifiers(execContext, modifiers));
            sb.append(" run ");
        }
        sb.append(chainedCommand);

        return sb.toString();
    }
}
