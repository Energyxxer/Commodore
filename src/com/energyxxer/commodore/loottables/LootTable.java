package com.energyxxer.commodore.loottables;

import com.energyxxer.commodore.loottables.functions.LootFunctions;
import com.energyxxer.commodore.module.Exportable;
import com.energyxxer.commodore.module.Namespace;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collection;

public class LootTable implements Exportable {
    private final LootTableManager parent;
    private final Namespace namespace;

    private final String path;

    private final ArrayList<Pool> pools = new ArrayList<>();

    private final LootFunctions functions = new LootFunctions();

    private boolean export = true;

    public LootTable(LootTableManager parent, String path) {
        this.parent = parent;
        this.namespace = parent.getNamespace();
        this.path = path;
    }

    public String getFullName() {
        return namespace.toString() + ':' + path;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public String getPath() {
        return path;
    }

    public void addPool(Pool pool) {
        pools.add(pool);
    }

    public Collection<Pool> getPools() {
        return pools;
    }

    public void addAllPools(Collection<Pool> pools) {
        this.pools.addAll(pools);
    }

    public void clearPools() {
        this.pools.clear();
    }

    @Override
    public boolean shouldExport() {
        return export;
    }

    @Override
    public void setExport(boolean export) {
        this.export = export;
    }

    @Override
    public String getExportPath() {
        return "loot_tables/" + getPath() + ".json";
    }

    @Override
    public byte[] getContents() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject root = new JsonObject();
        JsonArray poolList = new JsonArray();
        root.add("pools", poolList);

        for(Pool pool : pools) {
            poolList.add(pool.construct());
        }

        functions.constructInto(root);

        return gson.toJson(root).getBytes();
    }
}
