package com.energyxxer.commodore.module;

public class RawExportable implements Exportable {

    private boolean export = true;
    private final String exportPath;
    private String fileContent;

    public RawExportable(String exportPath) {
        this(exportPath, "");
    }

    public RawExportable(String exportPath, String fileContent) {
        this.exportPath = exportPath;
        this.fileContent = fileContent;

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
    public String getExportPath() {
        return exportPath;
    }

    @Override
    public String getContents() {
        return fileContent;
    }
}
