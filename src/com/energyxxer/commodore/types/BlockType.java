package com.energyxxer.commodore.types;

public class BlockType extends Type {
    public BlockType(String namespace, String id) {
        super(namespace, id);
    }

    public BlockType(String raw) {
        super(raw);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
