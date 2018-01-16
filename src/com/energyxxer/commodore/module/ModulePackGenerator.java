package com.energyxxer.commodore.module;

import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.tags.Tag;
import com.energyxxer.commodore.tags.TagGroup;
import com.energyxxer.commodore.tags.TagManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ModulePackGenerator {

    private final CommandModule module;
    private File directory;

    private String rootPath;
    private File rootDir;

    private Gson gson;

    public ModulePackGenerator(CommandModule module, File directory) throws IOException {
        this.module = module;
        if(!directory.isDirectory() || !directory.exists()) throw new IllegalArgumentException("Expected directory, instead found file at '" + directory + "'");

        this.gson = new Gson();

        rootPath = directory.getAbsolutePath() + File.separator + module.name;
        rootDir = new File(rootPath);
        rootDir.mkdir();

        createPackMcmeta();

        for(Namespace namespace : module.namespaces.values()) {
            String namespacePath = rootPath + File.separator + "data" + File.separator + namespace.name;
            File namespaceDir = new File(namespacePath);

            for(Function func : namespace.getFunctionManager().getAll()) {
                String functionPath = func.getFilePath();
                String directoryPath = namespacePath + File.separator + "functions" + File.separator + (functionPath.contains(".") ? functionPath.substring(0, functionPath.lastIndexOf(".")) : "");
                new File(directoryPath).mkdirs();
                String fileName = namespacePath + File.separator + "functions" + File.separator + functionPath + ".mcfunction";

                File file = new File(fileName);
                file.createNewFile();

                try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(func.getResolvedContent());
                    writer.flush();
                    writer.close();
                }
            }

            TagManager tagMgr = namespace.getTagManager();

            TagGroup<?>[] groups = {tagMgr.getBlockGroup(), tagMgr.getItemGroup(), tagMgr.getFunctionGroup()};

            for(int i = 0; i < groups.length; i++) {
                for(Tag tag : groups[i].getAll()) {
                    String directoryPath = namespacePath + File.separator + "tags" + File.separator + groups[i].getGroupName();
                    new File(directoryPath).mkdirs();
                    String fileName = directoryPath + File.separator + tag.getName() + ".json";

                    File file = new File(fileName);
                    file.createNewFile();

                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write(tag.getJSONContent());
                        writer.flush();
                        writer.close();
                    }
                }
            }
        }
    }

    private void createPackMcmeta() throws IOException {
        JsonObject root = new JsonObject();
        JsonObject inner = new JsonObject();
        root.add("pack", inner);
        inner.addProperty("pack_format", 1);
        if(module.description != null) inner.addProperty("description", module.description);

        File packMcmeta = new File(this.rootPath + File.separator + "pack.mcmeta");
        packMcmeta.createNewFile();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(packMcmeta))) {
            writer.write(gson.toJson(root));
            writer.flush();
            writer.close();
        }
    }
}
