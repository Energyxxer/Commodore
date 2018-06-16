package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.defaults.ItemType;

import java.util.ArrayList;

public class ItemTag extends ItemType implements Tag<ItemType> {

    private final ArrayList<ItemType> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;

    public static final TagInstantiator<ItemTag> INSTANTIATOR = ItemTag::new;
    private boolean export = true;

    private TagGroup group;

    ItemTag(TagGroup group, Namespace namespace, String id) {
        super(namespace, id);
        this.group = group;
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

    @Override
    public void addValue(TagIncorporable value) {
        if(value instanceof ItemType) values.add((ItemType) value);
        else throw new ClassCastException("Value cannot be cast to ItemType");
    }

    @Override
    public boolean shouldExport() {
        return export;
    }

    @Override
    public void setExport(boolean export) {
        this.export = export;
    }

    @Override
    public TagGroup<?> getGroup() {
        return group;
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
