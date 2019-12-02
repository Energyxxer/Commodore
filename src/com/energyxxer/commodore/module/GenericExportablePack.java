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

    private HashMap<String, Exportable> exportables = new HashMap<>();

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
}
