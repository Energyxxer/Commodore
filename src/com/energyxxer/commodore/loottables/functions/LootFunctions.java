package com.energyxxer.commodore.loottables.functions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collection;

public class LootFunctions {
    private ArrayList<LootFunction> functions = new ArrayList<>();

    public void add(LootFunction function) {
        functions.add(function);
    }

    public Collection<LootFunction> getFunctions() {
        return functions;
    }

    public void addAll(Collection<LootFunction> functions) {
        this.functions.addAll(functions);
    }

    public void clearFunction() {
        functions.clear();
    }

    public void constructInto(JsonObject object) {
        if(!functions.isEmpty()) {
            JsonArray list = new JsonArray();
            object.add("functions", list);
            for(LootFunction function : functions) {
                list.add(function.construct());
            }
        }
    }
}
