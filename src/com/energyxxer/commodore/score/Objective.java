package com.energyxxer.commodore.score;

public class Objective {
    private final ObjectiveManager parent;
    private String name;
    private String type;

    public static final int MAX_NAME_LENGTH = 16;

    Objective(ObjectiveManager parent, String name) {
        this(parent, name, "dummy");
    }

    Objective(ObjectiveManager parent, String name, String type) {
        if(name.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("Objective name '" + name + "' exceeds the limit of " + MAX_NAME_LENGTH + " characters");
        this.parent = parent;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ObjectiveManager getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return name + (!type.equals("dummy") ? " ("+type+")" : "");
    }
}
