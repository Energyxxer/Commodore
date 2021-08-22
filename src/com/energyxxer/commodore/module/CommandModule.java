package com.energyxxer.commodore.module;

import com.energyxxer.commodore.Commodore;
import com.energyxxer.commodore.defpacks.DefinitionPack;
import com.energyxxer.commodore.defpacks.MalformedPackException;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.functionlogic.score.ObjectiveManager;
import com.energyxxer.commodore.module.settings.ModuleSettings;
import com.energyxxer.commodore.module.settings.ModuleSettingsManager;
import com.energyxxer.commodore.tags.TagGroup;
import com.energyxxer.commodore.tags.TagManager;
import com.energyxxer.commodore.types.defaults.TypeManager;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The main agent of Commodore, storing data which is used by and for data packs, including, but not limited to, blocks,
 * items, entities, functions, tags, etc. Command modules can be modified via other classes and compiled into data
 * packs, which can then be used in Minecraft.
 * */
public class CommandModule implements ExportablePack, DefinitionPopulatable {
    /**
     * The name of this command module, which is used as the directory or file name for the compiled pack.
     * */
    @NotNull
    protected String name;
    /**
     * The description of this command module, as seen in the compiled pack.mcmeta
     * */
    @NotNull
    protected String description;

    /**
     * This module's settings.
     */
    @NotNull
    protected final ModuleSettings settings;

    /**
     * This module's objective manager.
     * */
    @NotNull
    protected final ObjectiveManager objMgr;

    /**
     * A map of this objective's namespaces, where the key is the namespace string, and the value is the object of the
     * namespace whose name is the string in its key.
     * */
    @NotNull
    protected final HashMap<@NotNull String, @NotNull Namespace> namespaces = new HashMap<>();

    /**
     * A list that contains extra exportable files that will be exported alongside the module when compiled.
     * */
    @NotNull
    public final ArrayList<Exportable> exportables = new ArrayList<>();

    /**
     * This command module's default namespace, made a <code>public final</code> field for easy access.
     * */
    @NotNull
    public final Namespace minecraft;

    @NotNull
    protected final HashMap<@NotNull String, Object> resources = new HashMap<>();

    /**
     * Creates a command module with the given name and prefix.
     *
     * @param name The name of the new module.
     * */
    public CommandModule(@NotNull String name) {
        this(name, "Module Pack created programmatically with Commodore");
    }

    /**
     * Creates a command module with the given name, description and prefix.
     *
     * @param name The name of the new module.
     * @param description The description of the module, as to be displayed in the data pack's mcmeta file.
     * */
    public CommandModule(@NotNull String name, @NotNull String description) {
        this.name = name;
        this.description = description;

        this.objMgr = new ObjectiveManager();

        this.minecraft = getNamespace("minecraft");

        settings = new ModuleSettings(Commodore.DEFAULT_TARGET_VERSION);
    }

    /**
     * Retrieves this module's name.
     *
     * @return The name for this module.
     * */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Retrieves this module's description.
     *
     * @return The description for this module.
     * */
    @NotNull
    public String getDescription() {
        return description;
    }

    /**
     * Changes this module's name.
     *
     * @param name The new name for this module.
     * */
    public void setName(@NotNull String name) {
        this.name = name;
    }

    /**
     * Changes this module's description.
     *
     * @param description The new description for this module.
     * */
    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    /**
     * Retrieves this module's objective manager.
     *
     * @return The objective manager for this module.
     * */
    @NotNull
    public ObjectiveManager getObjectiveManager() {
        return objMgr;
    }

    /**
     * Retrieves this module's settings manager.
     *
     * @return the settings for this module.
     */
    @NotNull
    public ModuleSettings getSettingsManager() {
        return settings;
    }

    /**
     * Enables this module's settings to be enforced from the point this method is call until compilation.
     * If enabled, any object instantiations or methods that may result in a version incompatibility error will throw
     * an exception on the spot, rather than only showing them during compilation.
     * <p>
     * It is advised that you call this before doing anything with this module.
     * </p>
     * Multiple command modules in the same thread may not be active at at once.
     */
    public void setSettingsActive() {
        ModuleSettingsManager.set(settings);
    }

    /**
     * Generates a data pack with the module's current content.
     *
     * @param file The file inside which the data pack will be generated.
     *
     * @throws IOException If an IO error occurs during the generation of the data pack.
     * */
    public void compile(@NotNull File file) throws IOException {
        ModuleSettingsManager.set(settings);
        objMgr.compile();
        namespaces.values().forEach(Namespace::compile);

        if(settings.EXPORT_PACK_MCMETA.getValue() && exportables.stream().noneMatch(e -> "pack.mcmeta".equals(e.getExportPath()))) {
            JsonObject root = new JsonObject();
            JsonObject inner = new JsonObject();
            root.add("pack", inner);
            inner.addProperty("pack_format", VersionFeatureManager.getInt("pack_format", VersionFeatureManager.getInt("data_pack_format", 1)));

            inner.addProperty("description", this.description);

            exportables.add(new RawExportable("pack.mcmeta", new GsonBuilder().setPrettyPrinting().create().toJson(root).getBytes(Commodore.getDefaultEncoding())));
        }

        new ModulePackGenerator(this, file).generate();
        ModuleSettingsManager.clear();
    }

    /**
     * Creates a namespace with the given name, if it doesn't already exist.
     *
     * @param name The name of the new namespace.
     *
     * @return The newly created namespace, if it was created, or the previously existing namespace if one of the same
     * name was found.
     *
     * @deprecated Does the same thing as {@link CommandModule#getNamespace(String)}
     * */
    @NotNull
    @Deprecated
    public Namespace createNamespace(@NotNull String name) {
        return getNamespace(name);
    }

    /**
     * Creates a namespace with the given name, if it doesn't already exist.
     *
     * @param name The name of the namespace.
     *
     * @return The newly created namespace, if it was created, or the previously existing namespace if one of the same
     * name was found.
     * */
    public Namespace getNamespace(@NotNull String name) {
        Namespace alreadyExisting = namespaces.get(name);
        if(alreadyExisting != null) return alreadyExisting;

        Namespace newNamespace = new Namespace(name);
        namespaces.put(name, newNamespace);
        return newNamespace;
    }

    /**
     * Retrieves a collection of all namespaces in this module
     *
     * @return a collection of all namespaces in this module
     * */
    @NotNull
    public Collection<Namespace> getAllNamespaces() {
        return namespaces.values();
    }

    /**
     * Adds all the data of the specified module into this module.
     *
     * @param other The module whose data is to be copied over to this module.
     * */
    public void join(@NotNull CommandModule other) {
        for(Map.Entry<String, Namespace> ns : other.namespaces.entrySet()) {
            if(this.namespaces.containsKey(ns.getKey())) {
                this.namespaces.get(ns.getKey()).join(ns.getValue());
            } else {
                this.namespaces.put(ns.getKey(), ns.getValue().clone());
            }
        }
        for(Objective obj : other.objMgr.getAll()) {
            if(!this.objMgr.exists(obj.getName())) {
                this.objMgr.create(obj.getName(), obj.getType(), obj.getDisplayName());
            }
        }
        this.resources.putAll(other.resources);
        this.exportables.addAll(other.exportables);
    }

    /**
     * Checks whether a namespace with the given name already exists.
     *
     * @param name The name of the namespace to check.
     *
     * @return <code>true</code> if a namespace of the given name exists in this module, <code>false</code> otherwise.
     * */
    public boolean namespaceExists(@NotNull String name) {
        return namespaces.containsKey(name);
    }

    /**
     * Adds all the given definition pack's definitions into this module. This method loads the definition pack into
     * memory if it hasn't already been loaded.
     *
     * @param pack The pack with whose definitions to populate this module.
     *
     * @throws IOException If an IOException occurred during the retrieval of the pack's resources (and hasn't been
     *                     loaded before).
     * @throws MalformedPackException If mandatory files or properties are not found in the pack's source (and hasn't
     *                                been loaded before).
     * */
    public void importDefinitions(@NotNull DefinitionPack pack) throws IOException {
        pack.populate(this);
    }

    /**
     * Adds the given key-value pair to this module's resource list
     *
     * @param key The key to associate this resource with
     * @param value The resource value to associate with this key
     * */
    @Override
    public void putResource(@NotNull String key, Object value) {
        resources.put(key, value);
    }

    /**
     * Tells all the exportables currently in this module whether to export or not, by the given argument.
     *
     * @param shouldExport Whether all this module's exportables should export.
     * */
    public void propagateExport(boolean shouldExport) {
        for(Exportable exportable : exportables) {
            exportable.setExport(shouldExport);
        }

        for(Namespace ns : namespaces.values()) {
            ns.propagateExport(shouldExport);
        }
    }

    /**
     * Retrieves the map of this module's extra resources.
     *
     * @return A map of module's resources.
     * */
    @NotNull
    public HashMap<String, Object> getResources() {
        return resources;
    }

    /**
     * Retrieves this module's resource associated with the given key
     *
     * @param key The key associated with the resource to retrieve
     *
     * @return The retrieved resource associated with the given key, if it exists.
     * Returns <code>null</code> if the key is not present.
     * */
    public Object getResource(@NotNull String key) {
        return resources.get(key);
    }

    @Override
    public String toString() {
        return "Module [" + name + "]";
    }

    @Override
    public Collection<Exportable> getAllExportables() {
        ArrayList<Exportable> allExportables = new ArrayList<>();
        for(Namespace namespace : namespaces.values()) {
            allExportables.addAll(namespace.getFunctionManager().getAll());

            TagManager tagMgr = namespace.getTagManager();
            for(TagGroup<?> group : tagMgr.getGroups()) {
                allExportables.addAll(group.getAll());
            }

            allExportables.addAll(namespace.lootTables.getAll());

        }
        allExportables.addAll(exportables);
        return allExportables;
    }

    @Override
    public TypeManager getTypeManager(String namespace) {
        Namespace ns = (namespace != null) ? getNamespace(namespace) : minecraft;
        return ns.types;
    }

    @Override
    public TagManager getTagManager(String namespace) {
        Namespace ns = (namespace != null) ? getNamespace(namespace) : minecraft;
        return ns.tags;
    }
}
