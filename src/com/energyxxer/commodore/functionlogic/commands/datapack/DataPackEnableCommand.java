package com.energyxxer.commodore.functionlogic.commands.datapack;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DataPackEnableCommand extends DataPackCommand {

    public enum Order {
        AFTER(true), BEFORE(true), FIRST(false), LAST(false);

        private final boolean takesSecondPack;

        Order(boolean takesSecondPack) {
            this.takesSecondPack = takesSecondPack;
        }
    }

    @NotNull
    private final String pack;
    @NotNull
    private final Order order;
    @Nullable
    private final String secondPack;

    public DataPackEnableCommand(@NotNull String pack, @NotNull Order order) {
        this(pack, order, null);

        if(order.takesSecondPack)
            throw new IllegalArgumentException("Order '" + order + "' requires a second datapack parameter");
    }

    public DataPackEnableCommand(@NotNull String pack, @NotNull Order order, @Nullable String secondPack) {
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
