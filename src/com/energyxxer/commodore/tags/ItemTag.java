package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.ItemType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.energyxxer.commodore.types.TypeAssert.assertItem;

public class ItemTag extends Tag {

    private final ArrayList<Type> values = new ArrayList<>();
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;

    public static final TagInstantiator<ItemTag> INSTANTIATOR = ItemTag::new;
    private boolean export = true;

    @NotNull
    private TagGroup group;

    ItemTag(@NotNull TagGroup group, @NotNull Namespace namespace, @NotNull String id) {
        super(ItemType.CATEGORY, namespace, id);
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
        assertItem(value);
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
