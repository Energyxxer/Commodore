package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;

public class ExecuteConditionRegion extends ExecuteCondition {

    public enum AirPolicy {
        ALL, MASKED
    }

    //If region matches the template

    private final CoordinateSet region0;
    private final CoordinateSet region1;
    private final CoordinateSet template;
    private final AirPolicy policy;

    public ExecuteConditionRegion(ConditionType flowController, CoordinateSet region0, CoordinateSet region1, CoordinateSet template) {
        this(flowController, region0, region1, template, AirPolicy.ALL);
    }

    public ExecuteConditionRegion(ConditionType flowController, CoordinateSet region0, CoordinateSet region1, CoordinateSet template, AirPolicy policy) {
        super(flowController);
        this.region0 = region0;
        this.region1 = region1;
        this.template = template;
        this.policy = policy;
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "blocks " + region0.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + region1.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + template.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + policy.toString().toLowerCase());
    }

    @Override
    public boolean isIdempotent() {
        return true;
    }

    @Override
    public boolean isSignificant() {
        return true;
    }

    @Override
    public boolean isAbsolute() {
        return false;
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        ExecutionVariableMap map = new ExecutionVariableMap();
        map.merge(region0.getUsedExecutionVariables());
        map.merge(region1.getUsedExecutionVariables());
        map.merge(template.getUsedExecutionVariables());
        return map;
    }
}
