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
     * Describes whether empty functions should be exported.
     *
     * Default value is <code>false</code>
     * */
    public final ModuleOption<Boolean> EXPORT_EMPTY_FUNCTIONS = new ModuleOption<>("EXPORT_EMPTY_FUNCTIONS", false);
}
