package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.types.BlockType;

import java.util.ArrayList;

public class BlockTag extends BlockType implements Tag {

    private ArrayList<BlockType> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;

    BlockTag(String namespace, String id) {
        super(namespace, id);
    }

    @Override
    public ArrayList<BlockType> getValues() {
        return values;
    }

    @Override
    public OverridePolicy getOverridePolicy() {
        return policy;
    }

    @Override
    public void setOverridePolicy(OverridePolicy newPolicy) {
        this.policy = policy;
    }

    @Override
    public boolean isConcrete() {
        return false;
    }

    @Override
    public String toString() {
        return "#" + super.toString();
    }
}
