package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.defaults.BlockType;
import com.energyxxer.commodore.types.defaults.FunctionReference;
import com.energyxxer.commodore.types.defaults.ItemType;

import java.util.Collection;
import java.util.HashMap;

public class TagManager {

    private final Namespace namespace;

    private final HashMap<String, TagGroup<? extends Tag>> groups = new HashMap<>();
    private final TagGroup<BlockTag> blockTags;
    private final TagGroup<ItemTag> itemTags;
    private final TagGroup<FunctionTag> functionTags;

    public TagManager(Namespace namespace) {
        this.namespace = namespace;

        put(this.blockTags = new TagGroup<>(namespace, BlockType.CATEGORY, "blocks", BlockTag.INSTANTIATOR));
        put(this.itemTags = new TagGroup<>(namespace, ItemType.CATEGORY, "items", ItemTag.INSTANTIATOR));
        put(this.functionTags = new TagGroup<>(namespace, FunctionReference.CATEGORY, "functions", FunctionTag.INSTANTIATOR));
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

    public TagGroup<BlockTag> getBlockGroup() {
        return blockTags;
    }

    public TagGroup<ItemTag> getItemGroup() {
        return itemTags;
    }

    public TagGroup<FunctionTag> getFunctionGroup() {
        return functionTags;
    }

    public void join(TagManager other) {
        this.blockTags.join(other.blockTags);
        this.itemTags.join(other.itemTags);
        this.functionTags.join(other.functionTags);
    }

    public Collection<TagGroup<? extends Tag>> getGroups() {
        return groups.values();
    }
}
