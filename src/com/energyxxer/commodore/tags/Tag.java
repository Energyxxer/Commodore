package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Exportable;
import com.energyxxer.commodore.types.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public interface Tag<T extends Type> extends Exportable {

    enum OverridePolicy {
        REPLACE(true), APPEND(false);

        final boolean valueBool;

        public static final OverridePolicy DEFAULT_POLICY = APPEND;

        OverridePolicy(boolean valueBool) {
            this.valueBool = valueBool;
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
}
