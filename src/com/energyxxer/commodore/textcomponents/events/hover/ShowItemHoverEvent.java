package com.energyxxer.commodore.textcomponents.events.hover;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class ShowItemHoverEvent extends ContentHoverEvent {
    private final @NotNull Item item;
    private final int count;

    public ShowItemHoverEvent(@NotNull Type type) {
        this(new Item(type), 1);
    }

    public ShowItemHoverEvent(@NotNull Item item) {
        this(item, 1);
    }

    public ShowItemHoverEvent(@NotNull Item item, int count) {
        this.item = item;
        this.count = count;
    }

    @Override
    public String toString() {
        String content = "\"" + CommandUtils.escape(item.getItemType().toString()) + "\"";
        if(count != 1 || item.getNBT() != null) {
            content = "{\"id\":" + content;
            if(count != 1) {
                content += ",\"count\":" + count;
            }
            if(item.getNBT() != null) {
                content += ",\"tag\":\"" + CommandUtils.escape(item.getNBT().toHeadlessString()) + "\"";
            }
            content += "}";
        }

        return "\"hoverEvent\":{\"action\":\"show_item\",\"contents\":" + content + "}";
    }

    @Override
    public void assertAvailable() {
        item.assertAvailable();
    }
}
