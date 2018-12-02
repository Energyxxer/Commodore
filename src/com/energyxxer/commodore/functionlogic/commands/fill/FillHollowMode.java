package com.energyxxer.commodore.functionlogic.commands.fill;

public class FillHollowMode implements FillCommand.FillMode {
    @Override
    public String getMaskExtra() {
        return " hollow";
    }
}
