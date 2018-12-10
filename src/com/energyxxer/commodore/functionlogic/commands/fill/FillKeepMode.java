package com.energyxxer.commodore.functionlogic.commands.fill;

import org.jetbrains.annotations.NotNull;

public class FillKeepMode implements FillCommand.FillMode {
    @NotNull
    @Override
    public String getMaskExtra() {
        return " keep";
    }
}
