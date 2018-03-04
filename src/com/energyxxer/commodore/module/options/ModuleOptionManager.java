package com.energyxxer.commodore.module.options;

public class ModuleOptionManager {
    public final ModuleOption<UnusedCommandPolicy> UNUSED_COMMAND_POLICY = new ModuleOption<>("UNUSED_COMMAND_POLICY", UnusedCommandPolicy.REMOVE);
    public final ModuleOption<Boolean> EXPORT_ACCESS_LOGS = new ModuleOption<>("EXPORT_ACCESS_LOGS", false);
}
