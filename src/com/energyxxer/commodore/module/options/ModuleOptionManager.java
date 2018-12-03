package com.energyxxer.commodore.module.options;

import com.energyxxer.commodore.module.CommandModule;

/**
 * Contains each of the options or flags a command module uses.
 *
 * @see ModuleOption
 * @see CommandModule
 * */
public class ModuleOptionManager {
    /**
     * Describes how Commodore should handle commands that perform no actions, such as the first of two
     * consecutive <code>scoreboard players set</code> commands on the same entity. Possible values are:
     *
     * <ol>
     *     <li>{@link UnusedCommandPolicy#KEEP}: The unused commands will remain in the function.</li>
     *     <li>{@link UnusedCommandPolicy#COMMENT_OUT}: The unused command will not run, but will remain as a function
     *     comment.</li>
     *     <li>{@link UnusedCommandPolicy#REMOVE}: The unused command will not be added to the function.</li>
     * </ol>
     *
     * Default value is {@link UnusedCommandPolicy#REMOVE}.
     * */
    public final ModuleOption<UnusedCommandPolicy> UNUSED_COMMAND_POLICY = new ModuleOption<>("UNUSED_COMMAND_POLICY", UnusedCommandPolicy.REMOVE);
    /**
     * Describes whether scoreboard access logs should be exported. This is a debug feature used to evaluate how each
     * command reports its scoreboard accesses. If set to <code>true</code>, upon compiling the module,
     * <code>*.accesslog</code> files will be created alongside functions, detailing each scoreboard access for each
     * command and its resolution (whether it was used or unused).
     * */
    public final ModuleOption<Boolean> EXPORT_ACCESS_LOGS = new ModuleOption<>("EXPORT_ACCESS_LOGS", false);
    /**
     * Describes whether empty functions should be exported.
     *
     * Default value is <code>false</code>
     * */
    public final ModuleOption<Boolean> EXPORT_EMPTY_FUNCTIONS = new ModuleOption<>("EXPORT_EMPTY_FUNCTIONS", false);
}
