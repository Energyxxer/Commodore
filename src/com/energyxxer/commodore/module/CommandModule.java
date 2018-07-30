package com.energyxxer.commodore.module;

import com.energyxxer.commodore.defpacks.DefinitionPack;
import com.energyxxer.commodore.module.options.ModuleOptionManager;
import com.energyxxer.commodore.score.ObjectiveManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandModule {

    protected final String name;
    protected String description;
    protected final String prefix;

    protected ModuleOptionManager optMgr;

    protected final ObjectiveManager objMgr;

    protected final HashMap<String, Namespace> namespaces = new HashMap<>();
    public final Namespace minecraft;

    public CommandModule(String name, String prefix) {
        this(name, null, prefix);
    }

    public CommandModule(String name, String description, String prefix) {
        this.name = name;
        this.description = description;
        this.prefix = prefix;

        this.objMgr = new ObjectiveManager(this);

        this.minecraft = createNamespace("minecraft");

        optMgr = new ModuleOptionManager();
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectiveManager getObjectiveManager() {
        return objMgr;
    }

    public ModuleOptionManager getOptionManager() {
        return optMgr;
    }

    public void compile(File directory, ModulePackGenerator.OutputType outputType) {
        objMgr.compile();
        namespaces.values().forEach(Namespace::compile);

        try {
            new ModulePackGenerator(this, directory, outputType);
        } catch(IOException x) {
            x.printStackTrace();
        }
    }

    public Namespace createNamespace(String name) {
        Namespace alreadyExisting = namespaces.get(name);
        if(alreadyExisting != null) return alreadyExisting;

        Namespace newNamespace = new Namespace(this, name);
        namespaces.put(name, newNamespace);
        return newNamespace;
    }

    public Namespace getNamespace(String name) {
        return namespaces.get(name);
    }

    public void join(CommandModule other) {
        for(Map.Entry<String, Namespace> ns : other.namespaces.entrySet()) {
            this.namespaces.putIfAbsent(ns.getKey(), ns.getValue().clone(this));
        }
    }

    public boolean namespaceExists(String name) {
        return namespaces.containsKey(name);
    }

    public void importDefinitions(DefinitionPack pack) throws IOException {
        pack.populate(this);
    }

    @Override
    public String toString() {
        return "Module [" + name + "]";
    }
}
