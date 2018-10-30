package com.energyxxer.commodore.loottables;

import com.google.gson.JsonObject;

public abstract class LootEntry {
    private String type;
    private int weight = 1;
    private int quality = 0;

    public LootEntry(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public JsonObject construct() {
        JsonObject object = new JsonObject();
        object.addProperty("type", type);
        object.addProperty("weight", weight);
        if(quality != 0) {
            object.addProperty("weight", weight);
        }

        return object;
    }
}
