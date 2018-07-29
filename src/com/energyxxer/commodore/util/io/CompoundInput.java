package com.energyxxer.commodore.util.io;

import java.io.IOException;
import java.io.InputStream;

public interface CompoundInput {

    InputStream get(String path) throws IOException;
    void open() throws IOException;
    void close() throws IOException;
}
