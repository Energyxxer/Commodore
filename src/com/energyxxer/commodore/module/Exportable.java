package com.energyxxer.commodore.module;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    @Nullable
    byte[] getContents();

    void dispose();

    public static ArrayList<IOException> exportAll(List<Exportable> exportables, String rootPath, @Nullable ZipOutputStream zipStream, boolean disposeAll, int numThreads) {
        ExecutorService threadPool = numThreads > 0 ? Executors.newFixedThreadPool(numThreads) : null;

        ArrayList<IOException> exceptions = new ArrayList<>();

        HashSet<String> seenPaths = new HashSet<>();

        for(int i = exportables.size()-1; i >= 0; i--) {
            Exportable exportable = exportables.get(i);
            if(exportable.shouldExport()) {
                String exportPath = exportable.getExportPath();
                if(exportable instanceof Namespaced) {
                    exportPath = exportPath.replaceAll("\\$NAMESPACE\\$", ((Namespaced) exportable).getNamespace().getName());
                }
                if(seenPaths.add(exportPath)) {
                    final String path = exportPath;
                    byte[] contents = exportable.getContents();
                    if(path != null && contents != null) {
                        if(threadPool != null) {
                            threadPool.execute(() -> {
                                try {
                                    createFile(path, contents, rootPath, zipStream);
                                } catch (IOException e) {
                                    exceptions.add(e);
                                }
                            });
                        } else {
                            try {
                                createFile(exportPath, contents, rootPath, zipStream);
                            } catch (IOException e) {
                                exceptions.add(e);
                            }
                        }
                    }
                }
            }

            if(disposeAll) {
                exportable.dispose();
            }
        }

        if(threadPool != null) {
            threadPool.shutdown();
            try {
                threadPool.awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                threadPool.shutdownNow();
                Thread.currentThread().interrupt();
            } catch (ThreadDeath e) {
                threadPool.shutdownNow();
                throw e;
            }
        }
        return exceptions;
    }

    static void createFile(@Nullable String path, @Nullable byte[] contents, String rootPath, ZipOutputStream zipStream) throws IOException {
        if(path == null || contents == null) return;
        if(zipStream != null) {
            ZipEntry e = new ZipEntry(path);
            zipStream.putNextEntry(e);

            zipStream.write(contents, 0, contents.length);
            zipStream.closeEntry();
        } else {
            File file = new File(rootPath + File.separator + path.replace('/', File.separatorChar));
            Path dirPath = file.getParentFile().toPath();
            Files.createDirectories(dirPath);
            if(!file.exists()) {
                if(!file.createNewFile()) {
                    throw new IOException("Failed to create file: " + file);
                }
            }

            try(FileOutputStream writer = new FileOutputStream(file)) {
                writer.write(contents);
                writer.flush();
            }
        }
    }

    public static void createContentsJson(ArrayList<Exportable> exportables) {
        String contentListPath = "contents.json";
        HashSet<String> allPaths = new HashSet<>();

        for(Exportable exportable : exportables) {
            String path = exportable.getExportPath();
            allPaths.add(path);
        }
        allPaths.add("contents.json");

        JsonArray contentList = new JsonArray();
        Iterator<String> it = allPaths.stream().sorted().iterator();
        while(it.hasNext()) {
            String path = it.next();

            JsonObject entryObj = new JsonObject();
            entryObj.addProperty("path", path);
            contentList.add(entryObj);
        }

        JsonObject contentsRoot = new JsonObject();
        contentsRoot.add("content", contentList);
        contentsRoot.addProperty("version", 1);
        exportables.add(new RawExportable("contents.json", new Gson().toJson(contentsRoot)));
    }
}
