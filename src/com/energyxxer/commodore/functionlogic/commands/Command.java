package com.energyxxer.commodore.functionlogic.commands;

import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.functions.FunctionSection;
import com.energyxxer.commodore.functionlogic.functions.FunctionWriter;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import com.energyxxer.commodore.module.options.UnusedCommandPolicy;
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

    @NotNull
    @Override
    default String toFunctionContent(@NotNull FunctionSection section) {
        try {
            UnusedCommandPolicy policy = section.getNamespace().getOwner().getOptionManager().UNUSED_COMMAND_POLICY.getValue();
            if(policy == KEEP) {
                return resolveCommand(section.getExecutionContext()).construct();
            } else {
                boolean used = !isScoreboardManipulation() || isUsed();

                if(!used && policy == REMOVE) return null;

                String content = resolveCommand(section.getExecutionContext()).construct();

                if(!used && policy == COMMENT_OUT) content = "# [UNUSED]: " + content;

                return content;
            }
        } catch(IllegalStateException x) {
            if(section instanceof Function) System.out.println(((Function) section).getAccessLog());
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
