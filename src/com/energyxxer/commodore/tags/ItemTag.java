package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.defaults.ItemType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ItemTag extends ItemType implements Tag {

    private final ArrayList<ItemType> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;

    public static final TagInstantiator<ItemTag> INSTANTIATOR = ItemTag::new;

    ItemTag(Namespace namespace, String id) {
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
        this.policy = newPolicy;
    }

    // ADD METHODS

    public void addValue(ItemType value) {
        values.add(value);
    }

    public void addValues(Collection<ItemType> values) {
        this.values.addAll(values);
    }

    public void addValues(ItemType... values) {
        this.addValues(Arrays.asList(values));
    }

    //----------

    @Override
    public boolean isConcrete() {
        return false;
    }

    @Override
    public String toString() {
        return "#" + super.toString();
    }
}
