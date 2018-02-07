package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.functions.FunctionWriter;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public interface Command extends FunctionWriter {
    @Deprecated
    String getRawCommand(Entity sender);

    default String getRawCommand() {
        return getRawCommand(null);
    }

    CommandResolution resolveCommand(ExecutionContext execContext);

    //TODO: add an execution context parameter to this function... somehow
    @Override
    default String toFunctionContent(Function function) {
        try {
            return (isUsed()) ? getRawCommand(function.getSender()) : "# [REMOVED]";
        } catch(IllegalStateException x) {
            System.out.println(function.getAccessLog());
            throw x;
        }
    }

    @NotNull
    default Collection<ScoreboardAccess> getScoreboardAccesses() {
        return Collections.emptyList();
    }

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
        function.getAccessLog().filterCommand(this);
        getScoreboardAccesses().forEach(a -> a.setFunction(function));
    }
}
