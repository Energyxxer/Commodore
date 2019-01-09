package com.energyxxer.commodore.functionlogic.commands;

import com.energyxxer.commodore.functionlogic.functions.FunctionSection;
import com.energyxxer.commodore.functionlogic.functions.FunctionWriter;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract representation of a Minecraft command.
 */
public interface Command extends FunctionWriter {

    /**
     * Resolves this command into a <code>CommandResolution</code> object, given the execution context passed.
     *
     * @param execContext The execution context this command is run under.
     * @return A <code>CommandResolution</code> object containing the execution context, formatted command and
     * execute modifiers.
     */
    @NotNull
    CommandResolution resolveCommand(ExecutionContext execContext);

    @NotNull
    @Override
    default String toFunctionContent(@NotNull FunctionSection section) {
        return resolveCommand(section.getExecutionContext()).construct();
    }

    @Override
    default void onAppend(@NotNull FunctionSection section) {
        this.onAppend(section, section.getExecutionContext());
    }

    /**
     * Runs whenever this command is appended into a function.
     * @param section The function section this command is appended to.
     * @param execContext The execution context of this command. May not be the function's execution context.
     */
    default void onAppend(FunctionSection section, ExecutionContext execContext) {
    }

}
