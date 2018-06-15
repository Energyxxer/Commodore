package com.energyxxer.commodore.module;

public interface Exportable {
    boolean shouldExport();
    void setExport(boolean export);

    String getExportPath();
    String getContents();
}
