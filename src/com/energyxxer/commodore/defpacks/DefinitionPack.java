package com.energyxxer.commodore.defpacks;

import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.types.TypeDictionary;
import com.energyxxer.commodore.types.TypeManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.energyxxer.commodore.defpacks.DefinitionPack.DefinitionCategory.*;

public class DefinitionPack {

    enum DefinitionCategory {
        BLOCK((m) -> m.block),
        ITEM((m) -> m.item),
        EFFECT((m) -> m.effect),
        ENTITY((m) -> m.entity),
        PARTICLE((m) -> m.particle),
        ENCHANTMENT((m) -> m.enchantment),
        DIFFICULTY((m) -> m.difficulty),
        DIMENSION((m) -> m.dimension),
        GAMEMODE((m) -> m.gamemode);

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
        definitions.put(ITEM, new ArrayList<>());
        definitions.put(EFFECT, new ArrayList<>());
        definitions.put(ENTITY, new ArrayList<>());
        definitions.put(PARTICLE, new ArrayList<>());
        definitions.put(ENCHANTMENT, new ArrayList<>());
        definitions.put(DIFFICULTY, new ArrayList<>());
        definitions.put(DIMENSION, new ArrayList<>());
        definitions.put(GAMEMODE, new ArrayList<>());
    }

    public void load() {
        if(loaded) return;

        Gson gson = new Gson();

        JsonObject blocks = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/blocks.json")), JsonObject.class);
        JsonObject items = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/items.json")), JsonObject.class);
        JsonObject effects = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/effects.json")), JsonObject.class);
        JsonObject entities = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/entities.json")), JsonObject.class);
        JsonObject particles = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/particles.json")), JsonObject.class);
        JsonObject enchantments = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/enchantments.json")), JsonObject.class);

        JsonObject difficulties = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/difficulties.json")), JsonObject.class);
        JsonObject dimensions = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/dimensions.json")), JsonObject.class);
        JsonObject gamemodes = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/gamemodes.json")), JsonObject.class);

        DefinitionCategory[] categories = {BLOCK, ITEM, EFFECT, ENTITY, PARTICLE, ENCHANTMENT, DIFFICULTY, DIMENSION, GAMEMODE};
        JsonObject[] objects = {blocks, items, effects, entities, particles, enchantments, difficulties, dimensions, gamemodes};

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

        System.out.println(definitions);

        loaded = true;
    }

    public void initialize(CommandModule module) {
        if(!loaded) load();
        for(Map.Entry<DefinitionCategory, ArrayList<DefinitionBlueprint>> defs : definitions.entrySet()) {
            DefinitionCategory category = defs.getKey();
            for(DefinitionBlueprint blueprint : defs.getValue()) {
                category.picker.get(module.getNamespace(blueprint.namespace).getTypeManager()).create(blueprint.name).putProperties(blueprint.properties);
            }
        }
    }

    @Override
    public String toString() {
        return "DefinitionPack [" + packName + "]";
    }
}

class DefinitionBlueprint {
    final String namespace;
    final String name;
    final HashMap<String, String> properties;

    public DefinitionBlueprint(String name) {
        this(name, null);
    }

    public DefinitionBlueprint(String name, HashMap<String, String> properties) {
        if(name.contains(":")) {
            this.namespace = name.substring(0, name.indexOf(":"));
            this.name = name.substring(name.indexOf(":")+1);
        } else {
            this.namespace = "minecraft";
            this.name = name;
        }
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "DefinitionBlueprint{" +
                "name='" + namespace + ':' + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}

interface DictionaryPicker {
    TypeDictionary get(TypeManager mgr);
}