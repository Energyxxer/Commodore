package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.defaults.BlockType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class BlockTag extends BlockType implements Tag {

    public static final TagInstantiator<BlockTag> INSTANTIATOR = BlockTag::new;
    private final ArrayList<BlockType> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;
    private boolean export = true;

    BlockTag(Namespace namespace, String id) {
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
        this.policy = newPolicy;
    }

    // ADD METHODS

    public void addValue(BlockType value) {
        values.add(value);
    }

    public void addValues(Collection<BlockType> values) {
        this.values.addAll(values);
    }

    public void addValues(BlockType... values) {
        this.addValues(Arrays.asList(values));
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
    public String getExportPath() {
        return "tags/blocks/" + name + ".json";
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
