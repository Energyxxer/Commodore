package com.energyxxer.commodore.loottables.conditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class LootConditions {
    private final ArrayList<LootCondition> conditions = new ArrayList<>();

    public void constructInto(JsonObject obj) {
        if(!conditions.isEmpty()) {
            JsonArray list = new JsonArray();
            obj.add("conditions", list);
            for(LootCondition condition : conditions){
                list.add(condition.construct());
            }
        }
    }
}
