package com.energyxxer.commodore.block;

import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.types.IllegalTypeException;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.BlockType;

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
    private final Type type;
    /**
     * The {@link Blockstate} of this block, if applicable.
     * */
    private Blockstate state;
    /**
     * The {@link TagCompound} NBT data for this block, if applicable.
     * */
    private final TagCompound nbt;

    /**
     * Creates a Block of the specific {@link Type}.
     *
     * @param type The {@link Type} of the block. Can be a block tag.
     *
     * @throws IllegalArgumentException if the type specified isn't of type Block.
     * */
    public Block(Type type) {
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
    public Block(Type type, Blockstate state) {
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
    public Block(Type type, TagCompound nbt) {
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
    public Block(Type type, Blockstate state, TagCompound nbt) {
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
    public Type getBlockType() {
        return type;
    }

    /**
     * Retrieves this block's blockstate.
     *
     * @return The blockstate for this block.
     * */
    public Blockstate getBlockstate() {
        return state;
    }

    /**
     * Retrieves this block's NBT content.
     *
     * @return The NBT content of this block.
     * */
    public TagCompound getNBT() {
        return nbt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(type.toString());
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
}
