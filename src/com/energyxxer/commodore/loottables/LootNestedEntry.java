package com.energyxxer.commodore.loottables;

import com.google.gson.JsonObject;

public class LootNestedEntry extends LootEntry {
    private final String lootTableName;

    public LootNestedEntry(String lootTableName) {
        super("loot_table");
        this.lootTableName = lootTableName;
    }

    @Override
    public JsonObject construct() {
        JsonObject object = super.construct();
        object.addProperty("name", lootTableName);
        return object;
    }
}
