package com.energyxxer.commodore.util.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipCompoundInput implements CompoundInput {
    private File file;
    private ZipFile zf = null;

    public ZipCompoundInput(File file) {
        this.file = file;
    }

    @Override
    public InputStream get(String path) throws IOException {
        if(zf == null) throw new IllegalStateException("Must first call open() on the ZipCompoundInput");
        ZipEntry entry = zf.getEntry(path);
        if(entry == null) return null;
        if(entry.isDirectory()) { // If it's a directory, list all subfiles' names
            Enumeration<? extends ZipEntry> it = zf.entries();
            StringBuilder sb = new StringBuilder();
            while(it.hasMoreElements()) {
                ZipEntry sub = it.nextElement();

                Path parent = Paths.get(sub.getName()).getParent();

                if(parent != null && parent.equals(Paths.get(path))) {
                    sb.append(sub.getName().substring(entry.getName().length())); // Add the name, excluding parent's path
                    if(sub.isDirectory()) sb.setLength(sb.length()-1); // If it's a directory, remove the trailing slash
                    sb.append('\n'); // Add a new line for more files
                }
            }
            if(sb.length() > 0) sb.setLength(sb.length()-1); // Remove trailing newline
            return new ByteArrayInputStream(sb.toString().getBytes());
        }
        return zf.getInputStream(entry);
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
}
