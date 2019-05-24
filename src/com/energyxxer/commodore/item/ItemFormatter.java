package com.energyxxer.commodore.item;

public class ItemFormatter {
    public static String asSet(Item item) {
        if (item instanceof DVItem) {
            return item.getItemType() + " " + Math.max(((DVItem) item).getDataValue(), 0);
        } else return item.toString();
    }

    public static String asMatch(Item item) {
        if (item instanceof DVItem) {
            return item.getItemType() + " " + ((DVItem) item).getDataValue();
        } else return item.toString();
    }

    public static String asMatch(Item item, String embedded) {
        if (embedded == null) return asMatch(item);
        if (item instanceof DVItem) {
            return item.getItemType() + " " + embedded + " " + embedded + " " + ((DVItem) item).getDataValue();
        } else return item.toString() + " " + embedded;
    }
}
