package com.energyxxer.commodore.functionlogic.score;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

/**
 * Implements an entity that represents a player referred by name, which may or may not exist as a player entity.
 * */
public class PlayerName implements Entity {
    /**
     * The name of this fake player.
     * */
    @NotNull
    private final String name;

    /**
     * Creates a fake player with the given name.
     *
     * @param name The name of the new fake player.
     * */
    public PlayerName(@NotNull String name) {
        this.name = name;
    }

    @Override
    public void assertEntityFriendly(String causeKey) {
        if(!VersionFeatureManager.getBoolean("player_names.accept_strings", false) && !name.matches(VersionFeatureManager.getString("player_names.regex", CommandUtils.IDENTIFIER_ALLOWED))) {
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Player name '" + name + "' has illegal characters. Quoting is not supported in the target version and does not match regex: " + VersionFeatureManager.getString("player_names.regex", CommandUtils.IDENTIFIER_ALLOWED), name, causeKey);
        }
    }

    @Override
    public void assertScoreHolderFriendly(String causeKey) {
        if(!VersionFeatureManager.getBoolean("score_holders.accept_strings", false) && !name.matches(VersionFeatureManager.getString("score_holders.regex", CommandUtils.IDENTIFIER_ALLOWED))) {
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Score holder '" + name + "' has illegal characters. Quoting is not supported in the target version and does not match regex: " + VersionFeatureManager.getString("score_holders.regex", CommandUtils.IDENTIFIER_ALLOWED), name, causeKey);
        }
    }

    @Override
    public void assertGameProfile(String causeKey) {
        if(!VersionFeatureManager.getBoolean("game_profiles.accept_strings", false) && !name.matches(VersionFeatureManager.getString("game_profiles.regex", CommandUtils.IDENTIFIER_ALLOWED))) {
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Game profile '" + name + "' has illegal characters. Quoting is not supported in the target version and does not match regex: " + VersionFeatureManager.getString("game_profiles.regex", CommandUtils.IDENTIFIER_ALLOWED), name, causeKey);
        }
    }

    @NotNull
    @Override
    public String toString() {
        if(!name.matches(VersionFeatureManager.getString("player_names.regex", CommandUtils.IDENTIFIER_ALLOWED))) {
            return CommandUtils.quote(name, false);
        } else {
            return name;
        }
    }

    @NotNull
    @Override
    public String scoreHolderToString() {
        if(!name.matches(VersionFeatureManager.getString("score_holders.regex", CommandUtils.IDENTIFIER_ALLOWED))) {
            return CommandUtils.quote(name, false);
        } else {
            return name;
        }
    }

    @NotNull
    @Override
    public String gameProfileToString() {
        if(!name.matches(VersionFeatureManager.getString("game_profiles.regex", CommandUtils.IDENTIFIER_ALLOWED))) {
            return CommandUtils.quote(name, false);
        } else {
            return name;
        }
    }

    @Override
    public @NotNull String selectorTextComponentToString() {
        return name;
    }

    @Override
    public @NotNull String scoreTextComponentToString() {
        return name;
    }

    @Override
    public int getLimit() {
        return 1;
    }

    @Override
    public Entity limitToOne() {
        return this.clone();
    }

    @Override
    public Entity clone() {
        return new PlayerName(this.name);
    }

    @Override
    public boolean isUnknownType() {
        return false;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }
}
