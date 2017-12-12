package com.energyxxer.commodore.tags;

import java.util.ArrayList;

public class TagGroup<T extends Tag> {
    private String groupName;
    private ArrayList<T> tags = new ArrayList<>();

    public TagGroup(String groupName) {
        this.groupName = groupName;
    }
}
