package com.energyxxer.commodore;

import com.energyxxer.commodore.versioning.JavaEditionVersion;
import com.energyxxer.commodore.versioning.Version;

import java.nio.charset.Charset;

public final class Commodore {
    private static Charset defaultEncoding = Charset.forName("UTF-8");
    public static Version DEFAULT_TARGET_VERSION = new JavaEditionVersion(1, 14, 0);

    public static Charset getDefaultEncoding() {
        return defaultEncoding;
    }

    public static void setDefaultEncoding(Charset defaultEncoding) {
        Commodore.defaultEncoding = defaultEncoding;
    }
}
