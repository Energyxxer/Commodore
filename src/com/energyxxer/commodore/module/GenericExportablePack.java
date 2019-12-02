package com.energyxxer.commodore.module;

import com.energyxxer.commodore.Commodore;
import com.energyxxer.commodore.module.settings.ModuleSettings;
import com.energyxxer.commodore.module.settings.ModuleSettingsManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class GenericExportablePack implements ExportablePack {
    @NotNull
    protected final ModuleSettings settings;

    protected HashMap<String, Exportable> exportables = new HashMap<>();

    public GenericExportablePack() {
        settings = new ModuleSettings(Commodore.DEFAULT_TARGET_VERSION);
    }

    public void put(Exportable exportable) {
        exportables.put(exportable.getExportPath(), exportable);
    }

    public Exportable get(String path) {
        return exportables.get(path);
    }

    @Override
    public Collection<Exportable> getAllExportables() {
        return exportables.values();
    }

    public void compile(@NotNull File file) throws IOException {
        ModuleSettingsManager.set(settings);

        new ModulePackGenerator(this, file).generate();
        ModuleSettingsManager.clear();
    }

    /**
     * Retrieves this module's settings manager.
     *
     * @return the settings for this module.
     */
    @NotNull
    public ModuleSettings getSettingsManager() {
        return settings;
    }

    /**
     * Enables this module's settings to be enforced from the point this method is call until compilation.
     * If enabled, any object instantiations or methods that may result in a version incompatibility error will throw
     * an exception on the spot, rather than only showing them during compilation.
     * <p>
     * It is advised that you call this before doing anything with this module.
     * </p>
     * Multiple command modules in the same thread may not be active at at once.
     */
    public void setSettingsActive() {
        ModuleSettingsManager.set(settings);
    }

    /**
     * Adds all the data of the specified module into this module.
     *
     * @param other The module whose data is to be copied over to this module.
     * */
    public void join(@NotNull GenericExportablePack other) {
        this.exportables.putAll(other.exportables);
    }
}
