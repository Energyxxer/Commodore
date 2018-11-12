package com.energyxxer.commodore.util.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSource;

public interface CompoundInput {

    InputStream get(String path) throws IOException;
    void open() throws IOException;
    void close() throws IOException;

    class Static {
        public static CompoundInput chooseInputForClasspath(String rootPath, Class cls) {
            CodeSource src = cls.getProtectionDomain().getCodeSource();
            if(src != null && src.getLocation().getFile().endsWith(".jar")) {
                return new ZipCompoundInput(new File(src.getLocation().getFile()), rootPath);
            }
            return new ResourceCompoundInput(rootPath, cls);
        }
    }
}
