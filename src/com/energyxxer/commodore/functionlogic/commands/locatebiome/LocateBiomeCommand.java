package com.energyxxer.commodore.functionlogic.commands.locatebiome;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertBiome;

public class LocateBiomeCommand implements Command {
    @NotNull
    private final Type biome;

    public LocateBiomeCommand(@NotNull Type biome) {
        this.biome = biome;

        assertBiome(biome);
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.locatebiome");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "locatebiome " + biome);
    }
}
