package com.energyxxer.commodore.types;

public class TypeNotFoundException extends RuntimeException {
    public TypeNotFoundException() {
    }

    public TypeNotFoundException(String message) {
        super(message);
    }
}
