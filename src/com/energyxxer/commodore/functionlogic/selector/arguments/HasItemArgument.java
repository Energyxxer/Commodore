package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.util.IntegerRange;
import com.energyxxer.commodore.util.Negatable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HasItemArgument implements SelectorArgument {

    public Type item;
    public int data = -1;
    public Type location;
    public Negatable<IntegerRange> slot;
    public Negatable<IntegerRange> quantity;

    @Override
    public @NotNull String getArgumentString() {
        StringBuilder sb = new StringBuilder("hasitem={");
        boolean any = false;
        if(item != null) {
            sb.append("item=").append(item.toSafeString()).append(",");
            any = true;
        }
        if(data != -1) {
            sb.append("data=").append(data).append(",");
            any = true;
        }
        if(location != null) {
            sb.append("location=").append(location.toSafeString()).append(",");
            any = true;
        }
        if(slot != null) {
            sb.append("slot=").append(slot.toString()).append(",");
            any = true;
        }
        if(quantity != null) {
            sb.append("quantity=").append(quantity.toString()).append(",");
            any = true;
        }

        if(any) sb.setLength(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public @NotNull SelectorArgument clone() {
        HasItemArgument copy = new HasItemArgument();
        copy.item = item;
        copy.data = data;
        copy.location = location;
        copy.slot = slot;
        copy.quantity = quantity;
        return copy;
    }

    @Override
    public @NotNull String getKey() {
        return "hasitem";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.SENDER);
    }

    @Override
    public String toString() {
        return getArgumentString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasItemArgument that = (HasItemArgument) o;
        return data == that.data &&
                Objects.equals(item, that.item) &&
                Objects.equals(location, that.location) &&
                Objects.equals(slot, that.slot) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, data, location, slot, quantity);
    }
}
