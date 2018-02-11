package com.energyxxer.commodore.module;

import com.energyxxer.commodore.score.ObjectiveManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class CommandModule {

    final String name;
    String description;
    final String prefix;

    private final ObjectiveManager objMgr;

    final HashMap<String, Namespace> namespaces = new HashMap<>();
    public final Namespace minecraft;

    public CommandModule(String name, String prefix) {
        this(name, null, prefix);
    }

    public CommandModule(String name, String description, String prefix) {
        this.name = name;
        this.description = description;
        this.prefix = prefix;

        this.objMgr = new ObjectiveManager(this);

        this.minecraft = getNamespace("minecraft");
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

    public void compile(File directory, ModulePackGenerator.OutputType outputType) {
        objMgr.compile();
        namespaces.values().forEach(Namespace::compile);

        try {
            new ModulePackGenerator(this, directory, outputType);
        } catch(IOException x) {
            x.printStackTrace();
        }
    }

    public Namespace getNamespace(String name) {
        Namespace alreadyExisting = namespaces.get(name);
        if(alreadyExisting != null) return alreadyExisting;

        Namespace newNamespace = new Namespace(this, name);
        namespaces.put(name, newNamespace);
        return newNamespace;
    }

    public boolean namespaceExists(String name) {
        return namespaces.containsKey(name);
    }

    @Override
    public String toString() {
        return "Module [" + name + "]";
    }
}
