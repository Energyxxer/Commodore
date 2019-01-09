package com.energyxxer.commodore.util.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;

public class ResourceCompoundInput implements CompoundInput {
    @NotNull
    private final String rootPath;
    @NotNull
    private final Class cls;

    public ResourceCompoundInput(@NotNull String rootPath, @NotNull Class cls) {
        this.rootPath = rootPath;
        this.cls = cls;
    }

    @Nullable
    @Override
    public InputStream get(@NotNull String path) {
        return cls.getResourceAsStream(rootPath + path);
    }

    @Override
    public void open() {
    }

    @Override
    public void close() {
    }
}
