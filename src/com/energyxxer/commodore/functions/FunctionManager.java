package com.energyxxer.commodore.functions;

import com.energyxxer.commodore.module.Namespace;

import java.util.Collection;
import java.util.HashMap;

public class FunctionManager {

    private final Namespace namespace;

    private final HashMap<String, Function> functions = new HashMap<>();

    public FunctionManager(Namespace namespace) {
        this.namespace = namespace;
    }

    public Function get(String name) {
        Function existing = functions.get(name);

        return (existing != null) ? existing : forceCreate(name);
    }

    public void resolveAccessLogs() {
        functions.values().forEach(Function::resolveAccessLogs);
    }

    public boolean contains(String name) {
        return functions.containsKey(name);
    }

    public Function create(String name) {
        if(!contains(name)) return forceCreate(name);
        throw new IllegalArgumentException("A function by the name '" + name + "' already exists");
    }

    private Function forceCreate(String name) {
        Function newFunction = new Function(this, namespace, name);
        functions.put(name, newFunction);
        return newFunction;
    }

    public Collection<Function> getAll() {
        return functions.values();
    }
}
