package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.FluidType;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertFluid;

public class FluidTag extends Tag {

    public static final TagInstantiator<FluidTag> INSTANTIATOR = FluidTag::new;

    FluidTag(@NotNull TagGroup group, @NotNull Namespace namespace, @NotNull String id) {
        super(FluidType.CATEGORY, namespace, id, group);
    }

    // ADD METHODS

    @Override
    public void addValue(@NotNull Type value) {
        assertFluid(value);
        super.addValue(value);
    }
}
