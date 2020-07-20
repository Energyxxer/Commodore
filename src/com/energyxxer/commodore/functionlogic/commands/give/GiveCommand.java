package com.energyxxer.commodore.functionlogic.commands.give;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.item.Item;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertStandalone;

public class GiveCommand implements Command {
    @NotNull
    private final Entity player;
    @NotNull
    private final Item item;
    private final int count;

    public GiveCommand(@NotNull Entity player, @NotNull Item item) {
        this(player, item, 1);
    }

    public GiveCommand(@NotNull Entity player, @NotNull Item item, int count) {
        this.player = player;
        this.item = item;
        this.count = count;

        player.assertPlayer();
        player.assertEntityFriendly();
        assertStandalone(item.getItemType());
        if(count < 1) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Count must not be less than 1, found " + count, count, "COUNT");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "give " + player + " " + item + (count != 1 ? " " + count : ""));
    }

    @Override
    public void assertAvailable() {
        player.assertAvailable();
    }
}
