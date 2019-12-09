package com.energyxxer.commodore.versioning;

import org.jetbrains.annotations.NotNull;

public class BedrockEditionVersion extends ThreeNumberVersion {

    public BedrockEditionVersion(int major, int minor, int patch) {
        super(major, minor, patch);
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    @NotNull
    @Override
    public String getEditionString() {
        return "Bedrock";
    }

    @Override
    public String toString() {
        return "Bedrock Edition " + getVersionString();
    }

    @Override
    public int compare(Version rawOther) {
        if (rawOther instanceof BedrockEditionVersion) {
            return super.compare(rawOther);
        } else throw new UnsupportedOperationException(this + " and " + rawOther + " are not comparable versions");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BedrockEditionVersion that = (BedrockEditionVersion) o;
        return major == that.major &&
                minor == that.minor &&
                patch == that.patch;
    }
}
