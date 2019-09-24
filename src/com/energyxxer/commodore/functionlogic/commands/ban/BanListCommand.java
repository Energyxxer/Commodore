package com.energyxxer.commodore.functionlogic.commands.ban;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class BanListCommand implements Command {
    public enum QueryType {
        PLAYERS, IPS
    }

    @Nullable
    private final QueryType query;

    public BanListCommand() {
        this(null);
    }

    public BanListCommand(@Nullable QueryType query) {
        this.query = query;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "banlist" + (query != null ? " " + query.toString().toLowerCase(Locale.ENGLISH) : ""));
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("server_commands");
    }
}
