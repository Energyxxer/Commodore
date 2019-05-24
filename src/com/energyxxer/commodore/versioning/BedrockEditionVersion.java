package com.energyxxer.commodore.versioning;

import java.util.Objects;

public class BedrockEditionVersion implements Version {

    private final int major, minor, patch;

    public BedrockEditionVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Bedrock Edition " + major + "." + minor + "." + patch;
    }

    @Override
    public int compare(Version rawOther) {
        if (rawOther instanceof BedrockEditionVersion) {
            BedrockEditionVersion other = (BedrockEditionVersion) rawOther;

            if (other.major != this.major) return this.major - other.major;
            if (other.minor != this.minor) return this.minor - other.minor;
            return this.patch - other.patch;

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

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, patch);
    }
}
