package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;

public class TagManager {

    private final Namespace namespace;

    private final TagGroup<BlockTag> blockTags;
    private final TagGroup<ItemTag> itemTags;
    private final TagGroup<FunctionTag> functionTags;

    public TagManager(Namespace namespace) {
        this.namespace = namespace;

        this.blockTags = new TagGroup<>(namespace, "blocks", BlockTag.INSTANTIATOR);
        this.itemTags = new TagGroup<>(namespace, "items", ItemTag.INSTANTIATOR);
        this.functionTags = new TagGroup<>(namespace, "functions", FunctionTag.INSTANTIATOR);
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
}
