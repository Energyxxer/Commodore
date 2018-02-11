package com.energyxxer.commodore.commands.team;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.textcomponents.TextColor;
import com.energyxxer.commodore.types.TeamReference;
import org.jetbrains.annotations.NotNull;

public class TeamOptionCommand extends TeamCommand {

    enum ValueType {
        APPLIES_TO(AppliesTo.class), BOOLEAN(Boolean.class), COLOR(TextColor.class);

        Class _class;

        ValueType(Class _class) {
            this._class = _class;
        }

        boolean isValidValue(Object o) {
            return _class.isInstance(o);
        }
    }

    public enum TeamOptionKey {
        COLLISION_RULE("collisionRule", ValueType.APPLIES_TO, "push"),
        COLOR("color", ValueType.COLOR),
        DEATH_MESSAGE_VISIBILITY("deathMessageVisibility", ValueType.APPLIES_TO, "hideFor", true),
        FRIENDLY_FIRE("friendlyFire", ValueType.BOOLEAN),
        NAMETAG_VISIBILITY("nametagVisibility", ValueType.APPLIES_TO, "hideFor", true),
        SEE_FRIENDLY_INVISIBLES("seeFriendlyInvisibles", ValueType.BOOLEAN);

        private final String argumentKey;
        private final ValueType valueType;
        private final String valueVerb;
        private final boolean teamValueInverted;

        TeamOptionKey(String argumentKey, ValueType valueType) {
            this(argumentKey, valueType, null);
        }

        TeamOptionKey(String argumentKey, ValueType valueType, String valueVerb) {
            this(argumentKey, valueType, valueVerb, false);
        }

        TeamOptionKey(String argumentKey, ValueType valueType, String valueVerb, boolean teamValueInverted) {
            this.argumentKey = argumentKey;
            this.valueType = valueType;
            this.valueVerb = valueVerb;
            this.teamValueInverted = teamValueInverted;
        }

        public String getValueVerb() {
            return valueVerb;
        }

        public boolean isTeamValueInverted() {
            return teamValueInverted;
        }

        public String getArgumentKey() {
            return argumentKey;
        }
    }

    public enum AppliesTo {
        ALL("always"), OWN_TEAM("%OwnTeam"), OTHER_TEAMS("%OtherTeams"), NONE("never");

        private final String valueString;

        AppliesTo(String valueString) {
            this.valueString = valueString;
        }

        public AppliesTo getTeamInverted() {
            if(this == OWN_TEAM) return OTHER_TEAMS;
            else if(this == OTHER_TEAMS) return OWN_TEAM;
            return this;
        }

        public String getValueString() {
            return valueString;
        }
    }

    private final TeamReference reference;
    private final TeamOptionKey key;
    private final Object value;

    public TeamOptionCommand(TeamReference reference, TeamOptionKey key, AppliesTo value) {
        this(reference, key, (Object) value);
    }

    public TeamOptionCommand(TeamReference reference, TeamOptionKey key, boolean value) {
        this(reference, key, (Object) value);
    }

    public TeamOptionCommand(TeamReference reference, TeamOptionKey key, TextColor value) {
        this(reference, key, (Object) value);
    }

    private TeamOptionCommand(TeamReference reference, TeamOptionKey key, Object value) {
        this.reference = reference;
        this.key = key;
        if (key.valueType.isValidValue(value)) {
            this.value = value;
        } else throw new IllegalArgumentException("'" + value + "' is not a valid argument type for team option '" + key + "': expected value of type '" + key.valueType + "'");
    }

    private String getValueString() {
        if(value instanceof AppliesTo) {
            AppliesTo usedValue = (AppliesTo) value;
            if (key.isTeamValueInverted()) usedValue = ((AppliesTo) value).getTeamInverted();

            return usedValue.getValueString().replace("%", key.getValueVerb());
        } else if(value instanceof Boolean) {
            return ((Boolean) value).toString();
        } else if(value instanceof TextColor) {
            return value.toString().toLowerCase();
        } else return null;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "team option " + reference + " " + key.getArgumentKey() + " " + getValueString());
    }
}
