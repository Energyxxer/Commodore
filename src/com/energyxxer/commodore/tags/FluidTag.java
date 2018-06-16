package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.defaults.FluidType;

import java.util.ArrayList;

public class FluidTag extends FluidType implements Tag<FluidType> {

    public static final TagInstantiator<FluidTag> INSTANTIATOR = FluidTag::new;
    private final ArrayList<FluidType> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;
    private boolean export = true;

    private TagGroup<?> group;

    FluidTag(TagGroup group, Namespace namespace, String id) {
        super(namespace, id);
        this.group = group;
    }

    @Override
    public ArrayList<FluidType> getValues() {
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
        if(value instanceof FluidType) values.add((FluidType) value);
        else throw new ClassCastException("Value cannot be cast to FluidType");
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
