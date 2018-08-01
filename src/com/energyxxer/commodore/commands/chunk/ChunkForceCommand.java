package com.energyxxer.commodore.commands.chunk;

import com.energyxxer.commodore.types.Type;

public class ChunkForceCommand extends ChunkCommand {
    public ChunkForceCommand(int cx, int cz) {
        this(cx, cz, null);
    }

    public ChunkForceCommand(int cx, int cz, Type dimension) {
        super("force", cx, cz, dimension);
    }
}
