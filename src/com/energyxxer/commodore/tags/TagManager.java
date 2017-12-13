package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.CommandModule;

public class TagManager {

    private CommandModule owner;

    private String namespace;

    private TagGroup<BlockTag> blockTags;
    private TagGroup<ItemTag> itemTags;
    private TagGroup<FunctionTag> functionTags;

    public TagManager(CommandModule owner, String namespace) {
        this.owner = owner;
        this.namespace = namespace;

        this.blockTags = new TagGroup<>(namespace, "blocks", BlockTag.INSTANTIATOR);
        this.itemTags = new TagGroup<>(namespace, "items", ItemTag.INSTANTIATOR);
        this.functionTags = new TagGroup<>(namespace, "functions", FunctionTag.INSTANTIATOR);
    }

    public String getNamespace() {
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
}
