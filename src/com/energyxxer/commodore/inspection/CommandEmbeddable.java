package com.energyxxer.commodore.inspection;

/**
 * Represents an element that can be appended into a command.
 * */
public interface CommandEmbeddable {
    CommandEmbeddableResolution resolveEmbed(ExecutionContext execContext);
}
