package com.energyxxer.commodore.module;

import com.energyxxer.commodore.functionlogic.functions.AccessLogFile;
import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.loottables.LootTable;
import com.energyxxer.commodore.tags.TagGroup;
import com.energyxxer.commodore.tags.TagManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.energyxxer.commodore.module.ModulePackGenerator.OutputType.ZIP;

public class ModulePackGenerator {

    public enum OutputType {
        FOLDER, ZIP
    }

    private final CommandModule module;

    private String rootPath;
    private File rootFile;

    private Gson gson;

    private final OutputType outputType;

    private ZipOutputStream zipStream;

    public ModulePackGenerator(CommandModule module, File directory, OutputType outputType) {
        this.module = module;
        this.outputType = outputType;

        if(!directory.exists()) directory.mkdirs();
        if(!directory.isDirectory())
            throw new IllegalArgumentException("Expected directory, instead found file at '" + directory + "'");

        this.gson = new GsonBuilder().setPrettyPrinting().create();

        this.rootPath = directory.getAbsolutePath() + File.separator + module.name + (outputType == ZIP ? ".zip" : "");
        this.rootFile = new File(this.rootPath);
        if(rootFile.isDirectory() && !rootFile.exists()) rootFile.mkdirs();
    }

    public void generate() throws IOException {
        if(outputType == ZIP) {
            zipStream = new ZipOutputStream(new FileOutputStream(rootFile));
        }

        createPackMcmeta();

        for(Namespace namespace : module.namespaces.values()) {
            String namespacePath = "data/" + namespace.name;

            ArrayList<Exportable> exportables = new ArrayList<>();

            for(Function func : namespace.getFunctionManager().getAll()) {
                if(func.getEntryCount() == 0) continue;
                exportables.add(func);
                if(module.optMgr.EXPORT_ACCESS_LOGS.getValue()) exportables.add(new AccessLogFile(func));
            }

            TagManager tagMgr = namespace.getTagManager();

            for(TagGroup<?> group : tagMgr.getGroups()) {
                exportables.addAll(group.getAll());
            }

            exportables.addAll(namespace.lootTables.getAll());

            for(Exportable exportable : exportables) {
                if(exportable.shouldExport()) {
                    createFile(namespacePath + "/" + exportable.getExportPath(), exportable.getContents());
                }
            }
        }

        if(zipStream != null) zipStream.close();
    }

    private void createPackMcmeta() throws IOException {
        JsonObject root = new JsonObject();
        JsonObject inner = new JsonObject();
        root.add("pack", inner);
        inner.addProperty("pack_format", 1);

        if(module.description != null) inner.addProperty("description", module.description);

        createFile("pack.mcmeta", gson.toJson(root));
    }

    private void createFile(String path, String contents) throws IOException {
        if(outputType == ZIP) {
            ZipEntry e = new ZipEntry(path);
            zipStream.putNextEntry(e);

            byte[] data = contents.getBytes();
            zipStream.write(data, 0, data.length);
            zipStream.closeEntry();
        } else {
            File file = new File(rootPath + File.separator + path.replace("/", File.separator));
            file.getParentFile().mkdirs();
            file.createNewFile();

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(contents);
                writer.flush();
                writer.close();
            }
        }
    }
}
