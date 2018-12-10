package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Exportable;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.IllegalTypeException;
import com.energyxxer.commodore.types.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Defines a Minecraft tag that represents various elements of the same category, and can be used to check if an element
 * is one of various types at once.
 * */
public abstract class Tag extends Type implements Exportable {

    /**
     * Describes how a tag conflict between two data packs should be handled.
     * */
    public enum OverridePolicy {
        /**
         * Specifies that, if this tag is loaded after another tag in a different data pack with the same namespace and
         * name, the previous values the tag had should be removed and, in their place should be the values of this tag.
         * */
        REPLACE(true),
        /**
         * Specifies that, if this tag is loaded after another tag in a different data pack with the same namespace and
         * name, the previous values should be kept, and this tag's values should be added to the already-existing tags.
         * */
        APPEND(false);

        /**
         * The boolean value that the "replace" element in the exported json file should have in order to mimic this
         * policy's behavior.
         * */
        public final boolean valueBool;

        /**
         * The override policy that describes the default behavior of a tag when it doesn't have the "replace" element
         * explicit in its json file, as of Minecraft 1.13.
         * */
        public static final OverridePolicy DEFAULT_POLICY = APPEND;

        /**
         * Creates an override policy that describes the behavior of a tag when the "replace" element of a tag is the
         * specified boolean value.
         *
         * @param valueBool The boolean value that the "replace" element in the exported json file should have in order
         *                  to mimic this policy's behavior.
         * */
        OverridePolicy(boolean valueBool) {
            this.valueBool = valueBool;
        }

        /**
         * Obtains the policy that corresponds to the given boolean value of the "replace" element of a tag.
         *
         * @param bool The boolean value of the "replace" element in the json file of which to get the corresponding
         *             override policy.
         *
         * @return The override policy associated with the given boolean value.
         * */
        public static OverridePolicy valueOf(boolean bool) {
            return bool ? REPLACE : APPEND;
        }
    }

    /**
     * Creates a tag of the specified category, namespace and name.
     *
     * @param category The category of this tag's types.
     * @param namespace The namespace this tag belongs to.
     * @param name The name of this tag.
     * */
    public Tag(@NotNull String category, @NotNull Namespace namespace, @NotNull String name) {
        super(category, namespace, name);
    }

    /**
     * Retrieves this tag's list of values it represents.
     *
     * @return The list of values contained in this tag.
     * */
    @NotNull
    public abstract ArrayList<@NotNull Type> getValues();

    /**
     * Retrieves this tag's override policy. That is, how this tag handles conflicts with same-identifier tags from
     * different data packs.
     *
     * @return The override policy for this tag.
     * */
    @NotNull
    public abstract OverridePolicy getOverridePolicy();

    /**
     * Changes this tag's override policy to the one specified.
     *
     * @param newPolicy The new override policy for this tag.
     * */
    public abstract void setOverridePolicy(@NotNull OverridePolicy newPolicy);

    /**
     * Retrieves this tag's parent group.
     *
     * @return The group this tag belongs to.
     * */
    @NotNull
    public abstract TagGroup<?> getGroup();

    /**
     * Adds a value to this tag's contents.
     *
     * @param value The value to add to this tag.
     *
     * @throws IllegalTypeException If the given value doesn't match this tag's category.
     * */
    public abstract void addValue(@NotNull Type value);

    /**
     * Adds a collection of values to this tag's contents.
     *
     * @param values The values to add to this tag.
     *
     * @throws IllegalTypeException If any of the given values don't match this tag's category.
     * */
    public void addValues(@NotNull Collection<@NotNull Type> values) {
        values.forEach(this::addValue);
    }

    @Override
    @NotNull
    public String getContents() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject root = new JsonObject();
        JsonArray list = new JsonArray();
        root.add("values", list);

        for(Type value : getValues()) {
            list.add(value.toString());
        }

        return gson.toJson(root);
    }

    @Override
    @NotNull
    public String getExportPath() {
        return "tags/" + getGroup().getDirectoryName() + "/" + getName() + ".json";
    }
}
