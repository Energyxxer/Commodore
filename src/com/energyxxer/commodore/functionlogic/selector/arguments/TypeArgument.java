package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import static com.energyxxer.commodore.types.TypeAssert.assertEntity;

public class TypeArgument implements SelectorArgument {
    @NotNull
    private final Type type;
    private final boolean negated;

    public TypeArgument(@NotNull Type type) {
        this(type, false);
    }

    public TypeArgument(@NotNull Type type, boolean negated) {
        this.type = type;
        this.negated = negated;

        assertEntity(type);
    }

    @NotNull
    public Type getType() {
        return type;
    }

    public boolean isNegated() {
        return negated;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "type=" + (negated ? "!" : "") + type.toSafeString();
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @NotNull
    @Override
    public TypeArgument clone() {
        return new TypeArgument(type, negated);
    }

    @NotNull
    @Override
    public String getKey() {
        return "type";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    @Override
    public void assertCompatibleWith(@NotNull Selector selector) {
        if(selector.getBase().getPlayer().getValue() && selector.getBase().getPlayer().isEnforced()) {
            throw new CommodoreException(CommodoreException.Source.SELECTOR_ERROR, "Option 'type' isn't applicable for a player-only selector", selector);
        }
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
                throw new CommodoreException(CommodoreException.Source.SELECTOR_ERROR, "Impossible selector", selector);
            //this is positive standalone, that is positive standalone, not equal //impossible
            //equal in value, opposite in polarity //impossible
        }
        SelectorArgument.super.assertCompatibleWith(selector);
    }

    @Override
    public String toString() {
        return getArgumentString();
    }
}
