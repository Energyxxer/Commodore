package com.energyxxer.commodore.functionlogic.functions;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.module.Namespace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FunctionManager {

    @NotNull
    private final Namespace namespace;

    @NotNull
    private final HashMap<@NotNull String, @NotNull Function> functions = new HashMap<>();

    public FunctionManager(@NotNull Namespace namespace) {
        this.namespace = namespace;
    }

    @NotNull
    public Function get(@NotNull String name) {
        Function existing = functions.get(name.toLowerCase(Locale.ENGLISH));

        return (existing != null) ? existing : forceCreate(name);
    }

    public boolean contains(@NotNull String name) {
        return functions.containsKey(name.toLowerCase(Locale.ENGLISH));
    }

    public Function create(@NotNull String name, boolean force) {
        return create(name, force, null);
    }

    public Function create(@NotNull String name, boolean force, @Nullable Entity sender) {
        name = name.toLowerCase();
        if(!contains(name)) return forceCreate(name, sender);
        if(!force) {
            throw new CommodoreException(CommodoreException.Source.DUPLICATION_ERROR, "A function by the name '" + name + "' already exists", name);
        } else {
            int i = 1;
            while(true) {
                String newName = name + "-" + i;
                if(!contains(newName)) return forceCreate(newName, sender);
                i++;
            }
        }
    }

    @NotNull
    public Function create(@NotNull String name) {
        return create(name, false, null);
    }

    public Collection<@NotNull Function> getAll() {
        return functions.values();
    }

    public void join(@NotNull FunctionManager other) {
        for(Map.Entry<@NotNull String, @NotNull Function> entry : other.functions.entrySet()) {
            functions.putIfAbsent(entry.getKey(), entry.getValue());
        }
    }

    @NotNull
    private Function forceCreate(@NotNull String name) {
        return forceCreate(name, null);
    }

    @NotNull
    private Function forceCreate(@NotNull String name, @Nullable Entity sender) {
        Function newFunction = new Function(this, namespace, name, sender);
        functions.put(name.toLowerCase(Locale.ENGLISH), newFunction);
        return newFunction;
    }
}
