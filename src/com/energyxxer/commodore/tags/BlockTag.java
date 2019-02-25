package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.BlockType;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertBlock;

public class BlockTag extends Tag {

    public static final TagInstantiator<BlockTag> INSTANTIATOR = BlockTag::new;

    BlockTag(@NotNull TagGroup group, @NotNull Namespace namespace, @NotNull String id) {
        super(BlockType.CATEGORY, namespace, id, group);
    }

    // ADD METHODS

    @Override
    public void addValue(@NotNull Type value) {
        assertBlock(value);
        super.addValue(value);
    }
}
