package com.energyxxer.commodore.functionlogic.commands.attribute;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertAttribute;

public abstract class AttributeCommand implements Command {
    protected @NotNull Entity target;
    protected @NotNull Type type;

    public AttributeCommand(@NotNull Entity target, @NotNull Type type) {
        this.target = target;
        this.type = type;

        target.assertEntityFriendly();
        target.assertSingle();
        assertAttribute(type);
    }

    @NotNull
    public Entity getTarget() {
        return target;
    }

    @NotNull
    public Type getAttributeType() {
        return type;
    }

    protected String getStart() {
        return "attribute " + target + " " + type.toSafeString() + " ";
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.attribute");
    }
}
