package com.energyxxer.commodore.commands.chunk;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertDimension;

public abstract class ChunkCommand implements Command {
    private String subcommand;
    private int cx;
    private int cz;

    private Type dimension;

    public ChunkCommand(String subcommand, int cx, int cz, Type dimension) {
        this.subcommand = subcommand;
        this.cx = cx;
        this.cz = cz;
        this.dimension = dimension;

        if(dimension != null) assertDimension(dimension);
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "chunk" + ((dimension != null) ? " in " + dimension : "") + " " + subcommand + " " + cx + " " + cz);
    }
}
