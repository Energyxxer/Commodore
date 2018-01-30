package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class FillCommand implements Command {

    private CoordinateSet pos1;
    private CoordinateSet pos2;

    private Block block;

    public FillCommand(CoordinateSet pos1, CoordinateSet pos2, Block block) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.block = block;
        if(!block.isConcrete()) throw new IllegalArgumentException("Tags aren't allowed here, only actual blocks");
    }

    protected String getMaskExtra() {
        return "";
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "fill " + pos1.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + pos2.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + block + getMaskExtra();
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "fill " + pos1.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + pos2.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + block + getMaskExtra());
    }
}
