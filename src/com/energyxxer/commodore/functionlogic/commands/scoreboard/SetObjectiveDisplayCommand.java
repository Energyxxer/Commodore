package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.TypeAssert;
import com.energyxxer.commodore.types.defaults.ScoreDisplayType;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SetObjectiveDisplayCommand implements Command {

    @Deprecated
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

        @NotNull
        private final String argumentKey;

        ScoreDisplay(@NotNull String argumentKey) {
            this.argumentKey = argumentKey;
        }

        @NotNull
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

    public enum ScoreDisplaySort {
        DESCENDING,
        ASCENDING
    }

    @Nullable
    private final Objective objective;
    @NotNull
    private final Type slot;
    @Nullable
    private final ScoreDisplaySort sort;

    @Deprecated
    public SetObjectiveDisplayCommand(@NotNull ScoreDisplay slot) {
        this(null, slot);
    }

    public SetObjectiveDisplayCommand(@NotNull Type slot) {
        this(null, slot);
    }

    @Deprecated
    public SetObjectiveDisplayCommand(@Nullable Objective objective, @NotNull ScoreDisplay slot) {
        this(objective, slot, null);
    }

    public SetObjectiveDisplayCommand(@Nullable Objective objective, @NotNull Type slot) {
        this(objective, slot, null);
    }

    @Deprecated
    public SetObjectiveDisplayCommand(@Nullable Objective objective, @NotNull ScoreDisplay slot, @Nullable ScoreDisplaySort sort) {
        this.objective = objective;
        this.slot = new ScoreDisplayType(null, slot.argumentKey);
        this.sort = sort;
    }

    public SetObjectiveDisplayCommand(@Nullable Objective objective, @NotNull Type slot, @Nullable ScoreDisplaySort sort) {
        this.objective = objective;
        this.slot = slot;
        this.sort = sort;

        if(sort != null) {
            String hasOrder = slot.getProperty("has_order");
            if("false".equals(hasOrder)) {
                throw new CommodoreException(CommodoreException.Source.TYPE_ERROR, "The given display slot '" + slot + "' does not support display sort", slot);
            }
        }

        TypeAssert.assertType(slot, ScoreDisplayType.CATEGORY);
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard objectives setdisplay " + slot + (objective != null ? (" " + objective.getName() + (sort != null ? " " + sort.toString().toLowerCase() : "")) : ""));
    }

    @Override
    public void assertAvailable() {
        if(sort != null) {
            VersionFeatureManager.assertEnabled("command.scoreboard_display_sort");
        }
    }
}
