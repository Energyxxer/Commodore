package com.energyxxer.commodore.functionlogic.nbt;

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

    public TagList merge(TagList other) {
        if(this.isEmpty()) return other.clone();
        if(other.isEmpty()) return this.clone();
        if(other.content.get(0).getType().equals(this.content.get(0).getType())) {
            TagList merged = this.clone();
            for(NBTTag tag : other.content) {
                merged.add(tag.clone());
            }
            return merged;
        } else throw new IllegalArgumentException("Unable to merge tag lists: Incompatible types: " + this.content.get(0).getType() + " and " + other.content.get(0).getType());
    }

    @Override
    public void add(NBTTag tag) {
        if(content.isEmpty() || content.get(0).getType().equals(tag.getType())) {
            content.add(tag);
        } else {
            throw new IllegalArgumentException("Unable to add tag of type " + tag.getType() + " to list of type " + content.get(0).getType() + "; Tag: " + tag);
        }
    }

    @Override
    public Collection<NBTTag> getAllTags() {
        return new ArrayList<>(content);
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
    public int size() {
        return content.size();
    }

    @Override
    public boolean isEmpty() {
        return content.isEmpty();
    }

    @Override
    public boolean contains(String key) {
        for(NBTTag other : content) {
            if(other.name.equals(key)) return true;
        }
        return false;
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
