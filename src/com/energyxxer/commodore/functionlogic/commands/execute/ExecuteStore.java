package com.energyxxer.commodore.functionlogic.commands.execute;

import org.jetbrains.annotations.NotNull;

public abstract class ExecuteStore implements ExecuteModifier {
    public enum StoreValue {
        RESULT, SUCCESS;

        public static final StoreValue DEFAULT = RESULT;
    }

    protected final StoreValue storeValue;

    public ExecuteStore() {
        this(StoreValue.DEFAULT);
    }

    public ExecuteStore(StoreValue storeValue) {
        this.storeValue = storeValue;
    }

    protected String getStarter() {
        return "store " + storeValue.toString().toLowerCase() + " ";
    }

    @NotNull
    public StoreValue getStoreValue() {
        return storeValue;
    }
}
