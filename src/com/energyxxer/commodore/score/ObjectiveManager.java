package com.energyxxer.commodore.score;

import com.energyxxer.commodore.module.CommandModule;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectiveManager {

    private final CommandModule owner;

    private HashMap<String, Objective> objectives = new HashMap<>();

    private ArrayList<LocalScore> localScoreLog = new ArrayList<>();

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
        if(!contains(name)) return forceCreate(name);
        throw new IllegalArgumentException("An objective by the name '" + name + "' already exists");
    }

    public Objective create(String name, String type) {
        if(!contains(name)) return forceCreate(name, type, false);
        throw new IllegalArgumentException("An objective by the name '" + name + "' already exists");
    }

    private Objective forceCreate(String name) {
        return forceCreate(name, "dummy", false);
    }

    private Objective forceCreate(String name, String type, boolean field) {
        Objective newObjective = new Objective(this, name, type, field);
        objectives.put(name, newObjective);
        return newObjective;
    }

    public CommandModule getOwner() {
        return owner;
    }

    public void registerLocalScore(LocalScore score) {
        if(!localScoreLog.contains(score)) localScoreLog.add(score);
    }

    @Override
    public String toString() {
        return "Objective Manager for " + owner + " (" + objectives.size() + " objective" + (objectives.size() == 1 ? "" : "s") + ")";
    }
}
