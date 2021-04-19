package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TagArgument implements SelectorArgument {
    @Nullable
    private final String tag;
    private final boolean negated;

    public TagArgument(@Nullable String tag) {
        if(tag != null && tag.startsWith("!")) {
            this.tag = tag.substring(1);
            this.negated = true;
        } else {
            this.tag = tag;
            this.negated = false;
        }
    }

    public TagArgument(@Nullable String tag, boolean negated) {
        this.tag = tag;
        this.negated = negated;
        if(tag != null && !tag.matches(CommandUtils.IDENTIFIER_ALLOWED))
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Tag argument value '" + tag + "' has illegal characters. Does not match regex: " + CommandUtils.IDENTIFIER_ALLOWED, tag);
    }

    @Nullable
    public String getTag() {
        return tag;
    }

    public boolean isNegated() {
        return negated;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "tag=" + (negated ? "!" : "") + (tag != null ? CommandUtils.quoteIfNecessary(tag) : "");
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public String toString() {
        return getArgumentString();
    }

    @NotNull
    @Override
    public TagArgument clone() {
        return new TagArgument(tag, negated);
    }

    @NotNull
    @Override
    public String getKey() {
        return "tag";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
