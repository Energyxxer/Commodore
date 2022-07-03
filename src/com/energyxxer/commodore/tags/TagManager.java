package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.defaults.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TagManager {

    @NotNull
    private final Namespace namespace;

    @NotNull
    private final HashMap<String, TagGroup<? extends Tag>> groups = new HashMap<>();
    @NotNull
    public final TagGroup<BlockTag> blockTags;
    @NotNull
    public final TagGroup<ItemTag> itemTags;
    @NotNull
    public final TagGroup<EntityTypeTag> entityTypeTags;
    @NotNull
    public final TagGroup<FunctionTag> functionTags;
    @NotNull
    public final TagGroup<FluidTag> fluidTags;

    public TagManager(@NotNull Namespace namespace) {
        this.namespace = namespace;

        put(this.blockTags = new TagGroup<>(namespace, BlockType.CATEGORY, "blocks", BlockTag.INSTANTIATOR));
        put(this.itemTags = new TagGroup<>(namespace, ItemType.CATEGORY, "items", ItemTag.INSTANTIATOR));
        put(this.entityTypeTags = new TagGroup<>(namespace, EntityType.CATEGORY, "entity_types", EntityTypeTag.INSTANTIATOR));
        put(this.functionTags = new TagGroup<>(namespace, FunctionReference.CATEGORY, "functions", FunctionTag.INSTANTIATOR));
        put(this.fluidTags = new TagGroup<>(namespace, FluidType.CATEGORY, "fluids", FluidTag.INSTANTIATOR));
    }

    public void put(@NotNull TagGroup<? extends Tag> group) {
        groups.put(group.getCategory(), group);
    }

    @NotNull
    public TagGroup<? extends Tag> createGroup(@NotNull String category, @NotNull String tagDirectory) {
        category = namespace.resolveAlias(category);
        if(groups.containsKey(category)) return groups.get(category);
        TagGroup<? extends Tag> newGroup = new TagGroup<>(namespace, category, tagDirectory, GenericTag.INSTANTIATOR);
        put(newGroup);
        return newGroup;
    }

    public TagGroup<? extends Tag> getGroup(@NotNull String category) {
        category = namespace.resolveAlias(category);
        return groups.get(category);
    }

    @NotNull
    public Namespace getNamespace() {
        return namespace;
    }

    public void join(@NotNull TagManager other) {
        for(Map.Entry<String, TagGroup<? extends Tag>> entry : other.groups.entrySet()) {
            String category = entry.getKey();
            TagGroup<? extends Tag> group = entry.getValue();
            TagGroup<? extends Tag> thisGroup = this.createGroup(category, group.getDirectoryName());
            thisGroup.join(group);
        }
    }

    public Collection<TagGroup<? extends Tag>> getGroups() {
        return groups.values();
    }

    public boolean groupExists(@NotNull String category) {
        category = namespace.resolveAlias(category);
        return groups.containsKey(category);
    }

    /**
     * Tells all the exportables currently in this tag manager whether to export or not, by the given argument.
     *
     * @param shouldExport Whether all this tag manager's exportables should export.
     * */
    public void propagateExport(boolean shouldExport) {
        for(TagGroup group : this.groups.values()) {
            group.propagateExport(shouldExport);
        }
    }
}
