package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Exportable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collection;

public interface Tag<T extends TagIncorporable> extends Exportable, TagIncorporable {

    enum OverridePolicy {
        REPLACE(true), APPEND(false);

        public final boolean valueBool;

        public static final OverridePolicy DEFAULT_POLICY = APPEND;

        OverridePolicy(boolean valueBool) {
            this.valueBool = valueBool;
        }

        public static OverridePolicy valueOf(boolean bool) {
            return bool ? REPLACE : APPEND;
        }
    }

    ArrayList<T> getValues();

    default String getContents() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject root = new JsonObject();
        JsonArray list = new JsonArray();
        root.add("values", list);

        for(T value : getValues()) {
            list.add(value.toString());
        }

        return gson.toJson(root);
    }

    String getName();

    OverridePolicy getOverridePolicy();

    void setOverridePolicy(OverridePolicy newPolicy);

    TagGroup<?> getGroup();

    <V extends TagIncorporable> void addValue(V value);

    default void addValues(Collection<T> values) {
        values.forEach(this::addValue);
    }

    @Override
    default String getExportPath() {
        return "tags/" + getGroup().getDirectoryName() + "/" + getName() + ".json";
    }
}
