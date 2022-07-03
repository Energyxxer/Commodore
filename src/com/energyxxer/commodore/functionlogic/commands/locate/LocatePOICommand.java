package com.energyxxer.commodore.functionlogic.commands.locate;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class LocatePOICommand implements Command {
    @NotNull
    private final Type poi;

    public LocatePOICommand(@NotNull Type poi) {
        this.poi = poi;

        assertType(poi, "point_of_interest_type");
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.locate.merge");
        VersionFeatureManager.assertEnabled("command.locate.poi");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "locate poi " + poi.toSafeString());
    }

}
