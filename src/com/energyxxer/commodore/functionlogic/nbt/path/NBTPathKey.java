package com.energyxxer.commodore.functionlogic.nbt.path;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.regex.Pattern;

public class NBTPathKey implements NBTPathNode {
    @NotNull
    private final String name;
    @Nullable
    private final TagCompound compoundMatch;

    public static final Pattern NO_QUOTE_PATH_KEY_REGEX = Pattern.compile("[^\\s\\[\\].{}\"]+");

    public NBTPathKey(@NotNull String name) {
        this(name, null);
    }

    public NBTPathKey(@NotNull String name, @Nullable TagCompound compoundMatch) {
        this.name = name;
        this.compoundMatch = compoundMatch;
    }

    @NotNull
    @Override
    public String getPathString() {
        return ((NO_QUOTE_PATH_KEY_REGEX.matcher(name).matches()) ? name : "\"" + CommandUtils.escape(name) + "\"") + (compoundMatch != null ? compoundMatch.toHeadlessString() : "");
    }

    @NotNull
    @Override
    public String getPathSeparator() {
        return ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NBTPathKey that = (NBTPathKey) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
