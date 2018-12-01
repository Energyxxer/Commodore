package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.types.Type;

public class TeamReference extends Type {
    public static final String CATEGORY = "team_reference";

    public TeamReference(String name) {
        super(CATEGORY, null, name);
        if(name.isEmpty())
            throw new IllegalArgumentException("Team name must not be empty");
        if(!name.matches(CommandUtils.IDENTIFIER_ALLOWED))
            throw new IllegalArgumentException("Team name '" + name + "' has illegal characters. Does not match regex: " + CommandUtils.IDENTIFIER_ALLOWED);
    }

    @Override
    public boolean useNamespace() {
        return false;
    }

    @Override
    public boolean isStandalone() {
        return true;
    }
}
