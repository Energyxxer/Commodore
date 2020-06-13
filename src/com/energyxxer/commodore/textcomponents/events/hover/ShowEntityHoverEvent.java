package com.energyxxer.commodore.textcomponents.events.hover;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.textcomponents.TextComponent;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import static com.energyxxer.commodore.types.TypeAssert.assertEntity;

public class ShowEntityHoverEvent extends ContentHoverEvent {
    private final @NotNull Type type;
    private final @NotNull UUID id;
    private final @Nullable TextComponent name;

    public ShowEntityHoverEvent(@NotNull Type type, @NotNull UUID id) {
        this(type, id, null);
    }

    public ShowEntityHoverEvent(@NotNull Type type, @NotNull UUID id, @Nullable TextComponent name) {
        this.type = type;
        this.id = id;
        this.name = name;

        assertEntity(type);
    }

    @Override
    public String toString() {
        String content = "{\"type\":\"" + CommandUtils.escape(type.toString()) + "\",\"id\":\"" + CommandUtils.escape(id.toString()) + "\"";
        if(name != null) {
            content += ",\"name\":" + name.toString();
        }
        content += "}";
        return "\"hoverEvent\":{\"action\":\"show_entity\",\"contents\":" + content + "}";
    }

    @Override
    public void assertAvailable() {
        if(name != null) name.assertAvailable();
    }
}
