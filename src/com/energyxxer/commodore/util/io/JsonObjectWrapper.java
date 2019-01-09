package com.energyxxer.commodore.util.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public class JsonObjectWrapper {
    @NotNull
    public final JsonObject obj;

    public JsonObjectWrapper(@NotNull JsonObject obj) {
        this.obj = obj;
    }

    public boolean getAsBoolean(@NotNull String key, boolean def) {
        JsonElement elem = obj.get(key);
        return elem != null ? elem.getAsBoolean() : def;
    }

    public String getAsString(@NotNull String key) {
        return getAsString(key, null);
    }

    public String getAsString(@NotNull String key, String def) {
        JsonElement elem = obj.get(key);
        return elem != null ? elem.getAsString() : def;
    }

    public int getAsInt(@NotNull String key, int def) {
        JsonElement elem = obj.get(key);
        return elem != null ? elem.getAsInt() : def;
    }
}
