package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;

public class ExecuteConditionEntity extends ExecuteCondition {
    public Entity entity;

    public ExecuteConditionEntity(ConditionType flowController, Entity entity) {
        super(flowController);
        this.entity = entity;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult(this.getStarter() + "entity " + entity.getSelectorAs(sender));
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
