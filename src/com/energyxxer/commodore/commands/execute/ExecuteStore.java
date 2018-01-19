package com.energyxxer.commodore.commands.execute;

public abstract class ExecuteStore implements ExecuteModifier {
    public enum StoreValue {
        RESULT, SUCCESS;

        public static StoreValue getDefault() {
            return RESULT;
        }
    }

    protected StoreValue storeValue;

    public ExecuteStore() {
        this(StoreValue.getDefault());
    }

    public ExecuteStore(StoreValue storeValue) {
        this.storeValue = storeValue;
    }

    protected String getStarter() {
        return "store " + storeValue.toString().toLowerCase() + " ";
    }
}
