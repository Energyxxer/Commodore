package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.ItemType;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertItem;

public class ItemTag extends Tag {

    public static final TagInstantiator<ItemTag> INSTANTIATOR = ItemTag::new;

    ItemTag(@NotNull TagGroup group, @NotNull Namespace namespace, @NotNull String id) {
        super(ItemType.CATEGORY, namespace, id, group);
    }

    // ADD METHODS

    @Override
    public void addValue(@NotNull Type value) {
        assertItem(value);
        super.addValue(value);
    }
}
