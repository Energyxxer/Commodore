package com.energyxxer.commodore.functions;

import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.module.Namespace;

import java.util.Arrays;
import java.util.Collection;

public interface FunctionSection {
    default void append(FunctionWriter... writers) {
        append(Arrays.asList(writers));
    }

    void append(Collection<FunctionWriter> writers);

    Namespace getNamespace();

    ExecutionContext getExecutionContext();
}
