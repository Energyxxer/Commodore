package com.energyxxer.commodore.score;

public class Objective {
    private String name;
    private String type;

    public static final int MAX_NAME_LENGTH = 16;

    Objective(String name) {
        this(name, "dummy");
    }

    Objective(String name, String type) {
        if(name.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("Objective name '" + name + "' exceeds the limit of " + MAX_NAME_LENGTH + " characters");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
