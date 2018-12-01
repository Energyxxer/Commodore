package com.energyxxer.commodore.functionlogic.commands.fill;

public class FillKeepMode implements FillMode {
    @Override
    public String getMaskExtra() {
        return " keep";
    }
}
