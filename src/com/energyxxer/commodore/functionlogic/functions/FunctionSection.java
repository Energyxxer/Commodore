package com.energyxxer.commodore.functionlogic.functions;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.module.Namespace;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

/**
 * Describes an abstraction of a function onto which FunctionWriters can be appended to.
 *
 * @see Function
 * @see FunctionWriter
 * */
public interface FunctionSection {
    /**
     * Adds the given writer or writers to this function section, to be printed into the function after all the
     * previously appended writers.
     *
     * @param writers The writers to add to this function section.
     * */
    default void append(@NotNull FunctionWriter... writers) {
        append(Arrays.asList(writers));
    }

    /**
     * Adds the given writer or writers to this function section, to be printed into the function after all the
     * previously added writers.
     *
     * @param writers The writers to add to this function section.
     * */
    void append(@NotNull Collection<@NotNull FunctionWriter> writers);

    /**
     * Adds the given writer or writers to this function section, to be printed into the function before all the
     * previously added writers.
     *
     * @param writers The writers to add to this function section.
     * */
    default void prepend(@NotNull FunctionWriter... writers) {
        prepend(Arrays.asList(writers));
    }

    /**
     * Adds the given writer or writers to this function section, to be printed into the function before all the
     * previously added writers.
     *
     * @param writers The writers to add to this function section.
     * */
    void prepend(@NotNull Collection<@NotNull FunctionWriter> writers);

    /**
     * Retrieves the namespace this function section belongs to.
     *
     * @return The namespace this function section belongs to.
     * */
    @NotNull
    Namespace getNamespace();

    /**
     * Retrieves the execution context this function section runs on.
     *
     * @return The execution context this function section runs on.
     * */
    ExecutionContext getExecutionContext();
}
