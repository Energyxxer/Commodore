package com.energyxxer.commodore.functionlogic.commands.fill;

public class FillDestroyMode implements FillCommand.FillMode {
    @Override
    public String getMaskExtra() {
        return " destroy";
    }
}
