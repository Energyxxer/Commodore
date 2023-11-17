package com.energyxxer.commodore.functionlogic.nbt;

import com.energyxxer.commodore.CommodoreException;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TagList extends ComplexNBTTag {
    @NotNull
    private final ArrayList<@NotNull NBTTag> content = new ArrayList<>();

    public TagList() {
        this("");
    }

    public TagList(@NotNull String name) {
        super(name);
    }

    public TagList(@NotNull Collection<@NotNull NBTTag> tags) {
        this("", tags);
    }

    public TagList(@NotNull String name, @NotNull Collection<@NotNull NBTTag> tags) {
        super(name);
        this.addAll(tags);
    }

    public TagList(@NotNull NBTTag... tags) {
        this("", tags);
    }

    public TagList(@NotNull String name, @NotNull NBTTag... tags) {
        this(name, Arrays.asList(tags));
    }

    @NotNull
    public TagList merge(@NotNull TagList other) {
        if(this.isEmpty()) return other.clone();
        if(other.isEmpty()) return this.clone();
        if(other.content.get(0).getType().equals(this.content.get(0).getType())) {
            TagList merged = this.clone();
            for(NBTTag tag : other.content) {
                merged.add(tag.clone());
            }
            return merged;
        } else throw new CommodoreException(CommodoreException.Source.NBT_TYPE_ERROR, "Unable to merge tag lists: Incompatible types: " + this.content.get(0).getType() + " and " + other.content.get(0).getType(), other);
    }

    @Override
    public void add(@NotNull NBTTag tag) {
        if(content.isEmpty() || content.get(0).getType().equals(tag.getType())) {
            content.add(tag);
        } else {
            throw new CommodoreException(CommodoreException.Source.NBT_TYPE_ERROR, "Unable to add tag of type " + tag.getType() + " to list of type " + content.get(0).getType() + "; Tag: " + tag, tag);
        }
    }

    public void ensureCapacity(int minCapacity) {
        content.ensureCapacity(minCapacity);
    }

    public NBTTag get(int index) {
        return content.get(index);
    }

    @Override
    public List<@NotNull NBTTag> getAllTags() {
        return new ArrayList<>(content);
    }

    @NotNull
    @Override
    public String getType() {
        return "TAG_List";
    }

    @NotNull
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
    public boolean contains(@NotNull String key) {
        return false;
    }

    @NotNull
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

    @Override
    public Iterator<? extends NBTTag> iterator() {
        return content.iterator();
    }
}
