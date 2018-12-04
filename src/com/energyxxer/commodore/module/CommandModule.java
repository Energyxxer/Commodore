package com.energyxxer.commodore.module;

import com.energyxxer.commodore.defpacks.DefinitionPack;
import com.energyxxer.commodore.defpacks.MalformedPackException;
import com.energyxxer.commodore.functionlogic.score.ObjectiveManager;
import com.energyxxer.commodore.module.options.ModuleOptionManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The main agent of Commodore, storing data which is used by and for data packs, including, but not limited to, blocks,
 * items, entities, functions, tags, etc. Command modules can be modified via other classes and compiled into data
 * packs, which can then be used in Minecraft.
 * */
public class CommandModule {
    /**
     * The name of this command module, which is used as the directory or file name for the compiled pack.
     * */
    protected String name;
    /**
     * The description of this command module, as seen in the compiled pack.mcmeta
     * */
    protected String description;
    /**
     * The prefix which should be used for special names in this module. Currently only used by objective names.
     * */
    protected final String prefix;

    /**
     * This module's option manager.
     * */
    protected ModuleOptionManager optMgr;

    /**
     * This module's objective manager.
     * */
    protected final ObjectiveManager objMgr;

    /**
     * A map of this objective's namespaces, where the key is the namespace string, and the value is the object of the
     * namespace whose name is the string in its key.
     * */
    protected final HashMap<String, Namespace> namespaces = new HashMap<>();

    /**
     * A list that contains extra exportable files that will be exported alongside the module when compiled.
     * */
    public final ArrayList<Exportable> exportables = new ArrayList<>();

    /**
     * This command module's default namespace, made a <code>public final</code> field for easy access.
     * */
    public final Namespace minecraft;

    /**
     * Creates a command module with the given name.
     *
     * @param name The name of the new module.
     * */
    public CommandModule(String name) {
        this(name, null);
    }

    /**
     * Creates a command module with the given name and prefix.
     *
     * @param name The name of the new module.
     * @param prefix The prefix used optionally by elements of the module to avoid conflicts.
     * */
    public CommandModule(String name, String prefix) {
        this(name, "Module Pack created programmatically with Commodore", prefix);
    }

    /**
     * Creates a command module with the given name, description and prefix.
     *
     * @param name The name of the new module.
     * @param description The description of the module, as to be displayed in the data pack's mcmeta file.
     * @param prefix The prefix used optionally by elements of the module to avoid conflicts.
     * */
    public CommandModule(String name, @NotNull String description, String prefix) {
        this.name = name;
        this.description = description;
        this.prefix = prefix;

        this.objMgr = new ObjectiveManager(this);

        this.minecraft = createNamespace("minecraft");

        optMgr = new ModuleOptionManager();
    }

    /**
     * Retrieves this module's name.
     *
     * @return The name for this module.
     * */
    public String getName() {
        return name;
    }

    /**
     * Retrieves this module's description.
     *
     * @return The description for this module.
     * */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves this module's prefix.
     *
     * @return The prefix for this module.
     * */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Changes this module's name.
     *
     * @param name The new name for this module.
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes this module's description.
     *
     * @param description The new description for this module.
     * */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves this module's objective manager.
     *
     * @return The objective manager for this module.
     * */
    public ObjectiveManager getObjectiveManager() {
        return objMgr;
    }

    /**
     * Retrieves this module's option manager.
     *
     * @return The option manager for this module.
     * */
    public ModuleOptionManager getOptionManager() {
        return optMgr;
    }

    /**
     * Generates a data pack with the module's current content.
     *
     * @param file The file inside which the data pack will be generated.
     *
     * @throws IOException If an IO error occurs during the generation of the data pack.
     * */
    public void compile(File file) throws IOException {
        objMgr.compile();
        namespaces.values().forEach(Namespace::compile);

        new ModulePackGenerator(this, file).generate();
    }

    /**
     * Creates a namespace with the given name, if it doesn't already exist.
     *
     * @param name The name of the new namespace.
     *
     * @return The newly created namespace, if it was created, or the previously existing namespace if one of the same
     * name was found.
     * */
    public Namespace createNamespace(String name) {
        Namespace alreadyExisting = namespaces.get(name);
        if(alreadyExisting != null) return alreadyExisting;

        Namespace newNamespace = new Namespace(this, name);
        namespaces.put(name, newNamespace);
        return newNamespace;
    }

    /**
     * Retrieves the namespace with the given name.
     *
     * @param name The name of the namespace to retrieve.
     *
     * @return This module's namespace of the given name if it exists; <code>null</code> otherwise.
     * */
    public Namespace getNamespace(String name) {
        return namespaces.get(name);
    }

    /**
     * Adds all the data of the specified module into this module.
     *
     * @param other The module whose data is to be copied over to this module.
     * */
    public void join(CommandModule other) {
        for(Map.Entry<String, Namespace> ns : other.namespaces.entrySet()) {
            this.namespaces.putIfAbsent(ns.getKey(), ns.getValue().clone(this));
        }
    }

    /**
     * Checks whether a namespace with the given name already exists.
     *
     * @param name The name of the namespace to check.
     *
     * @return <code>true</code> if a namespace of the given name exists in this module, <code>false</code> otherwise.
     * */
    public boolean namespaceExists(String name) {
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
    public void importDefinitions(DefinitionPack pack) throws IOException {
        pack.populate(this);
    }

    @Override
    public String toString() {
        return "Module [" + name + "]";
    }
}
