package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public interface SelectorArgument extends Cloneable {
    String getArgumentString();

    boolean isRepeatable();

    SelectorArgument clone();

    String getKey();

    ExecutionVariableMap getUsedExecutionVariables();

    default boolean isCompatibleWith(Selector selector) {
        if(!isRepeatable() && !selector.getArgumentsByKey(getKey()).isEmpty()) throw new IllegalArgumentException("Only one '" + getKey() + "' allowed");
        return true;
    }
}
