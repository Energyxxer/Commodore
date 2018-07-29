package com.energyxxer.commodore.util.io;

import java.io.*;

public class DirectoryCompoundInput implements CompoundInput {
    private File directory;

    public DirectoryCompoundInput(File directory) {
        this.directory = directory;
    }

    @Override
    public InputStream get(String path) {
        File target = new File(directory, path.replace('/',File.separatorChar));

        try {
            if(target.isDirectory()) {
                String[] targetList = target.list();
                return targetList != null ? new ByteArrayInputStream(String.join("\n", targetList).getBytes()) : null;
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
