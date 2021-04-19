package com.energyxxer.commodore.item;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DVItem extends Item {

    private int dataValue;

    public DVItem(@NotNull Type type) {
        this(type, -1);
    }

    public DVItem(@NotNull Type type, int dataValue) {
        super(type);
        if (dataValue < -1) {
            throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "The given data value for this item is outside of the -1.. range: " + dataValue);
        }
        this.dataValue = dataValue;
    }

    /**
     * DVItem objects don't support NBT.
     *
     * @throws UnsupportedOperationException DVItem objects don't support NBT.
     */
    @Override
    @Deprecated
    public void setNbt(@Nullable TagCompound nbt) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieves this item's data value, or -1 if unspecified
     *
     * @return This item's data value
     */
    public int getDataValue() {
        return dataValue;
    }

    /**
     * Sets this item's data value
     *
     * @param dataValue This item's new data value, or -1 if unspecified
     */
    public void setDataValue(int dataValue) {
        this.dataValue = dataValue;
    }

    @Override
    public String toString() {
        return this.getItemType().toSafeStringExcludeMinecraftNamespace() + (dataValue < 0 ? "" : " " + dataValue);
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("item.data_values");
    }
}
