package com.energyxxer.commodore.functionlogic.score;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.scoreboard.ObjectivesAddCommand;
import com.energyxxer.commodore.textcomponents.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Objective {
    @NotNull
    private final ObjectiveManager parent;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @Nullable
    private TextComponent displayName;
    /**
     * Signifies whether the objective acts as a field, used to carry data from one tick or function to the next.
     * If this is true, a SET access command not followed by a GET access command at the end
     * of a function tree will not be removed.
     */
    private boolean field = false;

    public static final int MAX_NAME_LENGTH = 16;

    Objective(@NotNull ObjectiveManager parent, @NotNull String name, @NotNull String type) {
        this(parent, name, type, null);
    }

    Objective(@NotNull ObjectiveManager parent, @NotNull String name, @NotNull String type, @Nullable TextComponent displayName) {
        this(parent, name, type, displayName, false);
    }

    Objective(@NotNull ObjectiveManager parent, @NotNull String name, @NotNull String type, boolean field) {
        this(parent, name, type, null, field);
    }

    Objective(@NotNull ObjectiveManager parent, @NotNull String name, @NotNull String type, @Nullable TextComponent displayName, boolean field) {
        if(name.length() <= 0)
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Objective name must not be empty", name);
        if(name.length() > MAX_NAME_LENGTH)
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Objective name '" + name + "' exceeds the limit of " + MAX_NAME_LENGTH + " characters", name);
        if(!name.matches(CommandUtils.IDENTIFIER_ALLOWED))
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Objective name '" + name + "' has illegal characters. Does not match regex: " + CommandUtils.IDENTIFIER_ALLOWED, name);
        this.parent = parent;
        this.name = name;
        this.type = type;
        this.displayName = displayName;
        this.field = field;
    }

    @NotNull
    public String getName() {
        return (parent.isPrefixEnabled() ? parent.getOwner().getPrefix() + "_" : "") + name;
    }

    @NotNull
    public String getType() {
        return type;
    }

    public boolean isField() {
        return field;
    }

    @Nullable
    public TextComponent getDisplayName() {
        return displayName;
    }

    @NotNull
    public ObjectiveManager getParent() {
        return parent;
    }

    @NotNull
    public ObjectivesAddCommand getObjectiveCreator() {
        return new ObjectivesAddCommand(this);
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
