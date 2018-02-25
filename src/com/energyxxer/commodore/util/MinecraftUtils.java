package com.energyxxer.commodore.util;

import java.io.File;

public class MinecraftUtils {

    private MinecraftUtils() {}

    public static String getMinecraftDir() {
        String workingDirectory;
        // here, we assign the name of the OS, according to Java, to a
        // variable...
        String OS = (System.getProperty("os.name")).toUpperCase();
        // to determine what the workingDirectory is.
        // if it is some version of Windows
        if (OS.contains("WIN")) {
            // it is simply the location of the "AppData" folder
            workingDirectory = System.getenv("AppData");
        }
        // Otherwise, we assume Linux or Mac
        else {
            // in either case, we would start in the user's home directory
            workingDirectory = System.getProperty("user.home");
            // if we are on a Mac, we are not done, we look for "Application
            // Support"
            if(OS.contains("MAC")) workingDirectory += "/Library/Application Support";
        }

        workingDirectory += File.separator + (!OS.contains("MAC") ? "." : "") + "minecraft";

        return workingDirectory;
    }
}
