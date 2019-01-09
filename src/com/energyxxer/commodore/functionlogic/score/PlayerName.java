package com.energyxxer.commodore.functionlogic.score;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * Implements a score holder that represents a player referred by name, which may or may not exist as a player entity.
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

    @NotNull
    @Override
    public String toString() {
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
