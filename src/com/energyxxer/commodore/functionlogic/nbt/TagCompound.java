package com.energyxxer.commodore.functionlogic.nbt;

import java.util.*;

public class TagCompound extends ComplexNBTTag {
    private final ArrayList<NBTTag> content = new ArrayList<>();

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

    public NBTTag get(String key) {
        for(NBTTag other : content) {
            if(other.name.equals(key)) return other;
        }
        return null;
    }

    public void remove(String key) {
        content.removeIf(t -> t.name.equals(key));
    }

    public TagCompound merge(TagCompound other) {
        TagCompound merged = this.clone();
        for(NBTTag tag : other.getAllTags()) {
            if(merged.contains(tag.getName())) {
                NBTTag otherTag = merged.get(tag.getName());
                if(tag.getClass() == otherTag.getClass() && tag instanceof ComplexNBTTag) {
                    merged.remove(tag.getName());
                    if(tag instanceof TagCompound) {
                        merged.add(((TagCompound) tag).merge((TagCompound) otherTag));
                    } else if(tag instanceof TagList) {
                        merged.add(((TagList) tag).merge((TagList) otherTag));
                    }
                } else {
                    merged.remove(tag.getName());
                    merged.add(tag.clone());
                }
            } else {
                merged.add(tag.clone());
            }
        }
        return merged;
    }

    @Override
    public void add(NBTTag tag) {
        for(NBTTag other : content) {
            if(other.getName().equals(tag.name)) {
                throw new IllegalArgumentException("Unable to add tag: '" + tag.name + "' already exists in compound");
            }
        }
        content.add(tag);
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

    @Override
    public Collection<NBTTag> getAllTags() {
        return new ArrayList<>(content);
    }

    @Override
    public TagCompound clone() {
        TagCompound copy = new TagCompound(name);
        content.forEach(t -> copy.add(t.clone()));
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagCompound that = (TagCompound) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
