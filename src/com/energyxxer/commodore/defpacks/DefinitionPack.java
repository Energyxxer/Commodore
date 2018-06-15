package com.energyxxer.commodore.defpacks;

import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.Namespace;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DefinitionPack {

    /*public enum DefinitionCategory {
        BLOCK("blocks"),
        FLUID("fluids"),
        ITEM("items"),
        EFFECT("effects"),
        ENTITY("entities"),
        PARTICLE("particles"),
        ENCHANTMENT("enchantments"),
        DIMENSION("dimensions"),
        BIOME("biomes"),
        DIFFICULTY("difficulties"),
        GAMEMODE("gamemodes"),
        GAMERULE("gamerules"),
        STRUCTURE("structures"),
        SLOT("slots");

        private final String filename;

        DefinitionCategory(String filename) {
            this.filename = filename;
        }

        TypeDictionary pickFrom(TypeManager typeManager) {
            return typeManager.types.get(name().toLowerCase());
        }
    }*/

    private final String packName;
    private final String packDir;

    private final HashMap<String, ArrayList<DefinitionBlueprint>> definitions = new HashMap<>();
    private final ArrayList<String> definedCategories = new ArrayList<>();

    private boolean loaded = false;

    public DefinitionPack(String packName, String packDir) {
        this.packName = packName;
        this.packDir = packDir;

        /*for(DefinitionCategory cat : DefinitionCategory.values()) {
            definitions.put(cat, new ArrayList<>());
        }*/
    }

    public void load() {
        if(loaded) return;

        Gson gson = new Gson();

        {
            JsonObject definitions = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/type_definitions.json")), JsonObject.class);

            for(Map.Entry<String, JsonElement> categoryDef : definitions.entrySet()) {
                String category = categoryDef.getKey();
                this.definedCategories.add(category);
                this.definitions.put(category, new ArrayList<>());

                boolean useNamespace = categoryDef.getValue().getAsJsonObject().get("use_namespace").getAsJsonPrimitive().getAsBoolean();
                String from = categoryDef.getValue().getAsJsonObject().get("from").getAsString();

                JsonObject jso = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/" + from + ".json")), JsonObject.class);
                for(Map.Entry<String, JsonElement> typeDef : jso.entrySet()) {
                    String name = typeDef.getKey();
                    HashMap<String, String> properties = new HashMap<>();
                    for(Map.Entry<String, JsonElement> member : typeDef.getValue().getAsJsonObject().entrySet()) {
                        if(member.getValue().isJsonPrimitive()) {
                            properties.put(member.getKey(), member.getValue().getAsString());
                        }
                    }

                    this.definitions.get(category).add(new DefinitionBlueprint(name, properties, useNamespace));
                }
            }
        }

        loaded = true;
    }

    public void initialize(CommandModule module) {
        load();
        for(Map.Entry<String, ArrayList<DefinitionBlueprint>> defs : definitions.entrySet()) {
            String category = defs.getKey();
            for(DefinitionBlueprint blueprint : defs.getValue()) {
                Namespace ns = (blueprint.namespace != null) ? module.getNamespace(blueprint.namespace) : module.minecraft;
                ns.getTypeManager().createDictionary(category, blueprint.namespace != null).create(blueprint.name).putProperties(blueprint.properties);
            }
        }
    }

    public Collection<String> getDefinedCategories() {
        load();
        return new ArrayList<>(definedCategories);
    }

    public Collection<DefinitionBlueprint> getBlueprints(String category) {
        load();
        return new ArrayList<>(definitions.get(category));
    }

    @Override
    public String toString() {
        return "DefinitionPack [" + packName + "]";
    }
}