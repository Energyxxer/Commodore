package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.functions.FunctionWriter;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

import java.util.Collection;
import java.util.Collections;

public interface Command extends FunctionWriter {
    String getRawCommand(Entity sender);

    default String getRawCommand() {
        return getRawCommand(null);
    }

    @Override
    default String toFunctionContent(Function function) {
        return (isUsed()) ? getRawCommand(function.getSender()) : "# [REMOVED]";
    }

    default Collection<ScoreboardAccess> getScoreboardAccesses() {
        return Collections.emptyList();
    }

    default boolean isUsed() {
        for(ScoreboardAccess access : getScoreboardAccesses()) {
            if(access.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED)
                throw new IllegalStateException("This ScoreboardManipulation has unresolved access: " + this);
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
