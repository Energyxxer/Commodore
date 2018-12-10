package com.energyxxer.commodore.item;

import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.ItemType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.energyxxer.commodore.types.TypeAssert.assertItem;

/**
 * Defines an item of a specific type, complete with NBT data, which can be used with commands to give,
 * clear, replace and test.
 *
 * @see Type
 * @see ItemType
 * @see TagCompound
 * */
public class Item {
    /**
     * The {@link Type} of this item. Can be an item tag.
     * */
    @NotNull
    private final Type type;
    /**
     * The {@link TagCompound} NBT data for this item, if applicable.
     * */
    @Nullable
    private final TagCompound nbt;

    /**
     * Creates an Item of the specific {@link Type}.
     *
     * @param type The {@link Type} of the item. Can be an item tag.
     *
     * @throws IllegalArgumentException if the type specified isn't of type Item.
     * */
    public Item(@NotNull Type type) {
        this(type, null);
    }

    /**
     * Creates an Item of the specific {@link Type} and NBT content.
     *
     * @param type The {@link Type} of the item. Can be an item tag.
     * @param nbt The {@link TagCompound} that describes the NBT data of the item.
     *
     * @throws IllegalArgumentException if the type specified isn't of type Item.
     * */
    public Item(@NotNull Type type, @Nullable TagCompound nbt) {
        this.type = type;
        this.nbt = nbt;

        assertItem(type);
    }

    /**
     * Retrieves this item's type.
     *
     * @return The type of this item.
     * */
    @NotNull
    public Type getItemType() {
        return type;
    }

    /**
     * Retrieves this item's NBT content.
     *
     * @return The NBT content of this item.
     * */
    @Nullable
    public TagCompound getNBT() {
        return nbt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(type.toString());
        if(nbt != null) sb.append(nbt.toHeadlessString());
        return sb.toString();
    }
}
