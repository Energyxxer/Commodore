package com.energyxxer.commodore.textcomponents.events.hover;

import com.energyxxer.commodore.textcomponents.TextEvent;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;

public abstract class ContentHoverEvent extends TextEvent {

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("textcomponent.hover_event.content");
    }
}
