package com.energyxxer.commodore.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
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

    public void generate() throws IOException {
        if(outputType == ZIP) {
            zipStream = new ZipOutputStream(new FileOutputStream(rootFile));
        }

        for(Exportable exportable : module.getAllExportables()) {
            if(!exportable.shouldExport()) continue;
            String exportPath = exportable.getExportPath();
            if(exportable instanceof Namespaced) {
                exportPath = exportPath.replaceAll("\\$NAMESPACE\\$", ((Namespaced) exportable).getNamespace().getName());
            }
            queueFile(exportPath, exportable.getContents());
            exportable.dispose();
        }

        for(Map.Entry<String, byte[]> entry : queuedFiles.entrySet()) {
            createFile(entry.getKey(), entry.getValue());
        }

        if(zipStream != null) zipStream.close();
    }

    private void createFile(@Nullable String path, @Nullable byte[] contents) throws IOException {
        if(path == null || contents == null) return;
        if(outputType == ZIP) {
            ZipEntry e = new ZipEntry(path);
            zipStream.putNextEntry(e);

            zipStream.write(contents, 0, contents.length);
            zipStream.closeEntry();
        } else {
            File file = new File(rootPath + File.separator + path.replace("/", File.separator));
            file.getParentFile().mkdirs();
            file.createNewFile();

            try(FileOutputStream writer = new FileOutputStream(file)) {
                writer.write(contents);
                writer.flush();
            }
        }
    }

    private HashMap<String, byte[]> queuedFiles = new HashMap<>();

    private void queueFile(@Nullable String path, @Nullable byte[] contents) {
        if(path == null || contents == null) return;
        queuedFiles.put(path, contents);
    }
}
