package com.energyxxer.commodore.module;

import com.energyxxer.commodore.Commodore;
import org.jetbrains.annotations.NotNull;

public class RawExportable implements Exportable {

    private boolean export = true;
    @NotNull
    private final String exportPath;
    @NotNull
    private final byte[] data;

    public RawExportable(@NotNull String exportPath) {
        this(exportPath, "");
    }

    public RawExportable(@NotNull String exportPath, @NotNull String fileContent) {
        this(exportPath, fileContent.getBytes(Commodore.getDefaultEncoding()));
    }

    public RawExportable(@NotNull String exportPath, @NotNull byte[] data) {
        this.exportPath = exportPath;
        this.data = data;

        if(exportPath.contains("..")) throw new IllegalArgumentException("Parent directory path elements (../) are not allowed in an exportable");
    }

    @Override
    public boolean shouldExport() {
        return export;
    }

    @Override
    public void setExport(boolean export) {
        this.export = export;
    }

    @Override
    @NotNull
    public String getExportPath() {
        return exportPath;
    }

    @Override
    @NotNull
    public byte[] getContents() {
        return data;
    }
}
