package com.energyxxer.commodore.module.settings;

public class ModuleSettingsManager {
    private static final ThreadLocal<ModuleSettings> activeSettings = new ThreadLocal<>();

    public static ModuleSettings getActive() {
        return activeSettings.get();
    }

    public static void set(ModuleSettings settings) {
        activeSettings.set(settings);
    }

    public static void clear() {
        activeSettings.remove();
    }
}
