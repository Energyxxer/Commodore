package com.energyxxer.mctech;

import com.energyxxer.mctech.entity.Entity;
import com.energyxxer.mctech.functions.Function;
import com.energyxxer.mctech.functions.FunctionWriter;

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
