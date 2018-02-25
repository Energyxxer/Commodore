package com.energyxxer.commodore.nbt;

import java.util.*;

public class TagList extends ComplexNBTTag {
    private final ArrayList<NBTTag> content = new ArrayList<>();

    public TagList() {
        this("");
    }

    public TagList(String name) {
        super(name);
    }

    public TagList(Collection<NBTTag> tags) {
        this("", tags);
    }

    public TagList(String name, Collection<NBTTag> tags) {
        super(name);
        this.addAll(tags);
    }

    public TagList(NBTTag... tags) {
        this("", tags);
    }

    public TagList(String name, NBTTag... tags) {
        this(name, Arrays.asList(tags));
    }

    @Override
    public void add(NBTTag tag) {
        if(content.isEmpty() || content.get(0).getType().equals(tag.getType())) {
            content.add(tag);
        } else {
            throw new IllegalArgumentException("Unable to addCriterion tag of blockType " + tag.getType() + " to list of blockType " + content.get(0).getType() + "; Tag: " + tag);
        }
    }

    @Override
    public String getType() {
        return "TAG_List";
    }

    @Override
    public String toHeadlessString() {
        StringBuilder sb = new StringBuilder("[");

        Iterator<NBTTag> it = content.iterator();
        while(it.hasNext()) {
            NBTTag tag = it.next();
            sb.append(tag.toHeadlessString());
            if(it.hasNext()) sb.append(',');
        }
        sb.append(']');

        return sb.toString();
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

    @Override
    public TagList clone() {
        TagList copy = new TagList(name);
        content.forEach(t -> copy.add(t.clone()));
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagList tagList = (TagList) o;
        return Objects.equals(content, tagList.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
