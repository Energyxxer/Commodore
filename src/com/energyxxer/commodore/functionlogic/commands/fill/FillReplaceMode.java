package com.energyxxer.commodore.functionlogic.commands.fill;

import com.energyxxer.commodore.block.Block;

public class FillReplaceMode implements FillCommand.FillMode {

    private final Block replace;

    public FillReplaceMode() {
        this(null);
    }

    public FillReplaceMode(Block replace) {
        this.replace = replace;
    }

    @Override
    public String getMaskExtra() {
        return replace != null ? " replace " + replace : "";
    }
}
