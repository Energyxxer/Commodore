package com.energyxxer.commodore.functions;

public interface FunctionWriter {
    String toFunctionContent(Function function);
    default void onAppend() {}
}
