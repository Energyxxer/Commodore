package com.energyxxer.commodore.item;

public class ItemFormatter {
    public static String asSet(Item item) {
        if (item instanceof DVItem) {
            return item.getItemType().toSafeStringExcludeMinecraftNamespace() + " " + Math.max(((DVItem) item).getDataValue(), 0);
        } else return item.toString();
    }
    public static String asSet(Item item, String embedded, boolean doNotEmbedIfLast) {
        if (embedded == null) return asSet(item);
        if (item instanceof DVItem) {
            return item.getItemType().toSafeStringExcludeMinecraftNamespace() + " " + embedded + " " + Math.max(((DVItem) item).getDataValue(), 0);
        } else {
            if(doNotEmbedIfLast) return item.toString();
            else return item.toString() + " " + embedded;
        }
    }

    public static String asMatch(Item item) {
        if (item instanceof DVItem) {
            return item.getItemType().toSafeStringExcludeMinecraftNamespace() + " " + ((DVItem) item).getDataValue();
        } else return item.toString();
    }

    public static String asMatch(Item item, String embedded, boolean doNotEmbedIfLast) {
        if (embedded == null) return asMatch(item);
        if (item instanceof DVItem) {
            return item.getItemType().toSafeStringExcludeMinecraftNamespace() + " " + embedded + " " + ((DVItem) item).getDataValue();
        } else {
            if(doNotEmbedIfLast) return item.toString();
            else return item.toString() + " " + embedded;
        }
    }
}
