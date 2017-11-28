package com.energyxxer.commodore.score;

import java.util.ArrayList;

public class ObjectiveManager {
    private ArrayList<Objective> objectives = new ArrayList<>();

    public ObjectiveManager() {
    }

    public Objective get(String name) {
        for(Objective objective : objectives) {
            if(objective.getName().equals(name)) return objective;
        }
        return forceCreate(name);
    }

    public boolean contains(String name) {
        for(Objective objective : objectives) {
            if(objective.getName().equals(name)) return true;
        }
        return false;
    }

    public Objective create(String name) {
        if(!contains(name)) return forceCreate(name);
        throw new IllegalArgumentException("An objective by the name '" + name + "' already exists");
    }

    public Objective create(String name, String type) {
        if(!contains(name)) return forceCreate(name, type);
        throw new IllegalArgumentException("An objective by the name '" + name + "' already exists");
    }

    private Objective forceCreate(String name) {
        return forceCreate(name, "dummy");
    }

    private Objective forceCreate(String name, String type) {
        Objective newObjective = new Objective(name, type);
        objectives.add(newObjective);
        return newObjective;
    }
}
