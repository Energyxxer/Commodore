package com.energyxxer.commodore.versioning.compatibility;

import com.energyxxer.commodore.versioning.Version;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class VersionFeatures {
    @NotNull
    private final String edition;
    @NotNull
    private final Version version;
    @NotNull
    private final HashMap<String, Object> map = new HashMap<>();

    public VersionFeatures(@NotNull String edition, Version version) {
        this.edition = edition;
        this.version = version;
    }

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

    @NotNull
    public String getEdition() {
        return edition;
    }

    @NotNull
    public Version getVersion() {
        return version;
    }

    public VersionFeatures duplicate() {
        VersionFeatures copy = new VersionFeatures(edition, version);
        copy.map.putAll(this.map);
        return copy;
    }

    public boolean matches(Version version) {
        if(!version.getEditionString().equalsIgnoreCase(this.getEdition())) return false;
        return this.version.compareIgnoreEdition(version) <= 0;
    }
}
