package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.FunctionReference;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.energyxxer.commodore.types.TypeAssert.assertFunction;

public class FunctionTag extends Tag {

    public static final TagInstantiator<FunctionTag> INSTANTIATOR = FunctionTag::new;

    FunctionTag(@NotNull TagGroup group, @NotNull Namespace namespace, @NotNull String id) {
        super(FunctionReference.CATEGORY, namespace, id, group);
    }

    // ADD METHODS

    @Override
    public void addValue(@NotNull Type value) {
        assertFunction(value);
        super.addValue(value);
    }
}
