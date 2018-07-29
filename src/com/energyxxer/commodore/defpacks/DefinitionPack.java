package com.energyxxer.commodore.defpacks;

import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.standard.StandardDefinitionPacks;
import com.energyxxer.commodore.tags.Tag;
import com.energyxxer.commodore.tags.TagGroup;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.util.io.CompoundInput;
import com.energyxxer.commodore.util.io.JsonObjectWrapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Serves as a medium to incorporate game data from a pack, containing things from type definitions and tag definitions
 * to custom category definitions. This definition pack can then be loaded and imported into a command module once or
 * multiple times, copying all of the definitions supplied by the pack into that command module.<br><br>
 *
 * A definition pack can be created from, but not limited to, a classpath resource, a directory in the filesystem
 * or a zip file. More sources can be made by extending the CompoundInput interface.<br><br>
 *
 * Definition packs have the following structure:<br>
 *
 * <pre>
 * root/
 *      type_definitions.json
 *      data/
 *          <i>namespace/</i>
 *              tags/
 *                  <i>category_name/</i>
 *                      <i>tag_name</i>.json
 *                      <i>tag_subfolder/</i>
 *                          <i>nested_tag</i>.json
 * </pre>
 *
 * Of these, type_definitions.json is mandatory. It should contain an object where each element's key corresponds to
 * a type category, and their value corresponds to an object containing that category's properties. Possible category
 * options are as follows:
 *
 * <ol>
 *     <li>use_namespace: <code>boolean</code> - Describes whether the namespace of that type should be printed along
 *     with the type's name. For instance, blocks utilize the namespace (minecraft:stone), while gamemodes don't
 *     (survival). <code>true</code> if it should include the namespace, <code>false</code> if it shouldn't. If not
 *     specified, defaults to <code>false</code>. Types for
 *     categories that don't use namespaces will be stored in the minecraft namespace</li>
 *     <li>import_from: <code>string</code> - Specifies the path from the root of the definition pack to find all the
 *     definitions for the corresponding category. The .json extension for this file is implicit.
 *     This path doesn't need to be any of those defined in the definition pack structure above. In the standard
 *     definition pack for Minecraft Java 1.13, all the definitions are under the <code>definitions/</code>
 *     folder.</li>. If not specified, no default types are created for this category.
 *     <li>tag_directory: <code>string</code> - Describes the name of the folder under the
 *     <code>data/<i>namespace</i>/tags/</code> directory where tags for this category should be exported. If not
 *     specified, tags for this category will not be exported.</li>
 * </ol>
 *
 * An example of an entry in the root object of type_definitions is as follows:
 *
 * <pre>
 * "block": {
 *     "use_namespace": true,
 *     "import_from": "definitions/blocks",
 *     "tag_directory": "blocks"
 * }
 * </pre>
 *
 * For type definition files, the root should be an object, where each element's key corresponds to a type name
 * (including namespace, if applicable), and each element's value should be an object, containing any properties the
 * pack deems appropriate for the type. Those properties will then be added to the generated Type object's property map
 * in a String-String pair. Non-string property values, such as numbers, booleans, objects and lists,
 * will be stored as raw strings.<br><br>
 *
 * An example of a type definition in the root object of a type definition is as follows:
 * <pre>
 * "minecraft:speed": {
 *     "id": 1,
 *     "type": "positive"
 * }
 * </pre>
 *
 * As for the content of tag files, the format is exactly the same as vanilla Minecraft tags, with one extra property.
 * You can add a boolean property called "export" to the root object of the file to specify whether or not that
 * specific tag should be exported by any command module that has it, during compilation. By default, tags created
 * by definition packs are not exported into data packs.
 *
 * @see StandardDefinitionPacks
 *
 * @see CommandModule
 * @see CompoundInput
 * @see Type
 * @see Tag
 * */
public class DefinitionPack {

    private final Gson gson;

    private final String packName;
    private final CompoundInput fsi;

    private final HashMap<String, ArrayList<DefinitionBlueprint>> definitions = new HashMap<>();
    private final HashMap<String, ArrayList<TagBlueprint>> tags = new HashMap<>();
    private final ArrayList<TypeDefinition> definedCategories = new ArrayList<>();

    private boolean loaded = false;

    public DefinitionPack(String packName, CompoundInput fsi) {
        this.packName = packName;
        this.fsi = fsi;

        this.gson = new Gson();
    }

    public void load() throws IOException {
        if(loaded) return;
        loaded = true;

        try {
            fsi.open();

            JsonObject definitions = gson.fromJson(new InputStreamReader(fsi.get("type_definitions.json")), JsonObject.class);

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

        } finally {
            fsi.close();
        }
    }

    private void importTypes(TypeDefinition definition) throws IOException {
        InputStreamReader is = new InputStreamReader(fsi.get(definition.importFrom + ".json"));
        JsonObject entryFileObj = gson.fromJson(is, JsonObject.class);
        for(Map.Entry<String, JsonElement> typeDef : entryFileObj.entrySet()) {
            String name = typeDef.getKey();
            HashMap<String, String> properties = new HashMap<>();
            for(Map.Entry<String, JsonElement> member : typeDef.getValue().getAsJsonObject().entrySet()) {
                properties.put(member.getKey(), member.getValue().toString());
            }

            this.definitions.get(definition.category).add(new DefinitionBlueprint(name, properties, definition.useNamespace));
        }

        is.close();
    }

    private void importNamespaceData() throws IOException {
        InputStream in = fsi.get("data/");
        if(in != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String namespace;
            while((namespace = br.readLine()) != null) {
                importTags(namespace);
            }

            br.close();
        }
    }

    private void importTags(String namespace) throws IOException {
        InputStream in = fsi.get("data/" + namespace + "/tags/");
        if(in != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String inCategory;
            while((inCategory = br.readLine()) != null) {
                for(TypeDefinition definition : definedCategories) {
                    if(inCategory.equals(definition.tagDirectory)) {
                        importTagGroup("data/" + namespace + "/tags/" + definition.tagDirectory, namespace, "", definition);
                    }
                }

            }

            br.close();
        }
    }

    private void importTagGroup(String path, String namespace, String prevPath, TypeDefinition definition) throws IOException {
        InputStream in = fsi.get(path);
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
    }

    private void importTag(String path, String namespace, String name, TypeDefinition definition) throws IOException {
        try(InputStreamReader is = new InputStreamReader(fsi.get(path))) {
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
        }
    }

    public void initialize(CommandModule module) throws IOException {
        load();
        for(Map.Entry<String, ArrayList<DefinitionBlueprint>> defs : definitions.entrySet()) {
            String category = defs.getKey();
            for(DefinitionBlueprint blueprint : defs.getValue()) {
                Namespace ns = (blueprint.namespace != null) ? module.createNamespace(blueprint.namespace) : module.minecraft;
                ns.getTypeManager().createDictionary(category, blueprint.namespace != null).create(blueprint.name).putProperties(blueprint.properties);
            }
        }
        for(Map.Entry<String, ArrayList<TagBlueprint>> entry : tags.entrySet()) {
            String category = entry.getKey();
            for(TagBlueprint blueprint : entry.getValue()) {
                Namespace ns = (blueprint.namespace != null) ? module.createNamespace(blueprint.namespace) : module.minecraft;
                TagGroup<? extends Tag> group = ns.getTagManager().createGroup(category, getCategory(category).tagDirectory);

                Tag tag = group.create(blueprint.name);
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
                        Tag created = module.createNamespace(namespace).getTagManager().getGroup(category).create(value);
                        tag.addValue(created);
                    } else {
                        Type created = module.createNamespace(namespace).getTypeManager().createDictionary(category, true).create(value);
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

    @Override
    public String toString() {
        return "DefinitionPack [" + packName + "]";
    }
}