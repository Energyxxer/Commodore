package com.energyxxer.commodore.functionlogic.score;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.scoreboard.ObjectivesAddCommand;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
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

    Objective(@NotNull ObjectiveManager parent, @NotNull String name, @NotNull String type, @Nullable TextComponent displayName) {
        this.parent = parent;
        this.name = name;
        assertNameValid(name);
        this.type = type;
        this.displayName = displayName;
        if(displayName != null) displayName.assertAvailable();
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getType() {
        return type;
    }

    @Deprecated
    public boolean isField() {
        return true;
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
        if(!name.matches(VersionFeatureManager.getString("objectives.regex", CommandUtils.IDENTIFIER_ALLOWED))) {
            return CommandUtils.quote(name);
        } else {
            return name;
        }
    }

    public void assertAvailable() {
        if (!type.equals("dummy")) {
            VersionFeatureManager.assertEnabled("scoreboard.criteria");
        }
    }

    public static void assertNameValid(String name) {
        if(name.length() <= 0) {
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Objective name must not be empty", name);
        }
        if(name.length() > VersionFeatureManager.getInt("objective.max_length", 16)) {
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Objective name '" + name + "' exceeds the limit of " + VersionFeatureManager.getInt("objective.max_length", 16) + " characters", name);
        }
        if(!VersionFeatureManager.getBoolean("objectives.accept_strings", false) && !name.matches(VersionFeatureManager.getString("objectives.regex", CommandUtils.IDENTIFIER_ALLOWED))) {
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Objective name '" + name + "' has illegal characters. Does not match regex: " + VersionFeatureManager.getString("objective.regex", CommandUtils.IDENTIFIER_ALLOWED), name);
        }
    }
}
