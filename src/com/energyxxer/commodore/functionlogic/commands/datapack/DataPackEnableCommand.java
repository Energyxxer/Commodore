package com.energyxxer.commodore.functionlogic.commands.datapack;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

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
    }

    public DataPackEnableCommand(@NotNull String pack, @NotNull Order order, @Nullable String secondPack) {
        this.pack = pack;
        this.order = order;
        this.secondPack = secondPack;

        if(order.takesSecondPack && secondPack == null)
            throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "Order '" + order + "' requires a second datapack parameter");

        if(secondPack != null && !order.takesSecondPack)
            throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "Order '" + order + "' doesn't require a second datapack parameter, yet '" + secondPack + "' was passed", secondPack);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "datapack enable " + CommandUtils.quoteIfNecessary(pack) + " " + order.toString().toLowerCase(Locale.ENGLISH) + (order.takesSecondPack ? " " + CommandUtils.quoteIfNecessary(secondPack) : ""));
    }
}
