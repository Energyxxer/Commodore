package com.energyxxer.commodore.inspection;

import com.energyxxer.commodore.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CommandResolution {
    private ExecutionContext execContext;
    private String raw;
    private ArrayList<Entity> entities = new ArrayList<>();

    public CommandResolution(ExecutionContext execContext, String raw, Collection<Entity> entities) {
        this.execContext = execContext;
        this.raw = raw;
        if(entities != null) this.entities.addAll(entities);
    }

    public CommandResolution(ExecutionContext execContext, String raw, Entity... entities) {
        this(execContext, raw, Arrays.asList(entities));
    }
}
