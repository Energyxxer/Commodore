package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.defaults.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TagManager {

    private final Namespace namespace;

    private final HashMap<String, TagGroup<? extends Tag>> groups = new HashMap<>();
    public final TagGroup<BlockTag> blockTags;
    public final TagGroup<ItemTag> itemTags;
    public final TagGroup<EntityTypeTag> entityTypeTags;
    public final TagGroup<FunctionTag> functionTags;
    public final TagGroup<FluidTag> fluidTags;

    public TagManager(Namespace namespace) {
        this.namespace = namespace;

        put(this.blockTags = new TagGroup<>(namespace, BlockType.CATEGORY, "blocks", BlockTag.INSTANTIATOR));
        put(this.itemTags = new TagGroup<>(namespace, ItemType.CATEGORY, "items", ItemTag.INSTANTIATOR));
        put(this.entityTypeTags = new TagGroup<>(namespace, EntityType.CATEGORY, "entity_types", EntityTypeTag.INSTANTIATOR));
        put(this.functionTags = new TagGroup<>(namespace, FunctionReference.CATEGORY, "functions", FunctionTag.INSTANTIATOR));
        put(this.fluidTags = new TagGroup<>(namespace, FluidType.CATEGORY, "fluids", FluidTag.INSTANTIATOR));
    }

    public void put(TagGroup<? extends Tag> group) {
        groups.put(group.getCategory(), group);
    }

    public TagGroup<? extends Tag> createGroup(String category, String tagDirectory) {
        if(groups.containsKey(category)) return groups.get(category);
        TagGroup<? extends Tag> newGroup = new TagGroup<>(namespace, category, tagDirectory, GenericTag.INSTANTIATOR);
        put(newGroup);
        return newGroup;
    }

    public TagGroup<? extends Tag> getGroup(String category) {
        return groups.get(category);
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public void join(TagManager other) {
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
}
