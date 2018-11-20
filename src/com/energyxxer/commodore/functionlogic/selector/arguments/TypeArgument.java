package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.types.Type;

import java.util.Collection;

import static com.energyxxer.commodore.types.TypeAssert.assertEntity;

public class TypeArgument implements SelectorArgument {
    private final Type type;
    private final boolean negated;

    public TypeArgument(Type type) {
        this(type, false);
    }

    public TypeArgument(Type type, boolean negated) {
        this.type = type;
        this.negated = negated;

        assertEntity(type);
    }

    public Type getType() {
        return type;
    }

    public boolean isNegated() {
        return negated;
    }

    @Override
    public String getArgumentString() {
        return "type=" + (negated ? "!" : "") + type;
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
    public void assertCompatibleWith(Selector selector) {
        Collection<SelectorArgument> typeArgs = selector.getArgumentsByKey(getKey());
        for(SelectorArgument arg : typeArgs) {
            TypeArgument that = (TypeArgument) arg;

            if(    (!this.isNegated() &&
                    this.getType().isStandalone() &&
                    !that.isNegated() &&
                    that.getType().isStandalone() &&
                    this.getType() != that.getType())
                    ||
                   (this.getType() == that.getType() &&
                    this.isNegated() != that.isNegated()))
                throw new IllegalArgumentException("Impossible selector");
            //this is positive standalone, that is positive standalone, not equal //impossible
            //equal in value, opposite in polarity //impossible
        }
        SelectorArgument.super.assertCompatibleWith(selector);
    }
}
