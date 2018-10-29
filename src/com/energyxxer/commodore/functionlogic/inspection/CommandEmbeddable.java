package com.energyxxer.commodore.functionlogic.inspection;

/**
 * Represents an element that can be appended into a command.
 * */
public interface CommandEmbeddable {
    /**
     * Based on the given execution context, retrieves the info needed to append this object into a command, including
     * required execute modifiers and string to replace the embed marker.
     *
     * @param execContext The execution context this embeddable is present in.
     *
     * @return The {@link CommandEmbeddableResolution} object containing all the info needed to append this object
     * into a command.
     * */
    CommandEmbeddableResolution resolveEmbed(ExecutionContext execContext);
}
