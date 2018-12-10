package com.energyxxer.commodore.textcomponents.events;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.textcomponents.TextEvent;
import org.jetbrains.annotations.NotNull;

public class ClickEvent extends TextEvent {
    public enum Action {
        RUN_COMMAND, SUGGEST_COMMAND, OPEN_URL
    }
    @NotNull
    private final Action action;
    @NotNull
    private final String value;

    public ClickEvent(@NotNull Action action, @NotNull String value) {
        this.action = action;
        this.value = value;
    }

    @Override
    public String toString() {
        return "\"clickEvent\":{\"action\":\"" +
                action.toString().toLowerCase() +
                "\",\"value\":\"" +
                CommandUtils.escape(value) +
                "\"}";
    }
}
