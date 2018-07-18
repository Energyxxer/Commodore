package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.Objective;
import com.energyxxer.commodore.textcomponents.TextComponent;
import org.jetbrains.annotations.NotNull;

public class ObjectiveModifyCommand implements Command {

    public enum ObjectiveRenderType {
        HEARTS("hearts"),
        INTEGER("integer");

        private String valueKey;

        ObjectiveRenderType(String valueKey) {
            this.valueKey = valueKey;
        }

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

        private String argumentKey;
        private Class valueClass;

        ObjectiveModifyKey(String argumentKey, Class valueClass) {
            this.argumentKey = argumentKey;
            this.valueClass = valueClass;
        }

        public String getArgumentKey() {
            return argumentKey;
        }

        public Class getValueClass() {
            return valueClass;
        }

        public boolean isValidValue(Object o) {
            return valueClass.isInstance(o);
        }
    }

    private Objective objective;
    private ObjectiveModifyKey key;
    private Object value;

    public ObjectiveModifyCommand(Objective objective, ObjectiveModifyKey key, ObjectiveRenderType value) {
        this(objective, key, (Object) value);
    }

    public ObjectiveModifyCommand(Objective objective, ObjectiveModifyKey key, TextComponent value) {
        this(objective, key, (Object) value);
    }

    private ObjectiveModifyCommand(Objective objective, ObjectiveModifyKey key, Object value) {
        this.objective = objective;
        this.key = key;
        if (key.isValidValue(value)) {
            this.value = value;
        } else throw new IllegalArgumentException("'" + value + "' is not a valid argument type for team option '" + key + "': expected value of type '" + key.valueClass.getSimpleName() + "'");
    }

    private String getValueString() {
        return value.toString();
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard objectives modify " + objective.getName() + " " + key.getArgumentKey() + " " + getValueString());
    }
}
