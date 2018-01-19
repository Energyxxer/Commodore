package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommandUtils;

import java.awt.*;

public class ParticleColor {
    private double red;
    private double green;
    private double blue;

    //If all three values are 1 or under, it's the exact color.

    //If any of the values is above 1, the resulting color will be randomly chosen;
    // the rgb values will be the weight applied to their corresponding colors to select the hue;
    // saturation and lightness are static and hard-coded

    public ParticleColor(Color awtColor) {
        this((double) awtColor.getRed()/255, (double) awtColor.getGreen()/255, (double) awtColor.getBlue()/255);
    }

    public ParticleColor(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public String toString() {
        return CommandUtils.toString(red) + " " + CommandUtils.toString(green) + " " + CommandUtils.toString(blue);
    }
}
