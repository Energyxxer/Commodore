package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.FunctionReference;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FunctionTag extends Tag {

    private final ArrayList<Type> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;

    public static final TagInstantiator<FunctionTag> INSTANTIATOR = FunctionTag::new;
    private boolean export = true;

    @NotNull
    private TagGroup<?> group;

    FunctionTag(@NotNull TagGroup group, @NotNull Namespace namespace, @NotNull String id) {
        super(FunctionReference.CATEGORY, namespace, id);
        this.group = group;
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

    // ADD METHODS

    @Override
    public void addValue(@NotNull Type value) {
        if(value instanceof FunctionReference) values.add(value);
        else throw new ClassCastException("Value cannot be cast to FunctionReference");
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
