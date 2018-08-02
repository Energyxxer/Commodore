package com.energyxxer.commodore.defpacks;

/**
 * Signals that a definition pack has been constructed poorly and that mandatory elements of that pack were not present.
 * */
public class MalformedPackException extends RuntimeException {
    /**
     * Constructs a MalformedPackException with no message.
     * */
    public MalformedPackException() {
    }

    /**
     * Constructs a MalformedPackException with the given message.
     *
     * @param message The message detailing what element of the definition pack led to this exception.
     * */
    public MalformedPackException(String message) {
        super(message);
    }
}
