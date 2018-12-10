package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class GenericTag extends Tag {
    public static final TagInstantiator<GenericTag> INSTANTIATOR = GenericTag::new;
    private final ArrayList<Type> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;
    private boolean export = true;

    @NotNull
    private TagGroup<?> group;

    GenericTag(@NotNull TagGroup group, @NotNull Namespace namespace, @NotNull String id) {
        super(group.getCategory(), namespace, id);
        this.group = group;
    }

    @Override
    public void addValue(@NotNull Type value) {
        assertType(value, group.getCategory());
        values.add(value);
    }

    @NotNull
    @Override
    public ArrayList<Type> getValues() {
        return values;
    }

    @NotNull
    @Override
    public OverridePolicy getOverridePolicy() {
        return policy;
    }

    @Override
    public void setOverridePolicy(@NotNull OverridePolicy newPolicy) {
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

    @NotNull
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
