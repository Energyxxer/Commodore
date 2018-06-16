package com.energyxxer.commodore.defpacks;

import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.tags.Tag;
import com.energyxxer.commodore.tags.TagGroup;
import com.energyxxer.commodore.tags.TagIncorporable;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.util.JsonObjectWrapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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

    private final Gson gson;

    private final String packName;
    private final String packDir;

    private final HashMap<String, ArrayList<DefinitionBlueprint>> definitions = new HashMap<>();
    private final HashMap<String, ArrayList<TagBlueprint>> tags = new HashMap<>();
    private final ArrayList<TypeDefinition> definedCategories = new ArrayList<>();

    private boolean loaded = false;

    public DefinitionPack(String packName, String packDir) {
        this.packName = packName;
        this.packDir = packDir;

        this.gson = new Gson();

        /*for(DefinitionCategory cat : DefinitionCategory.values()) {
            definitions.put(cat, new ArrayList<>());
        }*/
    }

    public void load() {
        if(loaded) return;

        {
            JsonObject definitions = gson.fromJson(new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/type_definitions.json")), JsonObject.class);

            for(Map.Entry<String, JsonElement> categoryDef : definitions.entrySet()) {
                String category = categoryDef.getKey();

                TypeDefinition definition = new TypeDefinition(category);

                this.definedCategories.add(definition);
                this.definitions.put(category, new ArrayList<>());

                JsonObjectWrapper defObj = new JsonObjectWrapper(categoryDef.getValue().getAsJsonObject());

                definition.useNamespace = defObj.getAsBoolean("use_namespace", true);
                definition.importFrom = defObj.getAsString("import_from");
                definition.tagDirectory = defObj.getAsString("tag_directory");

                if(definition.importFrom != null) {
                    importTypes(definition);
                }
            }

            importNamespaceData();

            System.out.println("tags = " + tags);
        }

        loaded = true;
    }

    private void importTypes(TypeDefinition definition) {
        InputStreamReader is = new InputStreamReader(DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/" + definition.importFrom + ".json"));
        JsonObject entryFileObj = gson.fromJson(is, JsonObject.class);
        for(Map.Entry<String, JsonElement> typeDef : entryFileObj.entrySet()) {
            String name = typeDef.getKey();
            HashMap<String, String> properties = new HashMap<>();
            for(Map.Entry<String, JsonElement> member : typeDef.getValue().getAsJsonObject().entrySet()) {
                if(member.getValue().isJsonPrimitive()) {
                    properties.put(member.getKey(), member.getValue().getAsString());
                }
            }

            this.definitions.get(definition.category).add(new DefinitionBlueprint(name, properties, definition.useNamespace));
        }

        try {
            is.close();
        } catch(IOException x) {
            x.printStackTrace();
        }
    }

    private void importNamespaceData() {
        try {
            InputStream in = DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/data/");
            if(in != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String namespace;
                while((namespace = br.readLine()) != null) {
                    importTags(namespace);
                }

                br.close();
            }
        } catch(IOException x) {
            x.printStackTrace();
        }
    }

    private void importTags(String namespace) {
        try {
            InputStream in = DefinitionPack.class.getResourceAsStream("/defpacks/" + packDir + "/data/" + namespace + "/tags/");
            if(in != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String inCategory;
                while((inCategory = br.readLine()) != null) {

                    for(TypeDefinition definition : definedCategories) {
                        if(inCategory.equals(definition.tagDirectory)) {
                            importTagGroup("/defpacks/" + packDir + "/data/" + namespace + "/tags/" + definition.tagDirectory, namespace, "", definition);
                        }
                    }

                }

                br.close();
            }
        } catch(IOException x) {
            x.printStackTrace();
        }
    }

    private void importTagGroup(String path, String namespace, String prevPath, TypeDefinition definition) {
        try {
            InputStream in = DefinitionPack.class.getResourceAsStream(path);
            if(in != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String tag;
                while((tag = br.readLine()) != null) {
                    if(tag.endsWith(".json")) {
                        importTag(path + "/" + tag, namespace, prevPath + tag.substring(0, tag.length()-5), definition);
                    } else if(!tag.contains(".")) {
                        importTagGroup(path + "/" + tag, namespace, path + "/" + prevPath + tag + "/", definition);
                    }
                }

                br.close();
            }
        } catch(IOException x) {
            x.printStackTrace();
        }
    }

    private void importTag(String path, String namespace, String name, TypeDefinition definition) {
        try(InputStreamReader is = new InputStreamReader(DefinitionPack.class.getResourceAsStream(path))) {
            JsonObjectWrapper obj = new JsonObjectWrapper(gson.fromJson(is, JsonObject.class));

            TagBlueprint tag = new TagBlueprint(namespace, name);

            tag.policy = Tag.OverridePolicy.valueOf(obj.getAsBoolean("replace", Tag.OverridePolicy.DEFAULT_POLICY.valueBool));
            tag.export = obj.getAsBoolean("export", false);
            JsonArray values = obj.obj.getAsJsonArray("values");
            for(JsonElement elem : values) {
                tag.content.add(elem.getAsString());
            }

            if(!tags.containsKey(definition.category)) tags.put(definition.category, new ArrayList<>());
            tags.get(definition.category).add(tag);
        } catch(IOException x) {
            x.printStackTrace();
        }
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
        for(Map.Entry<String, ArrayList<TagBlueprint>> entry : tags.entrySet()) {
            String category = entry.getKey();
            for(TagBlueprint blueprint : entry.getValue()) {
                Namespace ns = (blueprint.namespace != null) ? module.getNamespace(blueprint.namespace) : module.minecraft;
                TagGroup<? extends Tag> group = ns.getTagManager().createGroup(category, getCategory(category).tagDirectory);

                Tag<? extends TagIncorporable> tag = group.create(blueprint.name);
                tag.setOverridePolicy(blueprint.policy);
                tag.setExport(blueprint.export);

                for(String value : blueprint.content) {
                    boolean isTag = value.startsWith("#");
                    if(isTag) value = value.substring(1);
                    String namespace = "minecraft";
                    if(value.contains(":")) {
                        namespace = value.substring(0, value.indexOf(":"));
                        value = value.substring(value.indexOf(":")+1);
                    }

                    if(isTag) {
                        Tag created = module.getNamespace(namespace).getTagManager().getGroup(category).create(value);
                        tag.addValue(created);
                    } else {
                        Type created = module.getNamespace(namespace).getTypeManager().createDictionary(category, true).create(value);
                        tag.addValue(created);
                    }
                }
            }
        }
    }

    private TypeDefinition getCategory(String category) {
        for(TypeDefinition def : definedCategories) {
            if(def.category.equals(category)) return def;
        }
        throw new RuntimeException("Unable to find definition of category '" + category + "'");
    }

    public Collection<TypeDefinition> getDefinedCategories() {
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