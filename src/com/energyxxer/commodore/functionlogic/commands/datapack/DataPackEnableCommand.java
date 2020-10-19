package com.energyxxer.commodore.functionlogic.commands.datapack;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DataPackEnableCommand extends DataPackCommand {

    public static class Order {
        @NotNull
        final String raw;

        Order(@NotNull String operation) {
            this.raw = operation;
        }

        @NotNull
        @Contract(" -> new")
        public static Order FIRST() { return new Order(" first"); }
        @NotNull
        @Contract("_ -> new")
        public static Order BEFORE(String referencePack) { return new Order(" before " + CommandUtils.quoteIfNecessary(referencePack)); }
        @NotNull
        @Contract("_ -> new")
        public static Order AFTER(String referencePack) { return new Order(" after " + CommandUtils.quoteIfNecessary(referencePack)); }
        @NotNull
        @Contract(" -> new")
        public static Order LAST() { return new Order(""); }
    }

    @NotNull
    private final String pack;
    @NotNull
    private final Order order;

    public DataPackEnableCommand(@NotNull String pack) {
        this(pack, Order.LAST());
    }

    public DataPackEnableCommand(@NotNull String pack, @NotNull Order order) {
        this.pack = pack;
        this.order = order;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "datapack enable " + CommandUtils.quoteIfNecessary(pack) + order.raw);
    }
}
