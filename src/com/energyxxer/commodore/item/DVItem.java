package com.energyxxer.commodore.item;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DVItem extends Item {

    private int dataValue;
    private JsonObject components;

    public DVItem(@NotNull Type type) {
        this(type, -1);
    }

    public DVItem(@NotNull Type type, int dataValue) {
        this(type, dataValue, null);
    }

    public DVItem(@NotNull Type type, int dataValue, JsonObject components) {
        super(type);
        if (dataValue < -1) {
            throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "The given data value for this item is outside of the -1.. range: " + dataValue);
        }
        this.dataValue = dataValue;
        this.components = components;
    }

    public JsonObject getComponents() {
        return components;
    }

    public void setComponents(JsonObject components) {
        this.components = components;
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
        return this.getItemType().toSafeStringExcludeMinecraftNamespace() + (dataValue < 0 ? "" : " " + dataValue) + (components != null ? " " + components : "");
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("item.data_values");
    }

    public String asSet() {
        return getItemType().toSafeStringExcludeMinecraftNamespace() + " " + Math.max(dataValue, 0) + (components != null ? " " + components : "");
    }

    public String asSet(String embedded, boolean doNotEmbedIfLast) {
        if(embedded == null) return asSet();
        return getItemType().toSafeStringExcludeMinecraftNamespace() + " " + embedded + " " + Math.max(dataValue, 0) + (components != null ? " " + components : "");
    }

    public String asMatch() {
        return getItemType().toSafeStringExcludeMinecraftNamespace() + " " + dataValue;
    }

    public String asMatch(String embedded, boolean doNotEmbedIfLast) {
        if(embedded == null) return asSet();
        return getItemType().toSafeStringExcludeMinecraftNamespace() + " " + embedded + " " + dataValue;
    }
}
