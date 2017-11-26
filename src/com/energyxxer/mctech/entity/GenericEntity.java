package com.energyxxer.mctech.entity;

import com.energyxxer.mctech.selector.Selector;

public class GenericEntity implements Entity {
    private Selector selector;

    public GenericEntity(Selector selector) {
        this.selector = selector;
    }

    @Override
    public Selector getSelector() {
        return selector;
    }
}
