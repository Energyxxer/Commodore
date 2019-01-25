package com.energyxxer.commodore.textcomponents.events;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.textcomponents.TextEvent;
import org.jetbrains.annotations.NotNull;

public class HoverEvent extends TextEvent {
    public enum Action {
        SHOW_TEXT, SHOW_ADVANCEMENT, SHOW_ITEM, SHOW_ENTITY
    }
    @NotNull
    private final Action action;
    @NotNull
    private final String value;

    public HoverEvent(@NotNull Action action, @NotNull String value) {
        this.action = action;
        this.value = value;
    }

    public HoverEvent(@NotNull Action action, @NotNull TextComponent value) {
        this.action = action;
        this.value = value.toString();
    }

    @Override
    public String toString() {
        return "\"hoverEvent\":{\"action\":\"" +
                action.toString().toLowerCase() +
                "\",\"value\":\"" +
                CommandUtils.escape(value) +
                "\"}";
    }
}
