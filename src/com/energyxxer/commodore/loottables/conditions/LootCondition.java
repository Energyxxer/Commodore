package com.energyxxer.commodore.loottables.conditions;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.util.NumberRange;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class LootCondition {
    private String type;

    public LootCondition(String type) {
        this.type = type;
    }

    public JsonObject construct() {
        JsonObject object = new JsonObject();
        object.addProperty("condition", type);
        return object;
    }

    public static class RandomChance extends LootCondition {

        private double chance;

        public RandomChance(double chance) {
            super("random_chance");
            this.chance = chance;
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            object.addProperty("chance", chance);
            return object;
        }

        //region Accessors
        public double getChance() {
            return chance;
        }

        public void setChance(double chance) {
            this.chance = chance;
        }
        //endregion
    }

    public static class RandomChanceWithLooting extends LootCondition {

        private double chance;
        private double lootingMultiplier;

        public RandomChanceWithLooting(double chance, double lootingMultiplier) {
            super("random_chance_with_looting");
            this.chance = chance;
            this.lootingMultiplier = lootingMultiplier;
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            object.addProperty("chance", chance);
            object.addProperty("looting_multiplier", lootingMultiplier);
            return object;
        }

        //region Accessors
        public double getChance() {
            return chance;
        }

        public void setChance(double chance) {
            this.chance = chance;
        }

        public double getLootingMultiplier() {
            return lootingMultiplier;
        }

        public void setLootingMultiplier(double lootingMultiplier) {
            this.lootingMultiplier = lootingMultiplier;
        }
        //endregion
    }

    public static class KilledByPlayer extends LootCondition {
        private boolean inverse = false;

        public KilledByPlayer() {
            this(false);
        }

        public KilledByPlayer(boolean inverse) {
            super("killed_by_player");
            this.inverse = inverse;
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            object.addProperty("inverse", inverse);
            return object;
        }

        //region Accessors
        public boolean isInverse() {
            return inverse;
        }

        public void setInverse(boolean inverse) {
            this.inverse = inverse;
        }
        //endregion
    }

    public static class EntityProperties extends LootCondition {

        public enum EntityReference {
            THIS, KILLER, KILLER_PLAYER, DIRECT_KILLER_ENTITY
        }

        public enum Property {
            ON_FIRE
        }

        private EntityReference entity;
        private HashMap<Property, Boolean> properties = new HashMap<>();

        public EntityProperties(@NotNull EntityReference entity) {
            super("entity_properties");
            this.entity = entity;
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            object.addProperty("entity", entity.toString().toLowerCase());
            if(!properties.isEmpty()) {
                JsonObject list = new JsonObject();
                object.add("properties", list);
                for(Map.Entry<Property, Boolean> pair : properties.entrySet()) {
                    list.addProperty(pair.getKey().toString().toLowerCase(), pair.getValue());
                }
            }
            return object;
        }

        //region Accessors
        public EntityReference getEntity() {
            return entity;
        }

        public void setEntity(EntityReference entity) {
            this.entity = entity;
        }

        public void setProperty(Property property, boolean value) {
            properties.put(property, value);
        }

        public void unsetProperty(Property property) {
            properties.remove(property);
        }

        public void clearProperties() {
            properties.clear();
        }

        public Boolean getProperty(Property property) {
            return properties.get(property);
        }
        //endregion
    }

    public static class EntityScores extends LootCondition {

        private EntityProperties.EntityReference entity;
        private HashMap<String, NumberRange<Integer>> scores = new HashMap<>();

        public EntityScores(@NotNull LootCondition.EntityProperties.EntityReference entity) {
            super("entity_scores");
            this.entity = entity;
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            object.addProperty("entity", entity.toString().toLowerCase());
            if(!scores.isEmpty()) {
                JsonObject list = new JsonObject();
                object.add("scores", list);
                for(Map.Entry<String, NumberRange<Integer>> pair : scores.entrySet()) {
                    list.add(pair.getKey(), CommandUtils.constructRange(pair.getValue().getMin(), pair.getValue().getMax()));
                }
            }
            return object;
        }

        //region Accessors
        public EntityProperties.EntityReference getEntity() {
            return entity;
        }

        public void setEntity(EntityProperties.EntityReference entity) {
            this.entity = entity;
        }

        public void setScore(String objective, int value) {
            this.setScore(objective, value, value);
        }

        public void setScore(String objective, int min, int max) {
            this.setScore(objective, new NumberRange<>(min, max));
        }

        public void setScore(String objective, NumberRange<Integer> range) {
            scores.put(objective, range);
        }

        public void unsetScore(String objective) {
            scores.remove(objective);
        }

        public void clearProperties() {
            scores.clear();
        }

        public NumberRange<Integer> getScore(String objective) {
            return scores.get(objective);
        }
        //endregion
    }
}
