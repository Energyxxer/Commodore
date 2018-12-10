package com.energyxxer.commodore.functionlogic.commands.fill;

import org.jetbrains.annotations.NotNull;

public class FillOutlineMode implements FillCommand.FillMode {
    @NotNull
    @Override
    public String getMaskExtra() {
        return " outline";
    }
}
