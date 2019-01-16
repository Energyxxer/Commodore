package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TagCompound extends ComplexNBTTag {
    @NotNull
    private final ArrayList<@NotNull NBTTag> content = new ArrayList<>();

    public TagCompound() {
        this("");
    }

    public TagCompound(@NotNull String name) {
        super(name);
    }

    public TagCompound(@NotNull Collection<@NotNull NBTTag> tags) {
        this("", tags);
    }

    public TagCompound(@NotNull String name, @NotNull Collection<@NotNull NBTTag> tags) {
        super(name);
        this.addAll(tags);
    }

    public TagCompound(@NotNull NBTTag... tags) {
        this("", tags);
    }

    public TagCompound(@NotNull String name, @NotNull NBTTag... tags) {
        this(name, Arrays.asList(tags));
    }

    public NBTTag get(@NotNull String key) {
        for(NBTTag other : content) {
            if(other.name.equals(key)) return other;
        }
        return null;
    }

    public void remove(@NotNull String key) {
        content.removeIf(t -> t.name.equals(key));
    }

    @NotNull
    public TagCompound merge(@NotNull TagCompound other) {
        TagCompound merged = this.clone();
        for(NBTTag otherTag : other.getAllTags()) {
            if(merged.contains(otherTag.getName())) {
                NBTTag tag = merged.get(otherTag.getName());
                if(otherTag.getClass() == tag.getClass() && otherTag instanceof ComplexNBTTag) {
                    merged.remove(otherTag.getName());
                    if(otherTag instanceof TagCompound) {
                        merged.add(((TagCompound) tag).merge((TagCompound) otherTag));
                    } else if(otherTag instanceof TagList) {
                        merged.add(((TagList) tag).merge((TagList) otherTag));
                    }
                } else {
                    merged.remove(otherTag.getName());
                    merged.add(otherTag.clone());
                }
            } else {
                merged.add(otherTag.clone());
            }
        }
        return merged;
    }

    @Override
    public void add(@NotNull NBTTag tag) {
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
    public boolean contains(@NotNull String key) {
        for(NBTTag other : content) {
            if(other.name.equals(key)) return true;
        }
        return false;
    }

    @NotNull
    @Override
    public String getType() {
        return "TAG_Compound";
    }

    @NotNull
    @Override
    public String toHeadlessString() {
        StringBuilder sb = new StringBuilder("{");

        Iterator<NBTTag> it = content.iterator();
        while(it.hasNext()) {
            NBTTag tag = it.next();
            sb.append(tag.toHeaderString());
            sb.append(':');
            sb.append(tag.toHeadlessString());
            if(it.hasNext()) sb.append(',');
        }
        sb.append('}');

        return sb.toString();
    }

    @Override
    public List<@NotNull NBTTag> getAllTags() {
        return new ArrayList<>(content);
    }

    @NotNull
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
