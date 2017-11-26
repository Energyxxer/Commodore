package com.energyxxer.mctech.coordinates;

import com.energyxxer.mctech.entity.Entity;
import com.energyxxer.mctech.commands.execute.ExecuteModifier;
import com.energyxxer.mctech.commands.execute.SubCommandResult;

public class ExecuteAlignment implements ExecuteModifier {
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;

    public ExecuteAlignment(boolean x, boolean y, boolean z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult("align " + ((x) ? "x" : "") + ((y) ? "y" : "") + ((z) ? "z" : ""));
    }

    @Override
    public boolean isIdempotent() {
        return true;
    }

    @Override
    public boolean isSignificant() {
        return x || y || z;
    }

    @Override
    public boolean isAbsolute() {
        return false;
    }
}
