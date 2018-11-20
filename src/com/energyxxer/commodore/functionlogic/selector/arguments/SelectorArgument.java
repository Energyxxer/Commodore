package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.selector.Selector;

public interface SelectorArgument extends Cloneable {
    String getArgumentString();

    boolean isRepeatable();

    SelectorArgument clone();

    String getKey();

    ExecutionVariableMap getUsedExecutionVariables();

    default void assertCompatibleWith(Selector selector) {
        if(!isRepeatable() && !selector.getArgumentsByKey(getKey()).isEmpty())
            throw new IllegalArgumentException("Only one '" + getKey() + "' argument is allowed per selector");
    }
}
