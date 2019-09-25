package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import org.jetbrains.annotations.NotNull;

public interface SelectorArgument extends Cloneable {
    @NotNull
    String getArgumentString();

    boolean isRepeatable();

    @NotNull
    SelectorArgument clone();

    @NotNull
    String getKey();

    ExecutionVariableMap getUsedExecutionVariables();

    default void assertCompatibleWith(@NotNull Selector selector) {
        if(!isRepeatable() && !selector.getArgumentsByKey(getKey()).isEmpty())
            throw new CommodoreException(CommodoreException.Source.SELECTOR_ERROR, "Only one '" + getKey() + "' argument is allowed per selector", selector);
    }

    default void assertAvailable() {
    }
}
