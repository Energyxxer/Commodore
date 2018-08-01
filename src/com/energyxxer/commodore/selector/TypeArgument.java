package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.defaults.EntityType;

import java.util.Collection;

public class TypeArgument implements SelectorArgument {
    private final EntityType type;
    private final boolean negated;

    public TypeArgument(EntityType type) {
        this(type, false);
    }

    public TypeArgument(EntityType type, boolean negated) {
        this.type = type;
        this.negated = negated;
    }

    public EntityType getType() {
        return type;
    }

    public boolean isNegated() {
        return negated;
    }

    @Override
    public String getArgumentString() {
        return "type=" + type;
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public String toString() {
        return getArgumentString();
    }

    @Override
    public TypeArgument clone() {
        return new TypeArgument(type, negated);
    }

    @Override
    public String getKey() {
        return "type";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    @Override
    public boolean isCompatibleWith(Selector selector) {
        Collection<SelectorArgument> typeArgs = selector.getArgumentsByKey(getKey());
        for(SelectorArgument arg : typeArgs) {
            TypeArgument that = (TypeArgument) arg;

            if((!this.isNegated() && this.getType().isStandalone() && !that.isNegated() && that.getType().isStandalone() && this.getType() != that.getType()) || (this.getType() == that.getType() && this.isNegated() != that.isNegated())) throw new IllegalArgumentException("Impossible selector");
            //this is positive standalone, that is positive standalone, not equal //NO
            //equal in value, opposite in polarity //NO
        }
        return SelectorArgument.super.isCompatibleWith(selector);
    }
}
