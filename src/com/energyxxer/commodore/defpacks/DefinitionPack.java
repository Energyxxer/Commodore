package com.energyxxer.commodore.defpacks;

import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.DefinitionPopulatable;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
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
 *      pack.json
 *      data/
 *          <i>namespace/</i>
 *              tags/
 *                  <i>category_name/</i>
 *                      <i>tag_name</i>.json
 *                      <i>tag_subfolder/</i>
 *                          <i>nested_tag</i>.json
 * </pre>
 *
 * Of these, pack.json is mandatory. Its root should be an object containing the main configuration for the pack.
 * <br>
 *     Accepted values currently are:<br><br>
 *
 *     <code>version - int</code>: serves to tell Commodore what version this pack is meant to be parsed as, for the
 *     sake of backwards compatibility. The current version will be in the {@link DefinitionPack#CURRENT_PACK_VERSION}
 *     field. This documentation will always cover info only for the current pack version.<br>
 *
 *     <code>categories - object</code>: It should be an object where each element's key corresponds to a type category,
 *     and their value corresponds to an object containing that category's properties. Possible category options are
 *     as follows:
 *
 * <ol>
 *     <li>use_namespace: <code>boolean</code> - Describes whether the namespace of that type should be printed along
 *     with the type's name. For instance, blocks utilize the namespace (minecraft:stone), while gamemodes don't
 *     (survival). <code>true</code> if it should include the namespace, <code>false</code> if it shouldn't. If not
 *     specified, defaults to <code>false</code>. Types for
 *     categories that don't use namespaces will be stored in the <code>minecraft</code> namespace</li>
 *     <li>import_from: <code>string</code> - Specifies the path from the root of the definition pack to find all the
 *     definitions for the corresponding category. The .json extension for this file is implicit.
 *     This path doesn't need to be any of those defined in the definition pack structure above. In the standard
 *     definition pack for Minecraft Java 1.13, all the definitions are under the <code>definitions/</code>
 *     folder. If not specified, no default types are created for this category.</li>
 *     <li>tag_directory: <code>string</code> - Describes the name of the folder under the
 *     <code>data/<i>namespace</i>/tags/</code> directory where tags for this category should be exported. If not
 *     specified, tags for this category will not be exported.</li>
 * </ol>
 *
 * An example of an entry in the root object of pack.json is as follows:
 *
 * <pre>
 * {
 *   "version": 1,
 *   "categories": {
 *     "block": {
 *       "use_namespace": true,
 *       "import_from": "definitions/blocks",
 *       "tag_directory": "blocks"
 *     },
 *     "item": {
         "use_namespace": true,
         "tag_directory": "items",
         "import_from": "definitions/items"
        }
 *   }
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
    /**
     * Describes the most recent definition pack version this class can parse.
     * */
    private final static int CURRENT_PACK_VERSION = 1;

    /**
     * The Gson object used to parse the pack's JSON.
     * */
    @NotNull
    private final Gson gson;

    /**
     * The {@link CompoundInput} from which to retrieve pack data.
     * */
    @NotNull
    private final CompoundInput source;

    /**
     * Stores each of the definitions for each category. The key is the category to which the definitions belong, and
     * the value is the list of definitions for that specific category.
     * Populated once pack is loaded.
     * */
    @NotNull
    private final HashMap<String, ArrayList<DefinitionBlueprint>> definitions = new HashMap<>();
    /**
     * Stores each of the tags for each category. The key is the category to which the tags belong, and the value is
     * the list of tags for that specific category.
     * Populated once pack is loaded.
     * */
    @NotNull
    private final HashMap<String, ArrayList<TagBlueprint>> tags = new HashMap<>();
    /**
     * Stores a list of all the defined categories and their flags.
     * */
    @NotNull
    private final ArrayList<CategoryDeclaration> definedCategories = new ArrayList<>();
    /**
     * Stores a map of all the extra resources for this definition pack.
     * */
    @NotNull
    private final HashMap<String, Object> resources = new HashMap<>();

    /**
     * Describes whether this pack has been loaded before; that is, if the contents of the pack have been read and
     * stored in this object's fields.
     * */
    private boolean loaded = false;

    /**
     * The name of the pack, as specified by the "name" property of the pack.json file. Set when pack is loaded.
     * */
    @Nullable
    private String packName;
    /**
     * The version of this pack, as specified by the "version" property of the pack.json file. Set when pack is loaded.
     * */
    private int version = -1;

    /**
     * Creates a definition pack object to be read from the specified source.
     *
     * @param source The source from which to read the definition pack.
     * */
    public DefinitionPack(@NotNull CompoundInput source) {
        this.source = source;

        this.gson = new Gson();
    }

    /**
     * Reads the definition pack's contents from the source into this object for future use.
     *
     * @throws IOException If an IOException occurred during the retrieval of the pack's resources.
     * @throws MalformedPackException If mandatory files or properties are not found in the source.
     * */
    public synchronized void load() throws IOException {
        if(loaded) return;

        try {
            source.open();

            InputStream packIs = source.get("pack.json");
            if(packIs == null) throw new MalformedPackException("pack.json file not found");

            JsonObject config = gson.fromJson(new InputStreamReader(packIs), JsonObject.class);
            JsonObjectWrapper configWrapper = new JsonObjectWrapper(config);

            this.packName = configWrapper.getAsString("name",null);
            if(this.packName == null || this.packName.length() == 0)
                throw new MalformedPackException("\"name\" property of definition pack not found in the root object of pack.json");

            this.version = configWrapper.getAsInt("version", CURRENT_PACK_VERSION);
            if(this.version > CURRENT_PACK_VERSION) {
                System.err.println("[WARNING] Definition pack '" + packName + "' is pack version " + this.version + " but " + getClass().getName() + " only supports up to version " + CURRENT_PACK_VERSION);
                System.err.println("\tWill attempt to parse pack as version " + CURRENT_PACK_VERSION);
            }

            JsonObject categories = config.getAsJsonObject("categories");

            if(categories != null) for(Map.Entry<String, JsonElement> categoryDef : categories.entrySet()) {
                String category = categoryDef.getKey();

                CategoryDeclaration definition = new CategoryDeclaration(category);

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

            //Load extra resources

            importResources();

        } finally {
            source.close();
            loaded = true;
        }
    }

    /**
     * Imports the type definitions for the specified category using that category's flags.
     *
     * @param declaration The category declaration object whose type definitions should be read.
     * */
    private void importTypes(@NotNull CategoryDeclaration declaration) throws IOException {
        InputStream fileIn = source.get(declaration.importFrom + ".json");
        if(fileIn == null) throw new MalformedPackException("Couldn't import type definitions for category '" + declaration.category + "': " + declaration.importFrom + ".json wasn't found");
        InputStreamReader isr = new InputStreamReader(fileIn);
        JsonObject entryFileObj = gson.fromJson(isr, JsonObject.class);
        for(Map.Entry<String, JsonElement> typeDef : entryFileObj.entrySet()) {
            String name = typeDef.getKey();
            HashMap<String, String> properties = new HashMap<>();
            for(Map.Entry<String, JsonElement> member : typeDef.getValue().getAsJsonObject().entrySet()) {
                String stringified = (member.getValue().isJsonPrimitive()) ? member.getValue().getAsString() : member.getValue().toString();
                properties.put(member.getKey(), stringified);
            }

            this.definitions.get(declaration.category).add(new DefinitionBlueprint(name, properties, declaration.useNamespace));
        }

        isr.close();
    }

    /**
     * Imports all the namespace data (such as tags) for this definition pack.
     * */
    private void importNamespaceData() throws IOException {
        InputStream in = source.get("data/");
        if(in != null) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

                String namespace;
                while ((namespace = br.readLine()) != null) {
                    importTags(namespace);
                }
            }
        }
    }

    /**
     * Imports all the tags in the specified namespace for this definition pack.
     *
     * @param namespace The string of the namespace whose tags should be imported.
     * */
    private void importTags(@NotNull String namespace) throws IOException {
        InputStream in = source.get("data/" + namespace + "/tags/");
        if(in != null) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String inCategory;
                while((inCategory = br.readLine()) != null) {
                    for(CategoryDeclaration definition : definedCategories) {
                        if(inCategory.equals(definition.tagDirectory)) {
                            importTagGroup("data/" + namespace + "/tags/" + definition.tagDirectory, namespace, "", definition);
                        }
                    }

                }
            }
        }
    }

    /**
     * Imports all the tags corresponding to a specific category in a given namespace for this definition pack.
     *
     * @param path The path to the directory containing the tags to import, directly under the tag group directory.
     * @param namespace The namespace this tag group belongs to; that is, the name of the folder directly
     *                  under the data/ directory.
     * @param prevPath The path segment to append between the path and the files to read. Used for nested tags.
     * @param declaration The category declaration for the category this tag group belongs to.
     * */
    private void importTagGroup(String path, String namespace, String prevPath, CategoryDeclaration declaration) throws IOException {
        InputStream in = source.get(path);
        if(in != null) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String tag;
                while ((tag = br.readLine()) != null) {
                    if (tag.endsWith(".json")) {
                        importTag(path + "/" + tag, namespace, prevPath + tag.substring(0, tag.length() - 5), declaration);
                    } else if (!tag.contains(".")) {
                        importTagGroup(path + "/" + tag, namespace, path + "/" + prevPath + tag + "/", declaration);
                    }
                }
            }
        }
    }

    /**
     * Imports the tag at the corresponding path.
     *
     * @param path The path to the tag file to import.
     * @param namespace The namespace this tag belongs to.
     * @param name The name of the tag, including nested folders inside the tag group directory, and excluding
     *             the .json extension. That is, what this tag will be referred to using commands, without the pound
     *             prefix and the namespace.
     * @param declaration The category declaration for the category this tag group belongs to.
     * */
    private void importTag(@NotNull String path, @NotNull String namespace, @NotNull String name, @NotNull CategoryDeclaration declaration) throws IOException {
        try(InputStreamReader is = new InputStreamReader(source.get(path))) {
            JsonObjectWrapper obj = new JsonObjectWrapper(gson.fromJson(is, JsonObject.class));

            TagBlueprint tag = new TagBlueprint(namespace, name);

            tag.policy = Tag.OverridePolicy.valueOf(obj.getAsBoolean("replace", Tag.OverridePolicy.DEFAULT_POLICY.valueBool));
            tag.export = obj.getAsBoolean("export", false);
            JsonArray values = obj.obj.getAsJsonArray("values");
            for(JsonElement elem : values) {
                tag.content.add(elem.getAsString());
            }

            if(!tags.containsKey(declaration.category)) tags.put(declaration.category, new ArrayList<>());
            tags.get(declaration.category).add(tag);
        }
    }

    private void importResources() throws IOException {
        InputStream in = source.get("resources/");
        if (in != null) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

                String resourceFile;
                while ((resourceFile = br.readLine()) != null) {
                    readResource(resourceFile);
                }
            }
        }
    }

    private void readResource(String fileName) throws IOException {
        InputStream in = source.get("resources/" + fileName);
        if(in != null) {
            if (fileName.endsWith(".json")) {
                try (InputStreamReader is = new InputStreamReader(in)) {
                    JsonObject obj = gson.fromJson(is, JsonObject.class);
                    resources.put(fileName, obj);
                }
            } else {
                try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    resources.put(fileName, sb.toString());
                }
            }
        }
    }

    /**
     * Adds all this definition pack's definitions into the given module. This method loads the definition pack into
     * memory if it hasn't already been loaded.
     *
     * @param module The module to populate with this pack's definitions.
     *
     * @throws IOException If an IOException occurred during the retrieval of the pack's resources (and hasn't been
     *                     loaded before).
     * @throws MalformedPackException If mandatory files or properties are not found in the source (and hasn't been
     *                                loaded before).
     * */
    public void populate(@NotNull DefinitionPopulatable module) throws IOException {
        load();
        for(Map.Entry<String, ArrayList<DefinitionBlueprint>> defs : definitions.entrySet()) {
            String category = defs.getKey();
            for(DefinitionBlueprint blueprint : defs.getValue()) {
                module.getTypeManager(blueprint.namespace).createDictionary(category, blueprint.namespace != null).getOrCreate(blueprint.name).putProperties(blueprint.properties);
            }
        }
        for(Map.Entry<String, ArrayList<TagBlueprint>> entry : tags.entrySet()) {
            String category = entry.getKey();
            for(TagBlueprint blueprint : entry.getValue()) {
                TagGroup<? extends Tag> group = module.getTagManager(blueprint.namespace).createGroup(category, getCategory(category).tagDirectory);

                Tag tag = group.getOrCreate(blueprint.name);
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
                        Tag created = module.getTagManager(namespace).getGroup(category).getOrCreate(value);
                        tag.addValue(created, false);
                    } else {
                        Type created = module.getTypeManager(namespace).createDictionary(category, true).getOrCreate(value);
                        tag.addValue(created, false);
                    }
                }
            }
        }
        for(Map.Entry<String, Object> entry : resources.entrySet()) {
            module.putResource(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Retrieves the declaration for the given category, if it exists.
     *
     * @param category The string specifying the name of the category whose declaration is to be fetched.
     *
     * @return The declaration for the specified category.
     *
     * @throws MalformedPackException If the declaration for such category doesn't exist (shouldn't happen in practice).
     * */
    private @NotNull CategoryDeclaration getCategory(@NotNull String category) {
        for(CategoryDeclaration def : definedCategories) {
            if(def.category.equals(category)) return def;
        }
        throw new MalformedPackException("Unable to find definition of category '" + category + "'");
    }

    /**
     * Retrieves the list of blueprints for types of the specified category.
     *
     * @param category The category whose blueprints should be retrieved.
     *
     * @return The list of blueprints for that specific category, if it exists. Otherwise <code>null</code>.
     *
     * @throws IllegalStateException If the definition pack hasn't been loaded yet.
     * */
    public @NotNull Collection<DefinitionBlueprint> getBlueprints(@NotNull String category) {
        if(!loaded) throw new IllegalStateException("Definition pack hasn't been loaded yet");
        return new ArrayList<>(definitions.get(category));
    }

    /**
     * Retrieves the map of each type blueprint, where the key is the category and the value is the list of its type
     * blueprints.
     *
     * @return The map of each type blueprint.
     *
     * @throws IllegalStateException If the definition pack hasn't been loaded yet.
     * */
    public HashMap<String, ArrayList<DefinitionBlueprint>> getDefinitions() {
        if(!loaded) throw new IllegalStateException("Definition pack hasn't been loaded yet");
        return definitions;
    }

    /**
     * Retrieves the list of category declaration in this definition pack.
     *
     * @return The list of defined categories.
     *
     * @throws IllegalStateException If the definition pack hasn't been loaded yet.
     * */
    public @NotNull Collection<CategoryDeclaration> getDefinedCategories() {
        if(!loaded) throw new IllegalStateException("Definition pack hasn't been loaded yet");
        return new ArrayList<>(definedCategories);
    }

    @Override
    public String toString() {
        return "DefinitionPack [" + packName + "]";
    }
}