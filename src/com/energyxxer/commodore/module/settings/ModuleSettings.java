package com.energyxxer.commodore.module.settings;

import com.energyxxer.commodore.module.options.ModuleOption;
import com.energyxxer.commodore.versioning.Version;

public class ModuleSettings {
    private Version targetVersion;
    /**
     * Describes whether empty functions should be exported.
     * <p>
     * Default value is <code>false</code>
     */
    public final ModuleOption<Boolean> EXPORT_EMPTY_FUNCTIONS = new ModuleOption<>("EXPORT_EMPTY_FUNCTIONS", false);
    /**
     * Describes whether pack.mcmeta should be exported.
     * <p>
     * Default value is <code>true</code>
     */
    public final ModuleOption<Boolean> EXPORT_PACK_MCMETA = new ModuleOption<>("EXPORT_PACK_MCMETA", true);

    public ModuleSettings(Version targetVersion) {
        this.targetVersion = targetVersion;
    }

    public Version getTargetVersion() {
        return targetVersion;
    }

    public void setTargetVersion(Version targetVersion) {
        this.targetVersion = targetVersion;
    }
}
