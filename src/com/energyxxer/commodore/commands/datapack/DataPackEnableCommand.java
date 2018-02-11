package com.energyxxer.commodore.commands.datapack;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class DataPackEnableCommand extends DataPackCommand {

    public enum Order {
        AFTER(true), BEFORE(true), FIRST(false), LAST(false);

        private final boolean takesSecondPack;

        Order(boolean takesSecondPack) {
            this.takesSecondPack = takesSecondPack;
        }
    }

    private final String pack;
    private final Order order;
    private String secondPack = null;

    public DataPackEnableCommand(String pack, Order order) {
        this(pack, order, null);

        if(order.takesSecondPack)
            throw new IllegalArgumentException("Order '" + order + "' requires a second datapack parameter");
    }

    public DataPackEnableCommand(String pack, Order order, String secondPack) {
        this.pack = pack;
        this.order = order;
        this.secondPack = secondPack;

        if(secondPack != null && !order.takesSecondPack)
            System.out.println("[Commodore] [NOTICE] Order '" + order + "' doesn't require a second datapack parameter, yet '" + secondPack + "' was passed");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "datapack enable " + pack + " " + order.toString().toLowerCase() + (order.takesSecondPack ? " " + secondPack : ""));
    }
}
