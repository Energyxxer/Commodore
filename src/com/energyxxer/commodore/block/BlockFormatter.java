package com.energyxxer.commodore.block;

public class BlockFormatter {
    public static String asSet(Block block) {
        if (block instanceof DVBlock) {
            return block.getBlockType() + " " + Math.max(((DVBlock) block).getDataValue(), 0);
        } else return block.toString();
    }

    public static String asMatch(Block block) {
        if (block instanceof DVBlock) {
            return block.getBlockType() + " " + ((DVBlock) block).getDataValue();
        } else return block.toString();
    }

    public static String asMatch(Block block, String embedded) {
        if (embedded == null) return asMatch(block);
        if (block instanceof DVBlock) {
            return block.getBlockType() + " " + embedded + " " + embedded + " " + ((DVBlock) block).getDataValue();
        } else return block.toString() + " " + embedded;
    }
}
