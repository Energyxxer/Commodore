package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SubCommandResult {
    @NotNull
    private final ExecutionContext execContext;
    @NotNull
    private final String raw;
    @NotNull
    private final ArrayList<CommandEmbeddable> embeddables = new ArrayList<>();

    public SubCommandResult(@NotNull ExecutionContext execContext, @NotNull String raw, @Nullable Collection<CommandEmbeddable> embeddables) {
        this.execContext = execContext;
        this.raw = raw;
        if(embeddables != null) this.embeddables.addAll(embeddables);
    }

    public SubCommandResult(@NotNull ExecutionContext execContext, @NotNull String raw, CommandEmbeddable... embeddables) {
        this(execContext, raw, Arrays.asList(embeddables));
    }

    private void resolveEmbeddableEntities() {
        for(int i = 0; i < embeddables.size(); i++) {
            if(embeddables.get(i) instanceof Entity) {
                embeddables.set(i, ((Entity) embeddables.get(i)).resolveFor(execContext));
            }
        }
    }

    @NotNull
    public ExecutionContext getExecContext() {
        return execContext;
    }

    @NotNull
    public String getRaw() {
        return raw;
    }

    @NotNull
    public ArrayList<CommandEmbeddable> getEmbeddables() {
        return embeddables;
    }
}
