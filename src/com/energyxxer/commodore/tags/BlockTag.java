package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.defaults.BlockType;

import java.util.ArrayList;

public class BlockTag extends BlockType implements Tag<BlockType> {

    public static final TagInstantiator<BlockTag> INSTANTIATOR = BlockTag::new;
    private final ArrayList<BlockType> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;
    private boolean export = true;

    private TagGroup<?> group;

    BlockTag(TagGroup group, Namespace namespace, String id) {
        super(namespace, id);
        this.group = group;
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
        this.policy = newPolicy;
    }

    // ADD METHODS

    @Override
    public void addValue(TagIncorporable value) {
        if(value instanceof BlockType) values.add((BlockType) value);
        else throw new ClassCastException("Value cannot be cast to BlockType");
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
