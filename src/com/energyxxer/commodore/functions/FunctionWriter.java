package com.energyxxer.commodore.functions;

public interface FunctionWriter {
    /**
     * Converts this FunctionWriter into a String representing it to be used in a function.
     *
     * @param section The function this writer is appended to.
     * @return The string to be written to the function.
     */
    String toFunctionContent(FunctionSection section);

    /**
     * Runs whenever this <code>FunctionWriter</code> is appended to a function.
     * @param section The function this writer is appended to.
     */
    default void onAppend(FunctionSection section) {
    }
}
