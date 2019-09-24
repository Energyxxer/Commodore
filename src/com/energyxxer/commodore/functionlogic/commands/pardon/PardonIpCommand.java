package com.energyxxer.commodore.functionlogic.commands.pardon;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class PardonIpCommand implements Command {
    @NotNull
    private final String ip;

    public PardonIpCommand(@NotNull String ip) {
        this.ip = ip;

        if(!VersionFeatureManager.getBoolean("ips.accept_strings", false) && !ip.matches(VersionFeatureManager.getString("ips.regex", CommandUtils.IDENTIFIER_ALLOWED))) {
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "IP '" + ip + "' has illegal characters. Quoting is not supported in the target version and does not match regex: " + VersionFeatureManager.getString("ips.regex", CommandUtils.IDENTIFIER_ALLOWED), ip, "IP");
        }
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "pardon-ip " + ip);
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("server_commands");
    }
}
