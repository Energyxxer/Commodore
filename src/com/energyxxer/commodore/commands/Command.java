package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.functions.FunctionWriter;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.module.options.UnusedCommandPolicy;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

import static com.energyxxer.commodore.module.options.UnusedCommandPolicy.*;

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

    @Override
    default String toFunctionContent(Function function) {
        try {
            UnusedCommandPolicy policy = function.getNamespace().getOwner().getOptionManager().UNUSED_COMMAND_POLICY.getValue();
            if(policy == KEEP) {
                return resolveCommand(function.getExecutionContext()).construct();
            } else {
                boolean used = !isScoreboardManipulation() || isUsed();

                if(!used && policy == REMOVE) return null;

                String content = resolveCommand(function.getExecutionContext()).construct();

                if(!used && policy == COMMENT_OUT) content = "# [UNUSED]: " + content;

                return content;
            }
        } catch(IllegalStateException x) {
            System.out.println(function.getAccessLog());
            throw x;
        }
    }

    /**
     * A list of the scoreboard accesses performed by this command. The list returned by this method may
     * be different objects when called more than once, but the objects inside those lists must
     * always be the same, for purposes of validation and scoreboard optimization.
     * @return A list of the scoreboard accesses performed by this command.
     */
    @NotNull
    default Collection<ScoreboardAccess> getScoreboardAccesses() {
        return Collections.emptyList();
    }

    /**
     * Whether any of this command's scoreboard accesses are used. This does not reflect the result of
     * isScoreboardManipulation.
     * @return true if any of the scoreboard accesses are used, false if all are unused.
     */
    default boolean isUsed() {
        for(ScoreboardAccess access : getScoreboardAccesses()) {
            if(access.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED) {
                throw new IllegalStateException("This ScoreboardManipulation has unresolved access: " + access + " - in: " + this);
            }
            if(access.getResolution() == ScoreboardAccess.AccessResolution.UNUSED) return false;
        }
        return true;
    }

    @Override
    default void onAppend(Function function) {
        this.onAppend(function, function.getExecutionContext());
    }

    /**
     * Runs whenever this command is appended into a function.
     * @param function The function this command is appended to.
     * @param execContext The execution context of this command. May not be the function's execution context.
     */
    default void onAppend(Function function, ExecutionContext execContext) {
    }

    /**
     * Whether this command should be omitted if all its scoreboard accesses are unused. Typically used for
     * classes derived from the /scoreboard command.
     *
     * @return true if the command should be omitted when scoreboard accesses unused, false otherwise.
     */
    default boolean isScoreboardManipulation() {
        return false;
    }
}
