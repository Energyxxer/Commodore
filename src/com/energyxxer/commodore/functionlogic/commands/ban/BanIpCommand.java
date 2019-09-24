package com.energyxxer.commodore.functionlogic.commands.ban;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BanIpCommand implements Command {
    @NotNull
    private final String ip;
    @Nullable
    private final String reason;

    public BanIpCommand(@NotNull String ip) {
        this(ip, null);
    }

    public BanIpCommand(@NotNull String ip, @Nullable String reason) {
        this.ip = ip;
        this.reason = reason;

        if(!VersionFeatureManager.getBoolean("ips.accept_strings", false) && !ip.matches(VersionFeatureManager.getString("ips.regex", CommandUtils.IDENTIFIER_ALLOWED))) {
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "IP '" + ip + "' has illegal characters. Quoting is not supported in the target version and does not match regex: " + VersionFeatureManager.getString("ips.regex", CommandUtils.IDENTIFIER_ALLOWED), ip, "IP");
        }
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "ban-ip " + ip + " " + reason);
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("server_commands");
    }
}
