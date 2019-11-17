package com.energyxxer.commodore.functionlogic.commands.stopsound;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.commands.playsound.PlaySoundCommand;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class StopSoundCommand implements Command {

    @NotNull
    private final Entity player;
    @Nullable
    private final PlaySoundCommand.Source source;
    @Nullable
    private final String sound;

    public StopSoundCommand(@NotNull Entity player) {
        this(player, null, null);
    }

    public StopSoundCommand(@NotNull Entity player, @Nullable PlaySoundCommand.Source source) {
        this(player, source, null);
    }

    public StopSoundCommand(@NotNull Entity player, @Nullable String sound) {
        this(player, null, sound);
    }

    public StopSoundCommand(@NotNull Entity player, @Nullable PlaySoundCommand.Source source, @Nullable String sound) {
        this.player = player;
        this.source = source;
        this.sound = sound;

        player.assertPlayer();
        player.assertEntityFriendly();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "stopsound " + player +
                (source != null ?
                        sound != null ?
                                " " + source.toString().toLowerCase(Locale.ENGLISH) + " " + sound
                                :
                                " " + source.toString().toLowerCase(Locale.ENGLISH)
                        :
                        sound != null ?
                                " * " + sound
                                :
                                ""
                ));
    }

    @Override
    public void assertAvailable() {
        player.assertAvailable();
    }
}
