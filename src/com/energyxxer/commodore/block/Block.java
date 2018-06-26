package com.energyxxer.commodore.block;

import com.energyxxer.commodore.nbt.TagCompound;
import com.energyxxer.commodore.types.Type;

import java.util.Objects;

import static com.energyxxer.commodore.types.TypeAssert.assertBlock;

public class Block {
    private final Type type;
    private Blockstate state;
    private final TagCompound nbt;

    public Block(Type type) {
        this(type, null, null);
    }

    public Block(Type type, Blockstate state) {
        this(type, state, null);
    }

    public Block(Type type, TagCompound nbt) {
        this(type, null, nbt);
    }

    public Block(Type type, Blockstate state, TagCompound nbt) {
        assertBlock(type);
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
