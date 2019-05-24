package com.energyxxer.commodore.versioning.compatibility;

import java.util.HashMap;

public class VersionFeatures {
    private HashMap<String, Object> map = new HashMap<>();

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        Object contained = map.get(key);
        return contained instanceof Integer ? ((int) contained) : defaultValue;
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Object contained = map.get(key);
        return contained instanceof Boolean ? ((boolean) contained) : defaultValue;
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        Object contained = map.get(key);
        return contained instanceof String ? ((String) contained) : defaultValue;
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public VersionFeatures duplicate() {
        VersionFeatures copy = new VersionFeatures();
        copy.map.putAll(this.map);
        return copy;
    }
}
