package com.energyxxer.mctech.block;

import com.energyxxer.mctech.nbt.TagCompound;
import com.energyxxer.mctech.types.BlockType;

public class Block {
    private BlockType type;
    private Blockstate state;
    private TagCompound nbt;

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
}
