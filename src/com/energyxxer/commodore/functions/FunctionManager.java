package com.energyxxer.commodore.functions;

import com.energyxxer.commodore.project.CommandModule;

import java.util.HashMap;

public class FunctionManager {

    private final CommandModule owner;

    private HashMap<String, Function> functions = new HashMap<>();

    public FunctionManager(CommandModule owner) {
        this.owner = owner;
    }

    public Function get(String name) {
        Function existing = functions.get(name);

        return (existing != null) ? existing : forceCreate(name);
    }

    public boolean contains(String name) {
        return functions.containsKey(name);
    }

    public Function create(String name) {
        if(!contains(name)) return forceCreate(name);
        throw new IllegalArgumentException("A function by the name '" + name + "' already exists");
    }

    private Function forceCreate(String name) {
        Function newFunction = new Function(this, name);
        functions.put(name, newFunction);
        return newFunction;
    }
}
