package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.Objective;
import org.jetbrains.annotations.NotNull;

public class SetObjectiveDisplayCommand implements Command {

    public enum ScoreDisplay {
        BELOW_NAME("belowName"),
        LIST("list"),
        SIDEBAR("sidebar"),
        SIDEBAR_TEAM_AQUA("sidebar.team.aqua"),
        SIDEBAR_TEAM_BLACK("sidebar.team.black"),
        SIDEBAR_TEAM_BLUE("sidebar.team.blue"),
        SIDEBAR_TEAM_DARK_AQUA("sidebar.team.dark_aqua"),
        SIDEBAR_TEAM_DARK_BLUE("sidebar.team.dark_blue"),
        SIDEBAR_TEAM_DARK_GRAY("sidebar.team.dark_gray"),
        SIDEBAR_TEAM_DARK_GREEN("sidebar.team.dark_green"),
        SIDEBAR_TEAM_DARK_PURPLE("sidebar.team.dark_purple"),
        SIDEBAR_TEAM_DARK_RED("sidebar.team.dark_red"),
        SIDEBAR_TEAM_GOLD("sidebar.team.gold"),
        SIDEBAR_TEAM_GRAY("sidebar.team.gray"),
        SIDEBAR_TEAM_GREEN("sidebar.team.green"),
        SIDEBAR_TEAM_LIGHT_PURPLE("sidebar.team.light_purple"),
        SIDEBAR_TEAM_RED("sidebar.team.red"),
        SIDEBAR_TEAM_WHITE("sidebar.team.white"),
        SIDEBAR_TEAM_YELLOW("sidebar.team.yellow");

        private final String argumentKey;

        ScoreDisplay(String argumentKey) {
            this.argumentKey = argumentKey;
        }

        private String getArgumentKey() {
            return argumentKey;
        }

        public static ScoreDisplay getValueForKey(String key) {
            for(ScoreDisplay value : values()) {
                if(value.argumentKey.equals(key)) return value;
            }
            return null;
        }
    }

    private final Objective objective;
    private final ScoreDisplay slot;

    public SetObjectiveDisplayCommand(ScoreDisplay slot) {
        this(null, slot);
    }

    public SetObjectiveDisplayCommand(Objective objective, ScoreDisplay slot) {
        this.objective = objective;
        this.slot = slot;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard objectives setdisplay " + slot.getArgumentKey() + (objective != null ? " " + objective.getName() : ""));
    }
}
