package com.energyxxer.commodore.textcomponents.events;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.textcomponents.TextEvent;
import org.jetbrains.annotations.NotNull;

public class InsertionEvent extends TextEvent {
    @NotNull
    private final String text;

    public InsertionEvent(@NotNull String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "\"insertion\":\"" + CommandUtils.escape(text) + "\"";
    }
}
