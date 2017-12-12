package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.types.FunctionReference;

import java.util.ArrayList;

public class FunctionTag extends FunctionReference implements Tag {

    private ArrayList<FunctionReference> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;

    FunctionTag(String namespace, String id) {
        super(namespace, id);
    }

    @Override
    public ArrayList<FunctionReference> getValues() {
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
