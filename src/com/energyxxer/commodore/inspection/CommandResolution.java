package com.energyxxer.commodore.inspection;

import java.util.ArrayList;
import java.util.Collection;

public class CommandResolution {
    private ExecutionContext execContext;
    private String raw;
    private ArrayList<EntityResolution> entities = new ArrayList<>();

    public CommandResolution(String raw) {
        this.raw = raw;
    }

    public CommandResolution(String raw, Collection<EntityResolution> entities) {
        this.raw = raw;
        this.entities.addAll(entities);
    }


}
