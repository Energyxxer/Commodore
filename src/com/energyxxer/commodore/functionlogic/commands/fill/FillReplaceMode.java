package com.energyxxer.commodore.functionlogic.commands.fill;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.block.BlockFormatter;
import org.jetbrains.annotations.NotNull;

public class FillReplaceMode implements FillCommand.FillMode {

    private final Block replace;

    public FillReplaceMode() {
        this(null);
    }

    public FillReplaceMode(Block replace) {
        this.replace = replace;
    }

    @NotNull
    @Override
    public String getMaskExtra() {
        return replace != null ? " replace " + BlockFormatter.asMatch(replace) : "";
    }
}
