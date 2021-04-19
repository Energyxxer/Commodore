package com.energyxxer.commodore.block;

import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.types.IllegalTypeException;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.BlockType;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static com.energyxxer.commodore.types.TypeAssert.assertBlock;

/**
 * Defines a block of a specific type, complete with blockstates and NBT data, which can be used with commands to place,
 * fill, and test.
 *
 * @see Type
 * @see BlockType
 * @see Blockstate
 * @see TagCompound
 * */
public class Block {
    /**
     * The {@link Type} of this block. Can be a block tag.
     * */
    @NotNull
    private Type type;
    /**
     * The {@link Blockstate} of this block, if applicable.
     * */
    @Nullable
    private Blockstate state;
    /**
     * The {@link TagCompound} NBT data for this block, if applicable.
     * */
    @Nullable
    private TagCompound nbt;

    /**
     * Creates a Block of the specific {@link Type}.
     *
     * @param type The {@link Type} of the block. Can be a block tag.
     *
     * @throws IllegalArgumentException if the type specified isn't of type Block.
     * */
    public Block(@NotNull Type type) {
        this(type, null, null);
    }

    /**
     * Creates a Block of the specific {@link Type} and blockstate.
     *
     * @param type The {@link Type} of the block. Can be a block tag.
     * @param state The {@link Blockstate} state of the block.
     *
     * @throws IllegalTypeException if the type specified isn't of type Block.
     * */
    public Block(@NotNull Type type, @Nullable Blockstate state) {
        this(type, state, null);
    }

    /**
     * Creates a Block of the specific {@link Type} and NBT content.
     *
     * @param type The {@link Type} of the block. Can be a block tag.
     * @param nbt The {@link TagCompound} that describes the NBT data of the block.
     *
     * @throws IllegalTypeException if the type specified isn't of type Block.
     * */
    public Block(@NotNull Type type, @Nullable TagCompound nbt) {
        this(type, null, nbt);
    }

    /**
     * Creates a Block of the specific {@link Type}, blockstate and NBT content.
     *
     * @param type The {@link Type} of the block. Can be a block tag.
     * @param state The {@link Blockstate} state of the block.
     * @param nbt The {@link TagCompound} that describes the NBT data of the block.
     *
     * @throws IllegalTypeException if the type specified isn't of type Block.
     * */
    public Block(@NotNull Type type, @Nullable Blockstate state, @Nullable TagCompound nbt) {
        assertBlock(type);
        this.type = type;
        if(state != null) this.state = state;
        this.nbt = nbt;
    }

    /**
     * Retrieves this block's type.
     *
     * @return The type of this block.
     * */
    public @NotNull Type getBlockType() {
        return type;
    }

    /**
     * Retrieves this block's blockstate.
     *
     * @return The blockstate for this block.
     * */
    public @Nullable Blockstate getBlockstate() {
        return state;
    }

    /**
     * Retrieves this block's NBT content.
     *
     * @return The NBT content of this block.
     * */
    public @Nullable TagCompound getNBT() {
        return nbt;
    }

    /**
     * Sets this block's type.
     *
     * @param type The new {@link Type} to assign to this block.
     * */
    public void setBlockType(@NotNull Type type) {
        assertBlock(type);
        this.type = type;
    }

    /**
     * Sets this block's blockstate.
     *
     * @param state The new {@link Blockstate} for this block.
     * */
    public void setBlockstate(@Nullable Blockstate state) {
        this.state = state;
    }

    /**
     * Sets this block's NBT.
     *
     * @param nbt The new {@link TagCompound} to assign to this block's NBT.
     * */
    public void setNbt(@Nullable TagCompound nbt) {
        this.nbt = nbt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(type.toSafeString());
        if(state != null) {
            sb.append('[');
            sb.append(state);
            sb.append(']');
        }
        if(nbt != null) sb.append(nbt.toHeadlessString());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(type, block.type) &&
                Objects.equals(state, block.state) &&
                Objects.equals(nbt, block.nbt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, state, nbt);
    }

    public void assertAvailable() {
        if (nbt != null) VersionFeatureManager.assertEnabled("nbt.access");
        if (state != null) VersionFeatureManager.assertEnabled("block.blockstates");
    }
}
