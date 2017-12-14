package com.energyxxer.commodore.module;

import com.energyxxer.commodore.score.ObjectiveManager;

import java.util.HashMap;

public class CommandModule {

    private String name;
    private String prefix;

    private ObjectiveManager objMgr;

    private HashMap<String, Namespace> namespaces = new HashMap<>();
    public final Namespace minecraft;

    public CommandModule(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;

        this.objMgr = new ObjectiveManager(this);

        this.minecraft = getNamespace("minecraft");
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public ObjectiveManager getObjectiveManager() {
        return objMgr;
    }

    public void compile() {
        objMgr.resolveAccessLogs();
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
