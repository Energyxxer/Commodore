package com.energyxxer.commodore.versioning;

import java.util.Objects;

public class JavaEditionVersion implements Version {

    private final int major, minor, patch;

    public JavaEditionVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Java Edition " + major + "." + minor + "." + patch;
    }

    @Override
    public int compare(Version rawOther) {
        if (rawOther instanceof JavaEditionVersion) {
            JavaEditionVersion other = (JavaEditionVersion) rawOther;

            if (other.major != this.major) return this.major - other.major;
            if (other.minor != this.minor) return this.minor - other.minor;
            return this.patch - other.patch;

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

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, patch);
    }
}
