package com.energyxxer.commodore.util.io;

import java.io.InputStream;

public class ResourceCompoundInput implements CompoundInput {
    private String rootPath;
    private Class cls;

    public ResourceCompoundInput(String rootPath, Class cls) {
        this.rootPath = rootPath;
        this.cls = cls;
    }

    @Override
    public InputStream get(String path) {
        return cls.getResourceAsStream(rootPath + path);
    }

    @Override
    public void open() {
    }

    @Override
    public void close() {
    }
}
