package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.EntityType;

public class TypeArgument implements SelectorArgument {
    private EntityType type;
    private boolean negated;

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
}
