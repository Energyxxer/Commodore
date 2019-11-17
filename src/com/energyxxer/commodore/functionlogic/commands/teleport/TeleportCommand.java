package com.energyxxer.commodore.functionlogic.commands.teleport;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.commands.teleport.destination.TeleportDestination;
import com.energyxxer.commodore.functionlogic.commands.teleport.facing.TeleportFacing;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TeleportCommand implements Command {
    @Nullable
    private final Entity victim;
    @NotNull
    private final TeleportDestination destination;
    @Nullable
    private final TeleportFacing facing;

    public TeleportCommand(@NotNull TeleportDestination destination) {
        this(null, destination);
    }

    public TeleportCommand(@Nullable Entity victim, @NotNull TeleportDestination destination) {
        this(victim, destination, null);
    }

    public TeleportCommand(@NotNull TeleportDestination destination, @Nullable TeleportFacing facing) {
        this(null, destination, facing);
    }

    public TeleportCommand(@Nullable Entity victim, @NotNull TeleportDestination destination, @Nullable TeleportFacing facing) {
        this.victim = victim;
        this.destination = destination;
        this.facing = facing;

        if(victim != null) victim.assertEntityFriendly();
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        StringBuilder sb = new StringBuilder("tp ");
        if(victim != null) sb.append(victim).append(" ");
        sb.append(destination.getRaw());
        if(facing != null) {
            sb.append(' ');
            sb.append(facing.getRaw());
        }

        String str = sb.toString();

        return new CommandResolution(execContext, str);
    }

    @Override
    public void assertAvailable() {
        if(victim != null) victim.assertAvailable();
        destination.assertAvailable();
        if(facing != null) facing.assertAvailable();
    }
}
