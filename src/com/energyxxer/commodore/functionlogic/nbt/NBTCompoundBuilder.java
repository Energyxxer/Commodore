package com.energyxxer.commodore.functionlogic.nbt;

import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPathIndex;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPathKey;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPathNode;
import org.jetbrains.annotations.NotNull;

public class NBTCompoundBuilder {
    @NotNull
    private TagCompound compound;
    @NotNull
    private ComplexNBTTag lastTag;

    public NBTCompoundBuilder() {
        this.compound = new TagCompound();
        lastTag = compound;
    }

    public void put(@NotNull NBTPath path, @NotNull NBTTag tag) {
        NBTPathNode previous = null;

        for(NBTPath subPath : path) {
            NBTPathNode node = subPath.getNode();

            if(previous != null) {
                if(node instanceof NBTPathKey) {
                    //Previous node is a compound
                    TagCompound newTag = new TagCompound(previous.getPathString());
                    lastTag.add(newTag);
                    lastTag = newTag;
                } else if(node instanceof NBTPathIndex) {
                    //Previous node is a list
                    TagList newTag = new TagList(previous.getPathString());
                    //TODO: index
                    lastTag = newTag;
                }
            }

            previous = node;

            if(!subPath.hasNext()) {
                if(node instanceof NBTPathKey) tag.setName(node.getPathString());
                lastTag.add(tag);
            }
        }
    }

    @NotNull
    public TagCompound getCompound() {
        return compound;
    }
}
