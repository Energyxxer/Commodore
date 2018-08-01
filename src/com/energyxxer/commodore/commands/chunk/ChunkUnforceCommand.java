package com.energyxxer.commodore.commands.chunk;

import com.energyxxer.commodore.types.Type;

public class ChunkUnforceCommand extends ChunkCommand {
    public ChunkUnforceCommand(int cx, int cz) {
        this(cx, cz, null);
    }

    public ChunkUnforceCommand(int cx, int cz, Type dimension) {
        super("unforce", cx, cz, dimension);
    }
}
