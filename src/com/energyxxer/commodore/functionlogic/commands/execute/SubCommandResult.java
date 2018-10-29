package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SubCommandResult {
    private final ExecutionContext execContext;
    private final String raw;
    private final ArrayList<CommandEmbeddable> embeddables = new ArrayList<>();

    public SubCommandResult(ExecutionContext execContext, String raw, Collection<CommandEmbeddable> embeddables) {
        this.execContext = execContext;
        this.raw = raw;
        if(embeddables != null) this.embeddables.addAll(embeddables);
    }

    public SubCommandResult(ExecutionContext execContext, String raw, CommandEmbeddable... embeddables) {
        this(execContext, raw, Arrays.asList(embeddables));
    }

    private void resolveEmbeddableEntities() {
        for(int i = 0; i < embeddables.size(); i++) {
            if(embeddables.get(i) instanceof Entity) {
                embeddables.set(i, ((Entity) embeddables.get(i)).resolveFor(execContext));
            }
        }
    }

    public ExecutionContext getExecContext() {
        return execContext;
    }

    public String getRaw() {
        return raw;
    }

    public ArrayList<CommandEmbeddable> getEmbeddables() {
        return embeddables;
    }
}
