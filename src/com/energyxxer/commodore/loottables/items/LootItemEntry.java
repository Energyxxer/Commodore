package com.energyxxer.commodore.loottables.items;

import com.energyxxer.commodore.loottables.LootEntry;
import com.energyxxer.commodore.loottables.functions.LootFunctions;
import com.energyxxer.commodore.types.Type;
import com.google.gson.JsonObject;

import static com.energyxxer.commodore.types.TypeAssert.assertItem;

public class LootItemEntry extends LootEntry {
    private final Type name;
    public final LootFunctions functions = new LootFunctions();

    public LootItemEntry(Type item) {
        super("item");
        assertItem(item);
        this.name = item;
    }

    @Override
    public JsonObject construct() {
        JsonObject object = super.construct();
        object.addProperty("name", name.toString());
        functions.constructInto(object);
        return object;
    }
}
