package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.energyxxer.commodore.types.TypeAssert.assertEntity;

public class EntityTypeTag extends Tag {

    public static final TagInstantiator<EntityTypeTag> INSTANTIATOR = EntityTypeTag::new;

    EntityTypeTag(@NotNull TagGroup group, @NotNull Namespace namespace, @NotNull String id) {
        super(EntityType.CATEGORY, namespace, id, group);
    }

    // ADD METHODS

    @Override
    public void addValue(@NotNull Type value) {
        assertEntity(value);
        super.addValue(value);
    }
}
