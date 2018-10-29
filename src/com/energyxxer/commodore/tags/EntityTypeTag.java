package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.EntityType;

import java.util.ArrayList;

import static com.energyxxer.commodore.types.TypeAssert.assertEntity;

public class EntityTypeTag extends Tag {

    public static final TagInstantiator<EntityTypeTag> INSTANTIATOR = EntityTypeTag::new;
    private final ArrayList<Type> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;
    private boolean export = true;

    private TagGroup<?> group;

    EntityTypeTag(TagGroup group, Namespace namespace, String id) {
        super(EntityType.CATEGORY, namespace, id);
        this.group = group;
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

    // ADD METHODS

    @Override
    public void addValue(Type value) {
        assertEntity(value);
        values.add(value);
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
    public boolean isStandalone() {
        return false;
    }

    @Override
    public String toString() {
        return "#" + super.toString();
    }
}
