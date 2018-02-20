package com.energyxxer.commodore.score;

import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.module.CommandModule;

import java.util.HashMap;

public class ObjectiveManager {

    private final CommandModule owner;

    private final HashMap<String, Objective> objectives = new HashMap<>();
    private Function creationFunction;

    private boolean usePrefix = false;

    public ObjectiveManager(CommandModule owner) {
        this.owner = owner;
    }

    public Objective get(String name) {
        Objective existing = objectives.get(name);

        return (existing != null) ? existing : forceCreate(name);
    }

    public boolean contains(String name) {
        return objectives.containsKey(name);
    }

    public Objective create(String name) {
        return create(name, "dummy");
    }

    public Objective create(String name, String type) {
        return create(name, type, false);
    }

    public Objective create(String name, boolean field) {
        return create(name, "dummy", field);
    }

    public Objective create(String name, String type, boolean field) {
        return create(name, type, null, field);
    }

    public Objective create(String name, String type, String displayName, boolean field) {
        if(!contains(name)) return forceCreate(name, type, displayName, field);
        throw new IllegalArgumentException("An objective by the name '" + name + "' already exists");
    }

    private Objective forceCreate(String name) {
        return forceCreate(name, "dummy", null, false);
    }

    private Objective forceCreate(String name, String type, String displayName, boolean field) {
        Objective newObjective = new Objective(this, name, type, displayName, field);
        objectives.put(name, newObjective);
        return newObjective;
    }

    public CommandModule getOwner() {
        return owner;
    }

    private void dumpObjectiveCreators(Function function) {
        objectives.values().forEach(o -> function.append(o.getObjectiveCreator()));
    }

    public void setCreationFunction(Function creationFunction) {
        this.creationFunction = creationFunction;
    }

    public void setPrefixEnabled(boolean prefixEnabled) {
        this.usePrefix = prefixEnabled;
    }

    public boolean isPrefixEnabled() {
        return usePrefix;
    }

    public void compile() {
        if(creationFunction != null) {
            dumpObjectiveCreators(creationFunction);
        }
    }

    @Override
    public String toString() {
        return "Objective Manager for " + owner + " (" + objectives.size() + " objective" + (objectives.size() == 1 ? "" : "s") + ")";
    }
}
