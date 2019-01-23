package com.energyxxer.commodore;

import java.nio.charset.Charset;

public final class Commodore {
    private static Charset defaultEncoding = Charset.forName("UTF-8");

    public static Charset getDefaultEncoding() {
        return defaultEncoding;
    }

    public static void setDefaultEncoding(Charset defaultEncoding) {
        Commodore.defaultEncoding = defaultEncoding;
    }
}
