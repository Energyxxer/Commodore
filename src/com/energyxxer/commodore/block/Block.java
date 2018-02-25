package com.energyxxer.commodore.block;

import com.energyxxer.commodore.nbt.TagCompound;
import com.energyxxer.commodore.types.BlockType;

import java.util.Objects;

public class Block {
    private final BlockType type;
    private Blockstate state;
    private final TagCompound nbt;

    public Block(BlockType type) {
        this(type, null, null);
    }

    public Block(BlockType type, Blockstate state) {
        this(type, state, null);
    }

    public Block(BlockType type, TagCompound nbt) {
        this(type, null, nbt);
    }

    public Block(BlockType type, Blockstate state, TagCompound nbt) {
        this.type = type;
        if(state != null) this.state = state;
        this.nbt = nbt;
    }

    public boolean isConcrete() {
        return type.isConcrete();
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
