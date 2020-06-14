package com.energyxxer.commodore.textcomponents.events.hover;

import com.energyxxer.commodore.textcomponents.TextComponent;
import org.jetbrains.annotations.NotNull;

public class ShowTextHoverEvent extends ContentHoverEvent {
    @NotNull
    private final TextComponent text;

    public ShowTextHoverEvent(@NotNull TextComponent text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "\"hoverEvent\":{\"action\":\"show_text\",\"contents\":" + text.toString() + "}";
    }

    @Override
    public void assertAvailable() {
        super.assertAvailable();
        text.assertAvailable();
    }
}
