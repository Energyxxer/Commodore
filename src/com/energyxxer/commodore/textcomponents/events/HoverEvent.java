package com.energyxxer.commodore.textcomponents.events;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.textcomponents.TextEvent;

public class HoverEvent extends TextEvent {
    public enum Action {
        SHOW_TEXT, SHOW_ADVANCEMENT, SHOW_ENTITY
    }

    private final Action action;
    private final String value;

    public HoverEvent(Action action, String value) {
        this.action = action;
        this.value = value;
    }

    public HoverEvent(Action action, TextComponent value) {
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
