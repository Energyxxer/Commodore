package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.Commodore;
import com.energyxxer.commodore.module.Exportable;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.module.Namespaced;
import com.energyxxer.commodore.types.IllegalTypeException;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
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
public abstract class Tag extends Type implements Exportable, Namespaced {

    @NotNull
    protected final TagGroup group;
    private OverridePolicy policy = OverridePolicy.DEFAULT_POLICY;
    private boolean export = true;

    public void join(Tag otherTag) {
        values.addAll(otherTag.values);
        valueModes.addAll(otherTag.valueModes);
        shouldExportValues.addAll(otherTag.shouldExportValues);
    }

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

    public enum TagValueMode {
        REQUIRED, OPTIONAL;

        public static final TagValueMode DEFAULT = REQUIRED;
    }

    private final ArrayList<Type> values = new ArrayList<>();
    private final ArrayList<TagValueMode> valueModes = new ArrayList<>();
    private final ArrayList<Boolean> shouldExportValues = new ArrayList<>();

    /**
     * Creates a tag of the specified category, namespace and name.
     *
     * @param category The category of this tag's types.
     * @param namespace The namespace this tag belongs to.
     * @param name The name of this tag.
     * */
    public Tag(@NotNull String category, @NotNull Namespace namespace, @NotNull String name, @NotNull TagGroup group) {
        super(category, namespace, name);
        this.group = group;
    }

    /**
     * Retrieves this tag's list of values it represents.
     *
     * @return The list of values contained in this tag.
     * */
    @NotNull
    public Collection<@NotNull Type> getValues() {
        return values;
    }

    @NotNull
    public OverridePolicy getOverridePolicy() {
        return policy;
    }

    public void setOverridePolicy(@NotNull OverridePolicy newPolicy) {
        this.policy = newPolicy;
    }

    @Override
    public boolean shouldExport() {
        return export;
    }

    @Override
    public void setExport(boolean export) {
        this.export = export;
    }

    @NotNull
    public TagGroup<?> getGroup() {
        return group;
    }

    /**
     * Adds a value to this tag's contents.
     *
     * @param value The value to add to this tag.
     *
     * @throws IllegalTypeException If the given value doesn't match this tag's category.
     * */
    public void addValue(@NotNull Type value) {
        addValue(value, true);
    }

    /**
     * Adds a value to this tag's contents.
     *
     * @param value The value to add to this tag.
     * @param shouldExport Whether this value should be exported with the tag file. If not specified, defaults to
     *                     <code>true</code>
     *
     * @throws IllegalTypeException If the given value doesn't match this tag's category.
     * */
    public void addValue(@NotNull Type value, boolean shouldExport) {
        addValue(value, TagValueMode.DEFAULT, shouldExport);
    }

    /**
     * Adds a value to this tag's contents.
     *
     * @param value The value to add to this tag.
     * @param valueMode The mode for this tag value (required/optional)
     * @throws IllegalTypeException If the given value doesn't match this tag's category.
     * */
    public void addValue(@NotNull Type value, TagValueMode valueMode) {
        addValue(value, valueMode, true);
    }

    /**
     * Adds a value to this tag's contents.
     *
     * @param value The value to add to this tag.
     * @param valueMode The mode for this tag value (required/optional)
     * @param shouldExport Whether this value should be exported with the tag file. If not specified, defaults to
     *                     <code>true</code>
     *
     * @throws IllegalTypeException If the given value doesn't match this tag's category.
     * */
    public void addValue(@NotNull Type value, TagValueMode valueMode, boolean shouldExport) {
        if(valueMode != TagValueMode.DEFAULT) {
            VersionFeatureManager.assertEnabled("type_tags.optional_values");
        }

        values.add(value);
        valueModes.add(valueMode);
        shouldExportValues.add(shouldExport);
    }

    /**
     * Removes a value from this tag's contents. Does nothing if the given value doesn't exist in this tag.
     *
     * @param value The value to remove from this tag.
     * */
    public void removeValue(@NotNull Type value) {
        int oldIndex;
        while((oldIndex = values.indexOf(value)) >= 0) {
            values.remove(oldIndex);
            valueModes.remove(oldIndex);
            shouldExportValues.remove(oldIndex);
        }
    }

    /**
     * Removes a value from this tag's contents, by the given index.
     *
     * @param index The index of the value to remove from this tag.
     * */
    public void removeValue(int index) {
        values.remove(index);
        valueModes.remove(index);
        shouldExportValues.remove(index);
    }

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
    public byte[] getContents() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject root = new JsonObject();
        JsonArray list = new JsonArray();
        root.add("values", list);

        for(int i = 0; i < values.size(); i++) {
            if(shouldExportValues.get(i)) {
                if(valueModes.get(i) == TagValueMode.DEFAULT) {
                    list.add(values.get(i).toString());
                } else {
                    JsonObject entry = new JsonObject();
                    entry.addProperty("id", values.get(i).toString());
                    entry.addProperty("required", false);
                    list.add(entry);
                }
            }
        }

        return gson.toJson(root).getBytes(Commodore.getDefaultEncoding());
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isStandalone() {
        return false;
    }

    @Override
    @NotNull
    public String getExportPath() {
        return "data/$NAMESPACE$/tags/" + getGroup().getDirectoryName() + "/" + getName() + ".json";
    }
}
