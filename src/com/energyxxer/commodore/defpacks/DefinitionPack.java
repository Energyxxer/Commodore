package com.energyxxer.commodore.defpacks;

import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.types.TypeDictionary;
import com.energyxxer.commodore.types.TypeManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.energyxxer.commodore.defpacks.DefinitionPack.DefinitionCategory.*;

public class DefinitionPack {

    public enum DefinitionCategory {
        BLOCK((m) -> m.block),
        FLUID((m) -> m.fluid),
        ITEM((m) -> m.item),
        EFFECT((m) -> m.effect),
        ENTITY((m) -> m.entity),
        PARTICLE((m) -> m.particle),
        ENCHANTMENT((m) -> m.enchantment),
        DIMENSION((m) -> m.dimension),
        BIOME((m) -> m.biome),
        DIFFICULTY((m) -> m.difficulty),
        GAMEMODE((m) -> m.gamemode),
        GAMERULE((m) -> m.gamerule),
        STRUCTURE((m) -> m.structure),
        SLOT((m) -> m.slot);

        private final DictionaryPicker picker;

        DefinitionCategory(DictionaryPicker picker) {
            this.picker = picker;
        }
    }

    private final String packName;
    private final String packDir;

    private final HashMap<DefinitionCategory, ArrayList<DefinitionBlueprint>> definitions = new HashMap<>();

    private boolean loaded = false;

    public DefinitionPack(String packName, String packDir) {
        this.packName = packName;
        this.packDir = packDir;

        definitions.put(BLOCK, new ArrayList<>());
        definitions.put(FLUID, new ArrayList<>());
        definitions.put(ITEM, new ArrayList<>());
        definitions.put(EFFECT, new ArrayList<>());
        definitions.put(ENTITY, new ArrayList<>());
        definitions.put(PARTICLE, new ArrayList<>());
        definitions.put(ENCHANTMENT, new ArrayList<>());
        definitions.put(DIMENSION, new ArrayList<>());
        definitions.put(BIOME, new ArrayList<>());
        definitions.put(DIFFICULTY, new ArrayList<>());
        definitions.put(GAMEMODE, new ArrayList<>());
        definitions.put(GAMERULE, new ArrayList<>());
        definitions.put(STRUCTURE, new ArrayList<>());
        definitions.put(SLOT, new ArrayList<>());
    }

    public void load() {
        if(loaded) return;

        Gson gson = new Gson();

        JsonObject blocks = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/blocks.json")), JsonObject.class);
        JsonObject fluids = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/fluids.json")), JsonObject.class);
        JsonObject items = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/items.json")), JsonObject.class);
        JsonObject effects = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/effects.json")), JsonObject.class);
        JsonObject entities = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/entities.json")), JsonObject.class);
        JsonObject particles = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/particles.json")), JsonObject.class);
        JsonObject enchantments = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/enchantments.json")), JsonObject.class);
        JsonObject dimensions = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/dimensions.json")), JsonObject.class);
        JsonObject biomes = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/biomes.json")), JsonObject.class);

        JsonObject difficulties = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/difficulties.json")), JsonObject.class);
        JsonObject gamemodes = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/gamemodes.json")), JsonObject.class);
        JsonObject gamerules = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/gamerules.json")), JsonObject.class);
        JsonObject structures = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/structures.json")), JsonObject.class);

        JsonObject slots = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/slots.json")), JsonObject.class);

        DefinitionCategory[] categories = {BLOCK, FLUID, ITEM, EFFECT, ENTITY, PARTICLE, ENCHANTMENT, DIMENSION, BIOME, DIFFICULTY, GAMEMODE, GAMERULE, STRUCTURE, SLOT};
        JsonObject[] objects = {blocks, fluids, items, effects, entities, particles, enchantments, dimensions, biomes, difficulties, gamemodes, gamerules, structures, slots};

        for(int i = 0; i < categories.length; i++) {
            for(Map.Entry<String, JsonElement> entry : objects[i].entrySet()) {
                String name = entry.getKey();
                HashMap<String, String> properties = new HashMap<>();
                for(Map.Entry<String, JsonElement> member : entry.getValue().getAsJsonObject().entrySet()) {
                    if(member.getValue().isJsonPrimitive()) {
                        properties.put(member.getKey(), member.getValue().getAsString());
                    }
                }

                definitions.get(categories[i]).add(new DefinitionBlueprint(name, properties));
            }
        }

        loaded = true;
    }

    public void initialize(CommandModule module) {
        load();
        for(Map.Entry<DefinitionCategory, ArrayList<DefinitionBlueprint>> defs : definitions.entrySet()) {
            DefinitionCategory category = defs.getKey();
            for(DefinitionBlueprint blueprint : defs.getValue()) {
                category.picker.get(module.getNamespace(blueprint.namespace).getTypeManager()).create(blueprint.name).putProperties(blueprint.properties);
            }
        }
    }

    public Collection<DefinitionBlueprint> getBlueprints(DefinitionCategory category) {
        load();
        return new ArrayList<>(definitions.get(category));
    }

    @Override
    public String toString() {
        return "DefinitionPack [" + packName + "]";
    }
}

interface DictionaryPicker {
    TypeDictionary get(TypeManager mgr);
}