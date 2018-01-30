package com.energyxxer.commodore.inspection;

import com.energyxxer.commodore.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CommandResolution {
    private ExecutionContext execContext;
    private String raw;
    private ArrayList<CommandEmbeddable> embeddables = new ArrayList<>();

    public CommandResolution(ExecutionContext execContext, String raw, Collection<CommandEmbeddable> embeddables) {
        this.execContext = execContext;
        this.raw = raw;
        if(embeddables != null) this.embeddables.addAll(embeddables);
    }

    public CommandResolution(ExecutionContext execContext, String raw, CommandEmbeddable... embeddables) {
        this(execContext, raw, Arrays.asList(embeddables));
    }

    private void resolveEmbeddableEntities() {
        for(int i = 0; i < embeddables.size(); i++) {
            if(embeddables.get(i) instanceof Entity) {
                embeddables.set(i, ((Entity) embeddables.get(i)).resolveFor(execContext));
            }
        }
    }

    public String construct() {
        resolveEmbeddableEntities();

        ArrayList<ExecuteModifier> modifiers = new ArrayList<>();

        for(CommandEmbeddable embeddable : embeddables) {
            if(embeddable instanceof EntityResolution) {
                modifiers.addAll(((EntityResolution) embeddable).getModifiers());
            }
        }

        String chainedCommand = raw;

        for(int i = 0; i < embeddables.size(); i++) {
            chainedCommand = chainedCommand.replace("\be" + i, embeddables.get(i).toString());
        }

        StringBuilder sb = new StringBuilder();

        if(!modifiers.isEmpty()) {
            sb.append("execute ");
            for(ExecuteModifier modifier : modifiers) {
                //TODO: Add subcommand execution context support
                sb.append(modifier.getSubCommand(null));
                sb.append(" ");
            }
        }
        sb.append(chainedCommand);

        return sb.toString();
    }
}
