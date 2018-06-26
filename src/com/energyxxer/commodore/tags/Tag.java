package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Exportable;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Tag extends Type implements Exportable {

    public enum OverridePolicy {
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

    public Tag(String category, Namespace namespace, String name) {
        super(category, namespace, name);
    }

    public abstract ArrayList<Type> getValues();

    public abstract OverridePolicy getOverridePolicy();

    public abstract void setOverridePolicy(OverridePolicy newPolicy);

    public abstract TagGroup<?> getGroup();

    public abstract void addValue(Type value);

    public String getContents() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject root = new JsonObject();
        JsonArray list = new JsonArray();
        root.add("values", list);

        for(Type value : getValues()) {
            list.add(value.toString());
        }

        return gson.toJson(root);
    }

    public void addValues(Collection<Type> values) {
        values.forEach(this::addValue);
    }

    @Override
    public String getExportPath() {
        return "tags/" + getGroup().getDirectoryName() + "/" + getName() + ".json";
    }
}
