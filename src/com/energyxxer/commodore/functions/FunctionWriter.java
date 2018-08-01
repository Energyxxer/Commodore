package com.energyxxer.commodore.functions;

/**
 * Describes an element that can write its contents into a Function via a FunctionSection.
 *
 * @see Function
 * @see FunctionSection
 * */
public interface FunctionWriter {
    /**
     * Converts this FunctionWriter into a String representing it to be used in a function.
     *
     * @param section The function this writer is appended to.
     *
     * @return The string to be written to the function section.
     */
    String toFunctionContent(FunctionSection section);

    /**
     * Runs whenever this <code>FunctionWriter</code> is appended to a function.
     *
     * @param section The function this writer is appended to.
     */
    default void onAppend(FunctionSection section) {
    }
}
