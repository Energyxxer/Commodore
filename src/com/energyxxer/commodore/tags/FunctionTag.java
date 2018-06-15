package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.defaults.FunctionReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FunctionTag extends FunctionReference implements Tag {

    private final ArrayList<FunctionReference> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;

    public static final TagInstantiator<FunctionTag> INSTANTIATOR = FunctionTag::new;
    private boolean export = true;

    FunctionTag(Namespace namespace, String id) {
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
        this.policy = newPolicy;
    }

    // ADD METHODS

    public void addValue(FunctionReference value) {
        values.add(value);
    }

    public void addValues(Collection<FunctionReference> values) {
        this.values.addAll(values);
    }

    public void addValues(FunctionReference... values) {
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
        return "tags/functions/" + name + ".json";
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
