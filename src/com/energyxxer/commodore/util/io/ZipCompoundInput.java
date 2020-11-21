package com.energyxxer.commodore.util.io;

import com.energyxxer.commodore.Commodore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipCompoundInput implements CompoundInput {
    @NotNull
    private final File file;
    @NotNull
    private final String rootPath;
    private ZipFile zf = null;

    public ZipCompoundInput(@NotNull File file) {
        this(file, "");
    }

    public ZipCompoundInput(@NotNull File file, @NotNull String rootPath) {
        this.file = file;
        if(rootPath.startsWith("/")) rootPath = rootPath.substring(1);
        this.rootPath = rootPath;
    }

    @Nullable
    @Override
    public InputStream get(@NotNull String path) throws IOException {
        if(zf == null) throw new IllegalStateException("Must first call open() on the ZipCompoundInput");
        ZipEntry entry = zf.getEntry(rootPath + path);
        if(entry == null || entry.isDirectory()) { // If it's a directory, list all subfiles' names
            StringBuilder sb = new StringBuilder();
            HashSet<String> seenPaths = new HashSet<>();
            collectSubEntries(entry, path, s -> {
                if(seenPaths.add(s)) {
                    sb.append(s);
                    sb.append('\n');
                }
            });
            if(sb.length() == 0) return null;
            if(sb.length() > 0) sb.setLength(sb.length()-1); // Remove trailing newline
            return new ByteArrayInputStream(sb.toString().getBytes(Commodore.getDefaultEncoding()));
        }
        return zf.getInputStream(entry);
    }

    @Override
    public boolean isDirectory(@NotNull String path) {
        if(zf == null) throw new IllegalStateException("Must first call open() on the ZipCompoundInput");
        ZipEntry entry = zf.getEntry(rootPath + path);
        if("".equals(path)) return true;
        return entry == null || entry.isDirectory();
    }

    @Override
    public Iterable<String> listSubEntries(@NotNull String path) {
        if(zf == null) throw new IllegalStateException("Must first call open() on the ZipCompoundInput");
        ZipEntry entry = zf.getEntry(rootPath + path);

        if(entry == null || entry.isDirectory()) { // If it's a directory, list all subfiles' names
            HashSet<String> subEntries = new HashSet<>();
            collectSubEntries(entry, path, subEntries::add);
            return subEntries;
        }
        return null;
    }

    private void collectSubEntries(ZipEntry entry, String path, Consumer<String> consumer) {
        if(entry == null || entry.isDirectory()) { // If it's a directory, list all subfiles' names
            Path pathToStart = Paths.get(rootPath + path);

            Enumeration<? extends ZipEntry> it = zf.entries();
            while(it.hasMoreElements()) {
                ZipEntry sub = it.nextElement();

                Path subPath = Paths.get(sub.getName());

                if((subPath == null && "".equals(rootPath + path)) || (subPath != null && subPath.startsWith(pathToStart) && !subPath.equals(pathToStart))) {
                    String subEntry = pathToStart.relativize(subPath).getName(0).toString();
                    consumer.accept(subEntry);
                }
            }
        }
    }

    @Override
    public void open() throws IOException {
        zf = new ZipFile(file);
    }

    @Override
    public void close() throws IOException {
        if (zf != null) zf.close();
        zf = null;
    }

    @Override
    public File getRootFile() {
        return file;
    }
}
