package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.types.ItemType;

import java.util.ArrayList;

public class ItemTag extends ItemType implements Tag {

    private ArrayList<ItemType> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;

    ItemTag(String namespace, String id) {
        super(namespace, id);
    }

    @Override
    public ArrayList<ItemType> getValues() {
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
