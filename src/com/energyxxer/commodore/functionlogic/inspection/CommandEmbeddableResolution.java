package com.energyxxer.commodore.functionlogic.inspection;

import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteModifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Contains all the info required to insert a {@link CommandEmbeddable} object into a command.
 * */
public class CommandEmbeddableResolution {
    /**
     * A list of execution modifiers required to be added before the command's execution to ensure the object is
     * embedded correctly.
     * */
    @NotNull
    private final ArrayList<ExecuteModifier> newModifiers = new ArrayList<>();
    /**
     * The string to replace the embed marker within the command.
     * */
    @NotNull
    private final String embedString;

    /**
     * Creates an embed resolution with the given replacement string and execute modifiers.
     *
     * @param embedString The string to replace the embed marker within the command.
     * @param newModifiers An array containing the execution modifiers required to be added before the commands'
     *                     execution. May be empty.
     * */
    public CommandEmbeddableResolution(@NotNull String embedString, @NotNull ExecuteModifier... newModifiers) {
        this(embedString, Arrays.asList(newModifiers));
    }

    /**
     * Creates an embed resolution with the given replacement string and execute modifiers.
     *
     * @param embedString The string to replace the embed marker within the command.
     * @param newModifiers A list containing the execution modifiers required to be added before the commands'
     *                     execution. May be empty.
     * */
    public CommandEmbeddableResolution(@NotNull String embedString, @NotNull Collection<@NotNull ExecuteModifier> newModifiers) {
        this.embedString = embedString;
        this.newModifiers.addAll(newModifiers);
    }

    /**
     * Retrieves this embed resolution's required modifiers.
     *
     * @return The modifiers required for this embed to be added.
     * */
    @NotNull
    public Collection<ExecuteModifier> getNewModifiers() {
        return newModifiers;
    }

    /**
     * Retrieves this embed resolution's replacement string.
     *
     * @return The string to replace the embed marker.
     * */
    @NotNull
    public String getEmbedString() {
        return embedString;
    }
}
