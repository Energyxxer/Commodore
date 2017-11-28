package com.energyxxer.commodore.nbt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class TagCompound extends NBTTag {
    private ArrayList<NBTTag> content = new ArrayList<>();

    public TagCompound() {
        this("");
    }

    public TagCompound(String name) {
        super(name);
    }

    public TagCompound(Collection<NBTTag> tags) {
        this("", tags);
    }

    public TagCompound(String name, Collection<NBTTag> tags) {
        super(name);
        this.addAll(tags);
    }

    public TagCompound(NBTTag... tags) {
        this("", tags);
    }

    public TagCompound(String name, NBTTag... tags) {
        this(name, Arrays.asList(tags));
    }



    public void addAll(Collection<NBTTag> tags) {
        tags.forEach(this::add);
    }

    public void add(NBTTag tag) {
        content.add(tag);
    }

    @Override
    public String getType() {
        return "TAG_Compound";
    }

    @Override
    public String toHeadlessString() {
        StringBuilder sb = new StringBuilder("{");

        Iterator<NBTTag> it = content.iterator();
        while(it.hasNext()) {
            NBTTag tag = it.next();
            sb.append(tag.toString());
            if(it.hasNext()) sb.append(',');
        }
        sb.append('}');

        return sb.toString();
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }
}
