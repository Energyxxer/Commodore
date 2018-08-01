package com.energyxxer.commodore.module.options;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.module.CommandModule;

/**
 * Enumeration for the possible values of the {@link ModuleOptionManager#UNUSED_COMMAND_POLICY} option for
 * command modules.
 *
 * @see ModuleOptionManager
 * @see CommandModule
 * @see Command
 * */
public enum UnusedCommandPolicy {
    KEEP, COMMENT_OUT, REMOVE
}
