package com.energyxxer.commodore.module;

import com.energyxxer.commodore.CommodoreException;
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
    public static final String ALLOWED_NAMESPACE_REGEX = "[a-z0-9_\\.-]+";

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
     * @param name The name of this namespace.
     * */
    public Namespace(@NotNull String name) {
        this.name = name;

        if (!name.matches(ALLOWED_NAMESPACE_REGEX)) {
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Illegal namespace name: " + name);
        }

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
     * @return The newly created namespace.
     * */
    @NotNull
    public Namespace clone() {
        Namespace clone = new Namespace(name);

        clone.functions.join(this.functions);
        clone.tags.join(this.tags);
        clone.types.join(this.types);

        return clone;
    }

    public void join(Namespace other) {
        this.functions.join(other.functions);
        this.tags.join(other.tags);
        this.types.join(other.types);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Tells all the exportables currently in this namespace whether to export or not, by the given argument.
     *
     * @param shouldExport Whether all this namespace's exportables should export.
     * */
    public void propagateExport(boolean shouldExport) {
        functions.propagateExport(shouldExport);
        tags.propagateExport(shouldExport);
    }
}
