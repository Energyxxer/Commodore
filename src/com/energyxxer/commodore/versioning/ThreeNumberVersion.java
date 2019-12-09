package com.energyxxer.commodore.versioning;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class ThreeNumberVersion implements Version {

    protected final int major, minor, patch;

    public ThreeNumberVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
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
    public String getVersionString() {
        return major + "." + minor + "." + patch;
    }

    @Override
    public String toString() {
        return getVersionString();
    }

    @Override
    public int compare(Version rawOther) {
        if (rawOther instanceof ThreeNumberVersion) {
            ThreeNumberVersion other = (ThreeNumberVersion) rawOther;

            if (other.major != this.major) return this.major - other.major;
            if (other.minor != this.minor) return this.minor - other.minor;
            return this.patch - other.patch;

        } else throw new UnsupportedOperationException(this + " and " + rawOther + " are not comparable versions");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreeNumberVersion that = (ThreeNumberVersion) o;
        return major == that.major &&
                minor == that.minor &&
                patch == that.patch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEditionString(), major, minor, patch);
    }
}
