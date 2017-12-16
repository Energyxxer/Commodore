package com.energyxxer.commodore.entity;

import com.energyxxer.commodore.selector.Selector;

public class GenericEntity implements Entity {
    private Selector selector;

    public GenericEntity(Selector selector) {
        this.selector = selector;
    }

    @Override
    public Selector getSelector() {
        return selector;
    }

    @Override
    public String toString() {
        return selector.toString();
    }
}
