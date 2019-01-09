package com.energyxxer.commodore.functionlogic.entity;

import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.functionlogic.selector.arguments.LimitArgument;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a simple entity from a selector.
 *
 * Deprecated - use {@link Selector}
 * */
@Deprecated
public class GenericEntity implements Entity {
    /**
     * The selector this generic entity should utilize.
     * */
    @NotNull
    private final Selector selector;

    /**
     * Creates a generic entity with the given selector.
     *
     * @param selector The selector this generic entity should resolve to.
     * */
    public GenericEntity(@NotNull Selector selector) {
        this.selector = selector;
    }

    /**
     * Retrieves this generic entity's selector object. Any changes to the return value will be reflected in this
     * generic entity.
     *
     * @return The selector for this generic entity.
     * */
    @NotNull
    public Selector getSelector() {
        return selector;
    }

    @Override
    public int getLimit() {
        return selector.getLimit();
    }

    @NotNull
    @Override
    public String toString() {
        return selector.toString();
    }

    @Override
    public GenericEntity clone() {
        return new GenericEntity(selector.clone());
    }

    @Override
    public boolean isUnknownType() {
        return selector.isUnknownType();
    }

    @Override
    public boolean isPlayer() {
        return selector.isPlayer() || selector.getBase().equals(Selector.BaseSelector.SENDER);
    }

    @Override
    public GenericEntity limitToOne() {
        Selector newSelector = selector.clone();
        newSelector.addArgument(new LimitArgument(1));
        return new GenericEntity(newSelector);
    }
}
