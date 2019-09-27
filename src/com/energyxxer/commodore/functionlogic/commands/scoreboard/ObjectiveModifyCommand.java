package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.textcomponents.TextComponent;
import org.jetbrains.annotations.NotNull;

public class ObjectiveModifyCommand implements Command {

    public enum ObjectiveRenderType {
        HEARTS("hearts"),
        INTEGER("integer");

        @NotNull
        private final String valueKey;

        ObjectiveRenderType(@NotNull String valueKey) {
            this.valueKey = valueKey;
        }

        @NotNull
        public String getValueKey() {
            return valueKey;
        }

        @Override
        public String toString() {
            return valueKey;
        }
    }

    public enum ObjectiveModifyKey {
        RENDER_TYPE("rendertype", ObjectiveRenderType.class),
        DISPLAY_NAME("displayname", TextComponent.class);

        @NotNull
        private final String argumentKey;
        @NotNull
        private final Class valueClass;

        ObjectiveModifyKey(@NotNull String argumentKey, @NotNull Class valueClass) {
            this.argumentKey = argumentKey;
            this.valueClass = valueClass;
        }

        @NotNull
        public String getArgumentKey() {
            return argumentKey;
        }

        @NotNull
        public Class getValueClass() {
            return valueClass;
        }

        public boolean isValidValue(Object o) {
            return valueClass.isInstance(o);
        }
    }

    @NotNull
    private final Objective objective;
    @NotNull
    private final ObjectiveModifyKey key;
    @NotNull
    private Object value;

    public ObjectiveModifyCommand(@NotNull Objective objective, @NotNull ObjectiveModifyKey key, @NotNull ObjectiveRenderType value) {
        this(objective, key, (Object) value);
    }

    public ObjectiveModifyCommand(@NotNull Objective objective, @NotNull ObjectiveModifyKey key, @NotNull TextComponent value) {
        this(objective, key, (Object) value);
    }

    private ObjectiveModifyCommand(@NotNull Objective objective, @NotNull ObjectiveModifyKey key, @NotNull Object value) {
        this.objective = objective;
        this.key = key;
        if (key.isValidValue(value)) {
            this.value = value;
        } else throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "'" + value + "' is not a valid argument type for team option '" + key + "': expected value of type '" + key.valueClass.getSimpleName() + "'", value);
    }

    @NotNull
    private String getValueString() {
        return value.toString();
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard objectives modify " + objective.toString() + " " + key.getArgumentKey() + " " + getValueString());
    }

    @Override
    public void assertAvailable() {
        if(value instanceof TextComponent) {
            ((TextComponent) value).assertAvailable();
        }
    }
}
