package com.energyxxer.commodore.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipOutputStream;

import static com.energyxxer.commodore.module.ModulePackGenerator.OutputType.FOLDER;
import static com.energyxxer.commodore.module.ModulePackGenerator.OutputType.ZIP;

public class ModulePackGenerator {

    public enum OutputType {
        FOLDER, ZIP
    }

    @NotNull
    private final ExportablePack module;

    @NotNull
    private final String rootPath;
    @NotNull
    private final File rootFile;

    private final Gson gson;

    @NotNull
    private final OutputType outputType;

    private ZipOutputStream zipStream;

    public ModulePackGenerator(@NotNull ExportablePack module, @NotNull File outFile) {
        this(module, outFile, outFile.getName().endsWith(".zip") ? ZIP : FOLDER);
    }

    public ModulePackGenerator(@NotNull ExportablePack module, @NotNull File outFile, @NotNull OutputType outputType) {
        this.module = module;
        this.outputType = outputType;

        if(outputType == FOLDER && !outFile.exists()) {
            outFile.mkdirs();
        } else if(outputType == ZIP && !outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        this.rootPath = outFile.getAbsolutePath();
        this.rootFile = outFile;
    }

    public ArrayList<IOException> generate() {
        return generate(0);
    }

    public ArrayList<IOException> generate(int numThreads) {
        if(outputType == ZIP) {
            try {
                zipStream = new ZipOutputStream(new FileOutputStream(rootFile));
            } catch (FileNotFoundException e) {
                ArrayList<IOException> exceptions = new ArrayList<>();
                exceptions.add(e);
                return exceptions;
            }
        }

        ArrayList<IOException> exceptions = null;
        try {
            exceptions = Exportable.exportAll(module.getAllExportables(), rootPath, zipStream, true, numThreads);
        } finally {
            if(zipStream != null) {
                try {
                    zipStream.close();
                } catch (IOException e) {
                    if(exceptions == null) exceptions = new ArrayList<>();
                    exceptions.add(e);
                }
            }
        }
        return exceptions;
    }
}
