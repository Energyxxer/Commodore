package com.energyxxer.commodore.util.io;

import com.energyxxer.commodore.Commodore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

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
    public void open() {
    }

    @Override
    public void close() {
    }
}
