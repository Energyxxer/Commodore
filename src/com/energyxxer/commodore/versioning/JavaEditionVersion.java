package com.energyxxer.commodore.versioning;

import org.jetbrains.annotations.NotNull;

public class JavaEditionVersion extends ThreeNumberVersion {

    public JavaEditionVersion(int major, int minor, int patch) {
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
        return "Java";
    }

    @Override
    public String toString() {
        return "Java Edition " + getVersionString();
    }

    @Override
    public int compare(Version rawOther) {
        if (rawOther instanceof JavaEditionVersion) {
            return super.compare(rawOther);
        } else throw new UnsupportedOperationException(this + " and " + rawOther + " are not comparable versions");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaEditionVersion that = (JavaEditionVersion) o;
        return major == that.major &&
                minor == that.minor &&
                patch == that.patch;
    }
}
