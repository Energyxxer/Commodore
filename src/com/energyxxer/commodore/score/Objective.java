package com.energyxxer.commodore.score;

import com.energyxxer.commodore.functions.FunctionWriter;

import java.util.Objects;

public class Objective {
    private final ObjectiveManager parent;
    private String name;
    private String type;
    /**
     * Signifies whether the objective acts as a field, used to carry data from one tick or function to the next.
     * If this is true, a SET access command not followed by a GET access command at the end
     * of a function tree will not be removed.
     */
    private boolean field = false;

    public static final int MAX_NAME_LENGTH = 16;

    Objective(ObjectiveManager parent, String name) {
        this(parent, name, false);
    }

    Objective(ObjectiveManager parent, String name, boolean field) {
        this(parent, name, "dummy", field);
    }

    Objective(ObjectiveManager parent, String name, String type) {
        this(parent, name, type, false);
    }

    Objective(ObjectiveManager parent, String name, String type, boolean field) {
        if(name.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException("Objective name '" + name + "' exceeds the limit of " + MAX_NAME_LENGTH + " characters");
        this.parent = parent;
        this.name = name;
        this.type = type;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isField() {
        return field;
    }

    public ObjectiveManager getParent() {
        return parent;
    }

    public FunctionWriter getObjectiveCreator() {
        return f -> "scoreboard objectives add " + name + " " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objective objective = (Objective) o;
        return Objects.equals(parent, objective.parent) &&
                Objects.equals(name, objective.name) &&
                Objects.equals(type, objective.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, name, type);
    }

    @Override
    public String toString() {
        return name + (!type.equals("dummy") ? " (" + type + ")" : "");
    }
}
