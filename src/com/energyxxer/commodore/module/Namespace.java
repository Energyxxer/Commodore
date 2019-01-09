package com.energyxxer.commodore.module;

import com.energyxxer.commodore.functionlogic.functions.FunctionManager;
import com.energyxxer.commodore.loottables.LootTableManager;
import com.energyxxer.commodore.tags.TagManager;
import com.energyxxer.commodore.types.defaults.TypeManager;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a namespace inside a command module, containing all the elements that belong to it, including, but not
 * limited to, types, tags and functions.
 *
 * @see CommandModule
 * @see FunctionManager
 * @see TagManager
 * @see TypeManager
 * */
public class Namespace {
    /**
     * The command module this namespace belongs to.
     * */
    @NotNull
    private final CommandModule owner;
    /**
     * The name of the namespace, which is used as a prefix to namespace-sensitive references such as blocks or
     * functions.
     * */
    @NotNull
    final String name;

    /**
     * The function manager for this namespace.
     * */
    @NotNull
    public final FunctionManager functions;
    /**
     * The tag manager for this namespace.
     * */
    @NotNull
    public final TagManager tags;
    /**
     * The type manager for this namespace.
     * */
    @NotNull
    public final TypeManager types;
    /**
     * The loot table manager for this namespace.
     * */
    public final LootTableManager lootTables;

    /**
     * Creates a namespace object for the given module and with the given name.
     *
     * @param owner The module this new namespace should belong to.
     * @param name The name of this namespace.
     * */
    public Namespace(@NotNull CommandModule owner, @NotNull String name) {
        this.owner = owner;
        this.name = name;

        this.functions = new FunctionManager(this);
        this.tags = new TagManager(this);
        this.types = new TypeManager(this);
        this.lootTables = new LootTableManager(this);
    }

    /**
     * Runs the necessary procedures for compilation of the module this belongs to.
     * */
    void compile() {
    }

    /**
     * Retrieves this namespace's owner module.
     *
     * @return The command module that owns this namespace.
     * */
    @NotNull
    public CommandModule getOwner() {
        return owner;
    }

    /**
     * Retrieves this namespace's name.
     *
     * @return The name for this namespace.
     * */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Retrieves this namespace's function manager.
     *
     * @return The function manager for this namespace.
     * */
    @NotNull
    public FunctionManager getFunctionManager() {
        return functions;
    }

    /**
     * Retrieves this namespace's tag manager.
     *
     * @return The tag manager for this namespace.
     * */
    @NotNull
    public TagManager getTagManager() {
        return tags;
    }

    /**
     * Retrieves this namespace's type manager.
     *
     * @return The type manager for this namespace.
     * */
    @NotNull
    public TypeManager getTypeManager() {
        return types;
    }

    /**
     * Retrieves this namespace's loot table manager.
     *
     * @return The loot table manager for this namespace.
     * */
    public LootTableManager getLootTableManager() {
        return lootTables;
    }

    /**
     * Creates a new namespace with the given command module as its owner, which contains all the data from this
     * namespace.
     *
     * @param newOwner The module that should own the cloned namespace.
     *
     * @return The newly created namespace.
     * */
    @NotNull
    public Namespace clone(@NotNull CommandModule newOwner) {
        Namespace clone = new Namespace(newOwner, name);

        clone.functions.join(this.functions);
        clone.tags.join(this.tags);
        clone.types.join(this.types);

        return clone;
    }

    @Override
    public String toString() {
        return name;
    }
}
