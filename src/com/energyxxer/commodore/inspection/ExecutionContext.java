package com.energyxxer.commodore.inspection;

import com.energyxxer.commodore.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Holds all the context-modifying execute subcommands a command or function section is run under.
 * */
public class ExecutionContext {
    /**
     * The sender of the command/function, before the execute modifiers.
     * */
    private final Entity originalSender;
    /**
     * The list of execute modifiers which modify the execution context; these are added in order into the execute
     * command.
     * */
    private final ArrayList<ExecuteModifier> modifiers = new ArrayList<>();
    /**
     * The sender of the chained command; that is, the entity that is executing the command after all the execute
     * modifiers.
     * */
    private Entity finalSender;

    /**
     * Creates an execution context with no execute modifiers, executed by the given entity.
     *
     * @param sender The entity to run the command.
     * */
    public ExecutionContext(@NotNull Entity sender) {
        this.originalSender = this.finalSender = sender;
    }

    /**
     * Creates an execution context started by the given sender entity, and modified by the given execute modifiers.
     *
     * @param originalSender The sender entity before the execute modifiers are run.
     * @param modifiers The list of execute modifiers which modify the execution context from the original sender's
     *                  context.
     * */
    public ExecutionContext(@NotNull Entity originalSender, Collection<ExecuteModifier> modifiers) {
        this.originalSender = originalSender;
        this.modifiers.addAll(modifiers);
        updateFinalSender();
    }

    /**
     * Changes the final sender of this execution context to the that of the last execute modifier that
     * changes the command sender. If no modifiers change the sender, it is set to the original sender.
     * */
    private void updateFinalSender() {
        for(int i = modifiers.size() - 1; i >= 0; i--) {
            ExecuteModifier modifier = modifiers.get(i);
            if(modifier.getNewSender() != null) {
                finalSender = modifier.getNewSender();
                return;
            }
        }
        finalSender = originalSender;
    }

    /**
     * Retrieves the entity who starts the execute command.
     *
     * @return This context's original sender.
     * */
    public Entity getOriginalSender() {
        return originalSender;
    }

    /**
     * Retrieves a list of the execute modifiers that affect this execution context.
     *
     * @return This context's execute modifiers.
     * */
    public ArrayList<ExecuteModifier> getModifiers() {
        return new ArrayList<>(modifiers);
    }

    /**
     * Retrieves entity who runs the command after all the executes have been evaluated.
     *
     * @return The final sender of the context.
     * */
    public Entity getFinalSender() {
        return finalSender;
    }
}
