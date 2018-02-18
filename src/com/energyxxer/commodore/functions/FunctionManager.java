package com.energyxxer.commodore.functions;

import com.energyxxer.commodore.module.Namespace;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    public Function create(String name, boolean force) {
        name = name.toLowerCase();
        if(!contains(name)) return forceCreate(name);
        if(!force) {
            throw new IllegalArgumentException("A function by the name '" + name + "' already exists");
        } else {
            int i = 1;
            while(true) {
                String newName = name + "#" + i;
                if(!contains(newName)) return forceCreate(newName);
                i++;
            }
        }
    }

    public Function create(String name) {
        return create(name, false);
    }

    public Collection<Function> getAll() {
        return functions.values();
    }

    public void join(FunctionManager other) {
        for(Map.Entry<String, Function> entry : other.functions.entrySet()) {
            functions.putIfAbsent(entry.getKey(), entry.getValue());
        }
    }

    private Function forceCreate(String name) {
        Function newFunction = new Function(this, namespace, name);
        functions.put(name, newFunction);
        return newFunction;
    }
}
