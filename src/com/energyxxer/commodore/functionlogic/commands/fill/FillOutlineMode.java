package com.energyxxer.commodore.functionlogic.commands.fill;

public class FillOutlineMode implements FillCommand.FillMode {
    @Override
    public String getMaskExtra() {
        return " outline";
    }
}
