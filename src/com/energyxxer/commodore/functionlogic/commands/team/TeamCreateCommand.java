package com.energyxxer.commodore.functionlogic.commands.team;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.textcomponents.StringTextComponent;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.types.defaults.TeamReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TeamCreateCommand extends TeamCommand {
    @NotNull
    private final TeamReference reference;
    @Nullable
    private final TextComponent displayName;

    public TeamCreateCommand(@NotNull TeamReference reference) {
        this(reference, (TextComponent) null);
    }

    public TeamCreateCommand(@NotNull TeamReference reference, @Nullable String displayName) {
        this(reference, displayName != null ? new StringTextComponent(displayName) : null);
    }

    public TeamCreateCommand(@NotNull TeamReference reference, @Nullable TextComponent displayName) {
        this.reference = reference;
        this.displayName = displayName;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "team add " + reference + ((displayName != null) ? " " + displayName : ""));
    }

    @Override
    public void assertAvailable() {
        if(displayName != null) displayName.assertAvailable();
    }
}
