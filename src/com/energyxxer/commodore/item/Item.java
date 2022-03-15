package com.energyxxer.commodore.item;

import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.ItemType;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

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
    private Type type;
    /**
     * The {@link TagCompound} NBT data for this item, if applicable.
     * */
    @Nullable
    private TagCompound nbt;

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

    /**
     * Sets this item's type.
     *
     * @param type The new {@link Type} to assign to this item.
     * */
    public void setItemType(@NotNull Type type) {
        assertItem(type);
        this.type = type;
    }

    /**
     * Sets this item's NBT.
     *
     * @param nbt The new {@link TagCompound} to assign to this item's NBT.
     * */
    public void setNbt(@Nullable TagCompound nbt) {
        this.nbt = nbt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(type.toSafeString());
        if(nbt != null) sb.append(nbt.toHeadlessString());
        return sb.toString();
    }

    public void assertAvailable() {
        if (nbt != null) VersionFeatureManager.assertEnabled("nbt.access");
    }

    public String asSet() {
        return toString();
    }

    public String asSet(String embedded, boolean doNotEmbedIfLast) {
        if(embedded == null) return asSet();
        if(doNotEmbedIfLast) return toString();
        else return toString() + " " + embedded;
    }

    public String asMatch() {
        return toString();
    }

    public String asMatch(String embedded, boolean doNotEmbedIfLast) {
        if(embedded == null) return asSet();
        if(doNotEmbedIfLast) return toString();
        else return toString() + " " + embedded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return type.equals(item.type) &&
                Objects.equals(nbt, item.nbt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, nbt);
    }
}
