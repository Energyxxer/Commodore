package com.energyxxer.commodore.util.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSource;

public interface CompoundInput {

    @Nullable
    InputStream get(@NotNull String path) throws IOException;
    void open() throws IOException;
    void close() throws IOException;
    File getRootFile();

    class Static {
        @NotNull
        public static CompoundInput chooseInputForClasspath(@NotNull String rootPath, @NotNull Class cls) {
            CodeSource src = cls.getProtectionDomain().getCodeSource();
            if(src != null && src.getLocation().getFile().endsWith(".jar")) {
                return new ZipCompoundInput(new File(src.getLocation().getFile()), rootPath);
            }
            return new ResourceCompoundInput(rootPath, cls);
        }
    }
}
