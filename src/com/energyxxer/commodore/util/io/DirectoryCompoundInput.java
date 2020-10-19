package com.energyxxer.commodore.util.io;

import com.energyxxer.commodore.Commodore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.Arrays;

public class DirectoryCompoundInput implements CompoundInput {
    @NotNull
    private final File directory;

    public DirectoryCompoundInput(@NotNull File directory) {
        this.directory = directory;
    }

    @Nullable
    @Override
    public InputStream get(@NotNull String path) throws IOException {
        if(!directory.exists()) throw new IOException("Root '" + directory + "' not found");
        File target = new File(directory, path.replace('/',File.separatorChar));

        try {
            if(target.isDirectory()) {
                String[] targetList = target.list();
                return targetList != null ? new ByteArrayInputStream(String.join("\n", targetList).getBytes(Commodore.getDefaultEncoding())) : null;
            } else {
                return new FileInputStream(target);
            }
        } catch(IOException x) {
            return null;
        }
    }

    @Override
    public long getEntryLength(@NotNull String path) {
        if(!directory.exists()) return 0L;
        return new File(directory, path.replace('/',File.separatorChar)).length();
    }

    @Override
    public boolean isDirectory(@NotNull String path) {
        if(!directory.exists()) return false;
        return new File(directory, path.replace('/',File.separatorChar)).isDirectory();
    }

    @Override
    public Iterable<String> listSubEntries(@NotNull String path) {
        if(!directory.exists()) return null;
        File dir = new File(directory, path.replace('/',File.separatorChar));
        if(dir.isDirectory()) {
            String[] subFiles = dir.list();
            if(subFiles != null) return Arrays.asList(subFiles);
        }
        return null;
    }

    @Override
    public void open() {
    }

    @Override
    public void close() {
    }

    @Override
    public File getRootFile() {
        return directory;
    }
}
