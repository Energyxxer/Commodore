package com.energyxxer.commodore.functionlogic.commands.team;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.textcomponents.TextColor;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.types.defaults.TeamReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class TeamModifyCommand extends TeamCommand {

    public enum TeamModifyKey {
        COLLISION_RULE("collisionRule", AppliesTo.class, "push"),
        COLOR("color", TextColor.class),
        DEATH_MESSAGE_VISIBILITY("deathMessageVisibility", AppliesTo.class, "hideFor", true),
        DISPLAY_NAME("displayName", TextComponent.class),
        FRIENDLY_FIRE("friendlyFire", Boolean.class),
        NAMETAG_VISIBILITY("nametagVisibility", AppliesTo.class, "hideFor", true),
        PREFIX("prefix", TextComponent.class),
        SEE_FRIENDLY_INVISIBLES("seeFriendlyInvisibles", Boolean.class),
        SUFFIX("suffix", TextComponent.class);

        private final String argumentKey;
        private final Class valueClass;
        private final String valueVerb;
        private final boolean teamValueInverted;

        TeamModifyKey(String argumentKey, Class valueClass) {
            this(argumentKey, valueClass, null);
        }

        TeamModifyKey(String argumentKey, Class valueClass, String valueVerb) {
            this(argumentKey, valueClass, valueVerb, false);
        }

        TeamModifyKey(String argumentKey, Class valueClass, String valueVerb, boolean teamValueInverted) {
            this.argumentKey = argumentKey;
            this.valueClass = valueClass;
            this.valueVerb = valueVerb;
            this.teamValueInverted = teamValueInverted;
        }

        public boolean isValidValue(Object o) {
            return valueClass.isInstance(o);
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

        public Class getValueClass() {
            return valueClass;
        }

        public static TeamModifyKey getValueForKey(String key) {
            for(TeamModifyKey value : values()) {
                if(value.argumentKey.equals(key)) return value;
            }
            return null;
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

    @NotNull
    private final TeamReference reference;
    @NotNull
    private final TeamModifyKey key;
    @NotNull
    private final Object value;

    public TeamModifyCommand(TeamReference reference, TeamModifyKey key, AppliesTo value) {
        this(reference, key, (Object) value);
    }

    public TeamModifyCommand(TeamReference reference, TeamModifyKey key, boolean value) {
        this(reference, key, (Object) value);
    }

    public TeamModifyCommand(TeamReference reference, TeamModifyKey key, TextColor value) {
        this(reference, key, (Object) value);
    }

    public TeamModifyCommand(TeamReference reference, TeamModifyKey key, TextComponent value) {
        this(reference, key, (Object) value);
    }

    private TeamModifyCommand(@NotNull TeamReference reference, @NotNull TeamModifyKey key, @NotNull Object value) {
        this.reference = reference;
        this.key = key;
        if (key.isValidValue(value)) {
            this.value = value;
        } else throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "'" + value + "' is not a valid argument type for team option '" + key + "': expected value of type '" + key.valueClass.getSimpleName() + "'", value);
    }

    private String getValueString() {
        if(value instanceof AppliesTo) {
            AppliesTo usedValue = (AppliesTo) value;
            if (key.isTeamValueInverted()) usedValue = ((AppliesTo) value).getTeamInverted();

            return usedValue.getValueString().replace("%", key.getValueVerb());
        } else if(value instanceof Boolean) {
            return ((Boolean) value).toString();
        } else if(value instanceof TextColor) {
            return value.toString().toLowerCase(Locale.ENGLISH);
        } else if(value instanceof TextComponent) {
            return value.toString();
        } else return null;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "team modify " + reference + " " + key.getArgumentKey() + " " + getValueString());
    }
}
