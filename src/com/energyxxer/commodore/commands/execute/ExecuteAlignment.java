package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;

import java.util.ArrayList;

import static com.energyxxer.commodore.commands.execute.SubCommandResult.ExecutionChange.*;

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
        ArrayList<SubCommandResult.ExecutionChange> changes = new ArrayList<>();
        if(x) changes.add(X);
        if(y) changes.add(Y);
        if(z) changes.add(Z);
        return new SubCommandResult("align " + ((x) ? "x" : "") + ((y) ? "y" : "") + ((z) ? "z" : ""), null, changes);
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
