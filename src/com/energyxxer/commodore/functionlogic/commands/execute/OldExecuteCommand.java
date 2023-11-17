package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OldExecuteCommand extends ExecuteCommand {
    protected Entity as;
    protected CoordinateSet positioned;
    protected ExecuteConditionBlock detect;
    protected Command chainedCommand;

    public OldExecuteCommand(Entity as, CoordinateSet positioned, Command chainedCommand) {
        this(as, positioned, null, chainedCommand);
    }

    public OldExecuteCommand(Entity as, CoordinateSet positioned, ExecuteConditionBlock detect, Command chainedCommand) {
        super(chainedCommand, setupSuperCall(as, positioned, detect));
        this.as = as;
        this.positioned = positioned;
        this.detect = detect;
        this.chainedCommand = chainedCommand;

        if(detect != null && !"if ".equals(detect.getStarter())) {
            throw new IllegalArgumentException("OldExecuteCommand does not accept 'unless' ExecuteConditionBlock objects");
        }
    }

    private static ArrayList<ExecuteModifier> setupSuperCall(Entity as, CoordinateSet positioned, ExecuteConditionBlock detect) {
        ArrayList<ExecuteModifier> modifiers = new ArrayList<>();
        modifiers.add(new ExecuteAsEntity(as));
        modifiers.add(new ExecuteAtEntity(new Selector(Selector.BaseSelector.SENDER)));
        if(positioned.isSignificant())
            modifiers.add(new ExecutePositioned(positioned));
        if(detect != null) modifiers.add(detect);
        return modifiers;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        if(VersionFeatureManager.getBoolean("execute_modifiers", false)) {
            return super.resolveCommand(execContext);
        } else {
            return new CommandResolution(execContext, "execute " + as.toString() + " " + positioned.getAs(Coordinate.DisplayMode.ENTITY_POS) + (detect != null ? " " + detect.getSubCommand(execContext).getRaw() : "") + " " + chainedCommand.resolveCommand(execContext).construct());
        }
    }

    @Override
    public void assertAvailable() {
        as.assertAvailable();
        chainedCommand.assertAvailable();
    }
}
