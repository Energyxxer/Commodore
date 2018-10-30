package com.energyxxer.commodore.loottables.items;

import com.energyxxer.commodore.loottables.LootEntry;
import com.energyxxer.commodore.types.Type;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.energyxxer.commodore.types.TypeAssert.assertItem;

public class LootItemEntry extends LootEntry {
    private Type name;
    private ArrayList<LootItemFunction> functions = new ArrayList<>();

    public LootItemEntry(Type item) {
        super("item");
        assertItem(item);
        this.name = item;
    }

    @Override
    public JsonObject construct() {
        JsonObject object = super.construct();
        object.addProperty("name", name.toString());
        if(!functions.isEmpty()) {
            JsonArray list = new JsonArray();
            object.add("functions", list);
            for(LootItemFunction function : functions) {
                list.add(function.construct());
            }
        }
        return object;
    }

    public void addFunction(LootItemFunction function) {
        functions.add(function);
    }

    public Collection<LootItemFunction> getFunctions() {
        return functions;
    }

    public void addAllFunctions(Collection<LootItemFunction> functions) {
        this.functions.addAll(functions);
    }

    public void clearFunction() {
        functions.clear();
    }
}
