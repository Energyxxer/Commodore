package com.energyxxer.commodore.textcomponents.events;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.textcomponents.TextEvent;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class ClickEvent extends TextEvent {
    public enum Action {
        OPEN_URL, OPEN_FILE, RUN_COMMAND, SUGGEST_COMMAND, CHANGE_PAGE, COPY_TO_CLIPBOARD("click_event.copy_to_clipboard");

        private final String availabilityKey;

        Action() {
            this(null);
        }

        Action(String availabilityKey) {
            this.availabilityKey = availabilityKey;
        }

        public void assertAvailable() {
            if(this.availabilityKey != null) VersionFeatureManager.assertEnabled(this.availabilityKey);
        }
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
    public void assertAvailable() {
        action.assertAvailable();
    }

    @Override
    public String toString() {
        return "\"clickEvent\":{\"action\":\"" +
                action.toString().toLowerCase(Locale.ENGLISH) +
                "\",\"value\":\"" +
                CommandUtils.escape(value) +
                "\"}";
    }
}
