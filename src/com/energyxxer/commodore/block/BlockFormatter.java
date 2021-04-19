package com.energyxxer.commodore.block;

public class BlockFormatter {
    public static String asSet(Block block) {
        if (block instanceof DVBlock) {
            return block.getBlockType().toSafeStringExcludeMinecraftNamespace() + " " + Math.max(((DVBlock) block).getDataValue(), 0);
        } else return block.toString();
    }

    public static String asMatch(Block block) {
        if (block instanceof DVBlock) {
            return block.getBlockType().toSafeStringExcludeMinecraftNamespace() + " " + ((DVBlock) block).getDataValue();
        } else return block.toString();
    }

    public static String asMatch(Block block, String embedded, boolean doNotEmbedIfLast) {
        if (embedded == null) return asMatch(block);
        if (block instanceof DVBlock) {
            return block.getBlockType().toSafeStringExcludeMinecraftNamespace() + " " + embedded + " " + ((DVBlock) block).getDataValue();
        } else {
            if(doNotEmbedIfLast) return block.toString();
            else return block.toString() + " " + embedded;
        }
    }
}
