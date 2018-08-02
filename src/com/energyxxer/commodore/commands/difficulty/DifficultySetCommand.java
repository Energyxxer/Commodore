package com.energyxxer.commodore.commands.difficulty;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertDifficulty;

public class DifficultySetCommand extends DifficultyCommand {

    private final Type difficulty;

    public DifficultySetCommand(Type difficulty) {
        this.difficulty = difficulty;
        assertDifficulty(difficulty);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "difficulty " + difficulty);
    }
}
