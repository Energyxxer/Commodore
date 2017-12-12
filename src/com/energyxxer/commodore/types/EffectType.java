package com.energyxxer.commodore.types;

public class EffectType extends Type {
    public EffectType(String namespace, String id) {
        super(namespace, id);
    }
    public EffectType(String raw) {
        super(raw);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
