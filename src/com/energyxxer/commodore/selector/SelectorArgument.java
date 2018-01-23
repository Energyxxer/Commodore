package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public interface SelectorArgument extends Cloneable {
    String getArgumentString();

    boolean isRepeatable();

    SelectorArgument clone();

    String getKey();

    ExecutionVariableMap getUsedExecutionVariables();
}
