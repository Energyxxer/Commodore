package com.energyxxer.commodore.module;

import com.energyxxer.commodore.Commodore;
import com.energyxxer.commodore.module.settings.ModuleSettings;
import com.energyxxer.commodore.module.settings.ModuleSettingsManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenericExportablePack implements ExportablePack {
    @NotNull
    protected final ModuleSettings settings;

    protected List<Exportable> exportables = new ArrayList<>();

    public GenericExportablePack() {
        settings = new ModuleSettings(Commodore.DEFAULT_TARGET_VERSION);
    }

    public void put(Exportable exportable) {
        exportables.add(exportable);
    }

    @Override
    public List<Exportable> getAllExportables() {
        return exportables;
    }

    public ArrayList<IOException> compile(@NotNull File file) {
        return compile(file, 0);
    }

    public ArrayList<IOException> compile(@NotNull File file, int numThreads) {
        ModuleSettingsManager.set(settings);

        ArrayList<IOException> exceptions = new ModulePackGenerator(this, file).generate(numThreads);
        ModuleSettingsManager.clear();
        return exceptions;
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
     * Multiple command modules in the same thread may not be active at once.
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
        this.exportables.addAll(other.exportables);
    }
}
