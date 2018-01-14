package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;

public class ExecuteConditionRegion extends ExecuteCondition {

    public enum AirPolicy {
        ALL, MASKED;
    }

    //If region matches the template

    private CoordinateSet region0;
    private CoordinateSet region1;
    private CoordinateSet template;
    private AirPolicy policy;

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
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult(this.getStarter() + "blocks " + region0.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + region1.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + template.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + policy.toString().toLowerCase());
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
}
