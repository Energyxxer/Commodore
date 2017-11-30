package com.energyxxer.commodore;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.functions.FunctionWriter;

public interface Command extends FunctionWriter {
    String getRawCommand(Entity sender);
    default String getRawCommand() {
        return getRawCommand(null);
    }
    @Override
    default String toFunctionContent(Function function) {
        return getRawCommand(function.getSender());
    }

}
