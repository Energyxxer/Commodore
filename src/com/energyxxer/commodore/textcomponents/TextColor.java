package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.module.settings.ModuleSettingsManager;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Objects;

public class TextColor {
    private static final TextColor[] defaultColors = new TextColor[17];
    private static int defaultColorIndex = 0;

    public static final TextColor BLACK = new TextColor(0, "black", "000000");
    public static final TextColor DARK_BLUE = new TextColor(1, "dark_blue", "0000AA");
    public static final TextColor DARK_AQUA = new TextColor(2, "dark_aqua", "00AA00");
    public static final TextColor DARK_GREEN = new TextColor(3, "dark_green", "00AAAA");
    public static final TextColor DARK_RED = new TextColor(4, "dark_red", "AA0000");
    public static final TextColor DARK_PURPLE = new TextColor(5, "dark_purple", "AA00AA");
    public static final TextColor GOLD = new TextColor(6, "gold", "FFAA00");
    public static final TextColor GRAY = new TextColor(7, "gray", "AAAAAA");
    public static final TextColor DARK_GRAY = new TextColor(8, "dark_gray", "555555");
    public static final TextColor BLUE = new TextColor(9, "blue", "5555FF");
    public static final TextColor GREEN = new TextColor(10, "green", "55FF55");
    public static final TextColor AQUA = new TextColor(11, "aqua", "55FFFF");
    public static final TextColor RED = new TextColor(12, "red", "FF5555");
    public static final TextColor LIGHT_PURPLE = new TextColor(13, "light_purple", "FF55FF");
    public static final TextColor YELLOW = new TextColor(14, "yellow", "FFFF55");
    public static final TextColor WHITE = new TextColor(15, "white", "FFFFFF");
    public static final TextColor RESET = new TextColor(-2, "reset");

    private final int code;

    private final String name;

    private final int red;
    private final int green;
    private final int blue;

    private TextColor(int code, String name) {
        this.code = code;
        this.name = name;

        this.red = this.green = this.blue = 0;
        defaultColors[defaultColorIndex] = this;
        defaultColorIndex++;
    }

    private TextColor(int code, String name, String hexString) {
        this.code = code;
        this.name = name;

        int parsed = Integer.parseInt(hexString, 16);
        red = ((parsed >> 16) & 0xFF);
        green = ((parsed >> 8) & 0xFF);
        blue = ((parsed) & 0xFF);

        defaultColors[defaultColorIndex] = this;
        defaultColorIndex++;
    }

    public TextColor(int red, int green, int blue) {
        this.code = -1;
        this.name = null;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public static TextColor valueOf(@NotNull String nm) {
        if(nm.startsWith("#")) {
            int parsed = Integer.parseUnsignedInt(nm.substring(1), 16);
            int red = ((parsed >> 16) & 0xFF);
            int green = ((parsed >> 8) & 0xFF);
            int blue = ((parsed) & 0xFF);
            return new TextColor(red, green, blue);
        } else {
            nm = nm.toLowerCase(Locale.ENGLISH);
            for(TextColor color : defaultColors) {
                if(nm.equals(color.name)) return color;
            }
        }
        return null;
    }

    public String name() {
        return toString();
    }

    @Override
    public String toString() {
        return name != null ? name : "#" + Integer.toUnsignedString((red << 16) | (green << 8) | blue, 16);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextColor textColor = (TextColor) o;
        return (Math.min(code, -1) == Math.min(textColor.code, -1)) &&
                (red == textColor.red &&
                green == textColor.green &&
                blue == textColor.blue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.min(code, -1), red, green, blue);
    }

    public void assertAvailable() {
        if(code == -1) {
            VersionFeatureManager.assertEnabled("textcomponent.hex_color");
        }
        if(code == -2 && VersionFeatureManager.getBoolean("textcomponent.hex_color")) {
            throw new CommodoreException(CommodoreException.Source.VERSION_ERROR, "The color code \"reset\" is not supported in " + ModuleSettingsManager.getActive().getTargetVersion());
        }
    }
}
