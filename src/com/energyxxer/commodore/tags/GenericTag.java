package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class GenericTag extends Tag {

    public static final TagInstantiator<GenericTag> INSTANTIATOR = GenericTag::new;

    GenericTag(@NotNull TagGroup group, @NotNull Namespace namespace, @NotNull String id) {
        super(group.getCategory(), namespace, id, group);
    }

    @Override
    public void addValue(@NotNull Type value) {
        assertType(value, group.getCategory());
        super.addValue(value);
    }

    @Override
    public boolean useNamespace() {
        return true;
    }
}
