package com.energyxxer.commodore.functionlogic.commands.teleport;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.commands.teleport.destination.TeleportDestination;
import com.energyxxer.commodore.functionlogic.commands.teleport.facing.TeleportFacing;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TeleportCommand implements Command {
    @Nullable
    private final Entity victim;
    @NotNull
    private final TeleportDestination destination;
    @Nullable
    private final TeleportFacing facing;
    private final boolean checkForBlocks;

    public TeleportCommand(@NotNull TeleportDestination destination) {
        this(destination, null, false);
    }

    public TeleportCommand(@NotNull TeleportDestination destination, boolean checkForBlocks) {
        this(null, destination, checkForBlocks);
    }

    public TeleportCommand(@Nullable Entity victim, @NotNull TeleportDestination destination) {
        this(victim, destination, null, false);
    }

    public TeleportCommand(@Nullable Entity victim, @NotNull TeleportDestination destination, boolean checkForBlocks) {
        this(victim, destination, null, checkForBlocks);
    }

    public TeleportCommand(@NotNull TeleportDestination destination, @Nullable TeleportFacing facing) {
        this(destination, facing, false);
    }

    public TeleportCommand(@NotNull TeleportDestination destination, @Nullable TeleportFacing facing, boolean checkForBlocks) {
        this(null, destination, facing, checkForBlocks);
    }

    public TeleportCommand(@Nullable Entity victim, @NotNull TeleportDestination destination, @Nullable TeleportFacing facing) {
        this(victim, destination, facing, false);
    }

    public TeleportCommand(@Nullable Entity victim, @NotNull TeleportDestination destination, @Nullable TeleportFacing facing, boolean checkForBlocks) {
        this.victim = victim;
        this.destination = destination;
        this.facing = facing;
        this.checkForBlocks = checkForBlocks;

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

        if(checkForBlocks) sb.append(" true");

        String str = sb.toString();

        return new CommandResolution(execContext, str);
    }

    @Override
    public void assertAvailable() {
        if(victim != null) victim.assertAvailable();
        destination.assertAvailable();
        if(facing != null) facing.assertAvailable();
        if(checkForBlocks) VersionFeatureManager.assertEnabled("command.tp.check_for_blocks");
    }
}
