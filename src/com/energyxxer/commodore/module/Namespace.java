package com.energyxxer.commodore.module;

import com.energyxxer.commodore.functions.FunctionManager;
import com.energyxxer.commodore.tags.TagManager;
import com.energyxxer.commodore.types.defaults.TypeManager;

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
    private final CommandModule owner;
    /**
     * The name of the namespace, which is used as a prefix to namespace-sensitive references such as blocks or
     * functions.
     * */
    final String name;

    /**
     * The function manager for this namespace.
     * */
    public final FunctionManager functions;
    /**
     * The tag manager for this namespace.
     * */
    public final TagManager tags;
    /**
     * The type manager for this namespace.
     * */
    public final TypeManager types;

    /**
     * Creates a namespace object for the given module and with the given name.
     *
     * @param owner The module this new namespace should belong to.
     * @param name The name of this namespace.
     * */
    public Namespace(CommandModule owner, String name) {
        this.owner = owner;
        this.name = name;

        this.functions = new FunctionManager(this);
        this.tags = new TagManager(this);
        this.types = new TypeManager(this);
    }

    /**
     * Runs the necessary procedures for compilation of the module this belongs to.
     * */
    void compile() {
        functions.resolveAccessLogs();
    }

    /**
     * Retrieves this namespace's owner module.
     *
     * @return The command module that owns this namespace.
     * */
    public CommandModule getOwner() {
        return owner;
    }

    /**
     * Retrieves this namespace's name.
     *
     * @return The name for this namespace.
     * */
    public String getName() {
        return name;
    }

    /**
     * Retrieves this namespace's function manager.
     *
     * @return The function manager for this namespace.
     * */
    public FunctionManager getFunctionManager() {
        return functions;
    }

    /**
     * Retrieves this namespace's tag manager.
     *
     * @return The tag manager for this namespace.
     * */
    public TagManager getTagManager() {
        return tags;
    }

    /**
     * Retrieves this namespace's type manager.
     *
     * @return The type manager for this namespace.
     * */
    public TypeManager getTypeManager() {
        return types;
    }

    /**
     * Creates a new namespace with the given command module as its owner, which contains all the data from this
     * namespace.
     *
     * @param newOwner The module that should own the cloned namespace.
     *
     * @return The newly created namespace.
     * */
    public Namespace clone(CommandModule newOwner) {
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
