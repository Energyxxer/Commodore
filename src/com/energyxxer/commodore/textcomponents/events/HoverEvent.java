package com.energyxxer.commodore.textcomponents.events;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.textcomponents.TextEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class HoverEvent extends TextEvent {
    public enum Action {
        SHOW_TEXT, SHOW_ADVANCEMENT, SHOW_ITEM, SHOW_ENTITY
    }
    @NotNull
    private final Action action;
    @NotNull
    private final Object value;

    public HoverEvent(@NotNull Action action, @NotNull String value) {
        this.action = action;
        this.value = value;
    }

    public HoverEvent(@NotNull Action action, @NotNull TextComponent value) {
        this.action = action;
        this.value = value;
    }

    @Override
    public String toString() {
        return "\"hoverEvent\":{\"action\":\"" +
                action.toString().toLowerCase(Locale.ENGLISH) +
                "\",\"value\":" +
                (value instanceof TextComponent ? value.toString() : "\"" + CommandUtils.escape(value.toString()) + "\"") +
                "}";
    }
}
