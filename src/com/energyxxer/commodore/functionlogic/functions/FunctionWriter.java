package com.energyxxer.commodore.functionlogic.functions;

import org.jetbrains.annotations.NotNull;

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
    @NotNull
    String toFunctionContent(@NotNull FunctionSection section);

    /**
     * Runs whenever this <code>FunctionWriter</code> is appended to a function.
     *
     * @param section The function this writer is appended to.
     */
    default void onAppend(@NotNull FunctionSection section) {
    }

    default void assertAvailable() {
    }
}
