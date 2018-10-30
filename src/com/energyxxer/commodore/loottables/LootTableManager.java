package com.energyxxer.commodore.loottables;

import com.energyxxer.commodore.module.Namespace;

import java.util.Collection;
import java.util.HashMap;

public class LootTableManager {

    private final Namespace namespace;

    private final HashMap<String, LootTable> lootTables = new HashMap<>();

    public LootTableManager(Namespace namespace) {
        this.namespace = namespace;
    }

    public LootTable get(String tableName) {
        return lootTables.get(tableName.toLowerCase());
    }

    public boolean contains(String tableName) {
        return lootTables.containsKey(tableName.toLowerCase());
    }

    public LootTable create(String tableName) {
        return create(tableName, false);
    }

    public LootTable create(String tableName, boolean force) {
        tableName = tableName.toLowerCase();

        if(force || !contains(tableName)) {
            LootTable table = new LootTable(this, tableName);
            lootTables.put(tableName, table);

            return table;
        } else throw new IllegalArgumentException("A loot table by the name '" + tableName + "' already exists");
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public Collection<LootTable> getAll() {
        return lootTables.values();
    }
}
