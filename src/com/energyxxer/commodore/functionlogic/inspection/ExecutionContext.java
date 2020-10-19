package com.energyxxer.commodore.functionlogic.inspection;

import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteModifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Holds all the context-modifying execute sub-commands a command or function section is run under.
 * */
public class ExecutionContext {
    /**
     * The list of execute modifiers which modify the execution context; these are added in order into the execute
     * command.
     * */
    @NotNull
    private final ArrayList<@NotNull ExecuteModifier> modifiers = new ArrayList<>();

    /**
     * Creates an execution context with no execute modifiers, executed by the given entity.
     *  */
    public ExecutionContext() {
    }

    /**
     * Creates an execution context started by the given sender entity, and modified by the given execute modifiers.
     *  @param modifiers The list of execute modifiers which modify the execution context from the original sender's
     *                  context.
     * */
    public ExecutionContext(@NotNull Collection<ExecuteModifier> modifiers) {
        this.modifiers.addAll(modifiers);
    }

    /**
     * Retrieves a list of the execute modifiers that affect this execution context.
     *
     * @return This context's execute modifiers.
     * */
    public ArrayList<ExecuteModifier> getModifiers() {
        return new ArrayList<>(modifiers);
    }

}
