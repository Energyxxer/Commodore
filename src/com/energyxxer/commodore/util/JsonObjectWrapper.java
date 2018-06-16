package com.energyxxer.commodore.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonObjectWrapper {
    public JsonObject obj;

    public JsonObjectWrapper(JsonObject obj) {
        this.obj = obj;
    }

    public boolean getAsBoolean(String key, boolean def) {
        JsonElement elem = obj.get(key);
        return elem != null ? elem.getAsBoolean() : def;
    }

    public String getAsString(String key) {
        return getAsString(key, null);
    }

    public String getAsString(String key, String def) {
        JsonElement elem = obj.get(key);
        return elem != null ? elem.getAsString() : def;
    }

    public int getAsInt(String key, int def) {
        JsonElement elem = obj.get(key);
        return elem != null ? elem.getAsInt() : def;
    }
}
