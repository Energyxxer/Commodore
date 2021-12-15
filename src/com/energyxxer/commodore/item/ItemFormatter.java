package com.energyxxer.commodore.item;

public class ItemFormatter {
    public static String asSet(Item item) {
        return item.asSet();
    }
    public static String asSet(Item item, String embedded, boolean doNotEmbedIfLast) {
        return item.asSet(embedded, doNotEmbedIfLast);
    }

    public static String asMatch(Item item) {
        return item.asMatch();
    }

    public static String asMatch(Item item, String embedded, boolean doNotEmbedIfLast) {
        return item.asMatch(embedded, doNotEmbedIfLast);
    }
}
