package com.energyxxer.commodore.functionlogic.nbt;

import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.StorageTarget;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class DataHolderStorage implements DataHolder {
    @NotNull
    private Type target;

    public DataHolderStorage(@NotNull Type target) {
        this.target = target;

        assertType(target, StorageTarget.CATEGORY);
    }

    @NotNull
    @Override
    public String resolve() {
        return "storage " + target;
    }

    @Override
    public void assertSingle() {
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.data.storage");
    }

    @Override
    public @NotNull String getTextComponentKey() {
        return "storage";
    }

    @Override
    public @NotNull String getTextComponentValue() {
        return target.toString();
    }
}
