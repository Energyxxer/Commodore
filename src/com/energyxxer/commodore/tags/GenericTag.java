package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;

import java.util.ArrayList;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class GenericTag extends Tag {
    public static final TagInstantiator<GenericTag> INSTANTIATOR = GenericTag::new;
    private final ArrayList<Type> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;
    private boolean export = true;

    private TagGroup<?> group;

    GenericTag(TagGroup group, Namespace namespace, String id) {
        super(group.getCategory(), namespace, id);
        this.group = group;
    }

    @Override
    public void addValue(Type value) {
        assertType(value, group.getCategory());
        values.add(value);
    }

    @Override
    public ArrayList<Type> getValues() {
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

    @Override
    public boolean isStandalone() {
        return false;
    }

    @Override
    public boolean useNamespace() {
        return true;
    }
}
