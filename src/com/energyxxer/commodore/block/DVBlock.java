package com.energyxxer.commodore.block;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DVBlock extends Block {

    private int dataValue;

    public DVBlock(@NotNull Type type) {
        this(type, -1);
    }

    public DVBlock(@NotNull Type type, int dataValue) {
        super(type);
        if (dataValue < -1 || dataValue > 15) {
            throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "The given data value for this block is outside of the -1..15 range: " + dataValue);
        }
        this.dataValue = dataValue;
    }

    /**
     * DVBlock objects don't support blockstates.
     *
     * @throws UnsupportedOperationException DVBlock objects don't support blockstates.
     */
    @Override
    @Deprecated
    public void setBlockstate(@Nullable Blockstate state) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * DVBlock objects don't support NBT.
     *
     * @throws UnsupportedOperationException DVBlock objects don't support NBT.
     */
    @Override
    @Deprecated
    public void setNbt(@Nullable TagCompound nbt) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieves this block's data value, or -1 if unspecified
     *
     * @return This block's data value
     */
    public int getDataValue() {
        return dataValue;
    }

    /**
     * Sets this block's data value
     *
     * @param dataValue This block's new data value, or -1 if unspecified
     */
    public void setDataValue(int dataValue) {
        this.dataValue = dataValue;
    }

    @Override
    public String toString() {
        return this.getBlockType().toSafeStringExcludeMinecraftNamespace() + (dataValue < 0 ? "" : " " + dataValue);
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("block.data_values");
    }
}
