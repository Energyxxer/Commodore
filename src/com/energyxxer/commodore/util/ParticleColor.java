package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommandUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class ParticleColor {
    private final double red;
    private final double green;
    private final double blue;

    //If all three values are 1 or under, it's the exact color.

    //If any of the values is above 1, the resulting color will be randomly chosen;
    // the rgb values will be the weight applied to their corresponding colors to select the hue;
    // saturation and lightness are static and hard-coded

    public ParticleColor(@NotNull Color awtColor) {
        this((double) awtColor.getRed()/255, (double) awtColor.getGreen()/255, (double) awtColor.getBlue()/255);
    }

    public ParticleColor(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        assertFinite(red, "red");
        assertFinite(green, "green");
        assertFinite(blue, "blue");
    }

    @Override
    public String toString() {
        return CommandUtils.numberToPlainString(red) + " " + CommandUtils.numberToPlainString(green) + " " + CommandUtils.numberToPlainString(blue);
    }
}
