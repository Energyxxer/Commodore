package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.types.Type;

public class TeamReference extends Type {
    public static final String CATEGORY = "team_reference";

    public TeamReference(String name) {
        super(CATEGORY, null, name);
        if(name.isEmpty())
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Team name must not be empty", name);
        if(!name.matches(CommandUtils.IDENTIFIER_ALLOWED))
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Team name '" + name + "' has illegal characters. Does not match regex: " + CommandUtils.IDENTIFIER_ALLOWED, name);
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
