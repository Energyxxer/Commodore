package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.commands.data.DataHolder;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.NumericNBTType;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class ExecuteStoreDataHolder extends ExecuteStore {
    @NotNull
    private final DataHolder holder;
    @NotNull
    private final NBTPath path;
    @NotNull
    private final NumericNBTType type;
    private final double scale;

    public ExecuteStoreDataHolder(@NotNull DataHolder holder, @NotNull NBTPath path, @NotNull NumericNBTType type) {
        this(holder, path, type, 1);
    }

    public ExecuteStoreDataHolder(@NotNull DataHolder holder, @NotNull NBTPath path, @NotNull NumericNBTType type, double scale) {
        this(StoreValue.DEFAULT, holder, path, type, scale);
    }

    public ExecuteStoreDataHolder(StoreValue storeValue, @NotNull DataHolder holder, @NotNull NBTPath path, @NotNull NumericNBTType type) {
        this(storeValue, holder, path, type, 1);
    }

    public ExecuteStoreDataHolder(StoreValue storeValue, @NotNull DataHolder holder, @NotNull NBTPath path, @NotNull NumericNBTType type, double scale) {
        super(storeValue);
        this.holder = holder;
        this.path = path;
        this.type = type;
        this.scale = scale;

        holder.assertSingle();
        assertFinite(scale, "scale");
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + holder + " " + path + " " + type.toString().toLowerCase(Locale.ENGLISH) + " " + CommandUtils.numberToPlainString(scale));
    }

    @Override
    public void assertAvailable() {
        holder.assertAvailable();
    }

    @Override
    public boolean isIdempotent() {
        return true;
    }

    @Override
    public boolean isSignificant() {
        return true;
    }

    @Override
    public boolean isAbsolute() {
        return true;
    }

    @NotNull
    public DataHolder getHolder() {
        return holder;
    }

    @NotNull
    public NBTPath getPath() {
        return path;
    }

    @NotNull
    public NumericNBTType getType() {
        return type;
    }

    public double getScale() {
        return scale;
    }
}
