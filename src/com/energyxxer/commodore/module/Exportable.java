package com.energyxxer.commodore.module;

/**
 * Describes an element of a command module that can be exported into a data pack.
 *
 * @see CommandModule
 * */
public interface Exportable {
    /**
     * Checks whether this exportable object is set to export upon compilation.
     *
     * @return <code>true</code> if this object should be exported, <code>false</code> otherwise.
     * */
    boolean shouldExport();
    /**
     * Tells this exportable object whether it should export upon compilation.
     *
     * @param export Whether this object should export upon compilation.
     * */
    void setExport(boolean export);

    /**
     * Retrieves the path from the data pack's root into which this object will place its file upon exporting.
     *
     * @return The path to this object's export location.
     * */
    String getExportPath();
    /**
     * Retrieves the contents that should be written into the exported file.
     *
     * @return The text to write into the export location.
     * */
    String getContents();
}
