package com.energyxxer.commodore.functionlogic.score;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.functions.FunctionSection;
import com.energyxxer.commodore.textcomponents.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;

public class ObjectiveManager {

    @NotNull
    private final HashMap<@NotNull String, @NotNull Objective> objectives = new HashMap<>();
    @Nullable
    private FunctionSection creationFunction;

    public ObjectiveManager() {
    }

    public Objective get(@NotNull String name) {
        return objectives.get(name);
    }

    @NotNull
    public Objective getOrCreate(@NotNull String name) {
        Objective existing = objectives.get(name);

        return (existing != null) ? existing : forceCreate(name);
    }

    public boolean exists(@NotNull String name) {
        return objectives.containsKey(name);
    }

    @NotNull
    public Objective create(@NotNull String name) {
        return create(name, "dummy");
    }

    @NotNull
    public Objective create(@NotNull String name, @NotNull String type) {
        return create(name, type, null);
    }

    @NotNull @Deprecated
    public Objective create(@NotNull String name, boolean field) {
        return create(name, "dummy");
    }

    @NotNull @Deprecated
    public Objective create(@NotNull String name, @NotNull String type, boolean field) {
        return create(name, type, null);
    }

    @NotNull @Deprecated
    public Objective create(@NotNull String name, @NotNull String type, @Nullable TextComponent displayName, boolean field) {
        return create(name, type, displayName);
    }

    public Objective create(@NotNull String name, @NotNull String type, @Nullable TextComponent displayName) {
        if(!exists(name)) return forceCreate(name, type, displayName);
        throw new CommodoreException(CommodoreException.Source.DUPLICATION_ERROR, "An objective by the name '" + name + "' already exists", name);
    }

    @NotNull
    private Objective forceCreate(@NotNull String name) {
        return forceCreate(name, "dummy", null);
    }

    @NotNull
    private Objective forceCreate(@NotNull String name, @NotNull String type, @Nullable TextComponent displayName) {
        Objective newObjective = new Objective(this, name, type, displayName);
        objectives.put(name, newObjective);
        return newObjective;
    }

    public void dumpObjectiveCreators(@NotNull FunctionSection function) {
        objectives.values().forEach(o -> {
            function.append(o.getObjectiveCreator());
        });
    }

    public void setCreationFunction(@Nullable FunctionSection creationFunction) {
        this.creationFunction = creationFunction;
    }

    public void compile() {
        objectives.values().forEach(Objective::assertAvailable);
        if(creationFunction != null) {
            dumpObjectiveCreators(creationFunction);
        }
    }

    public Collection<Objective> getAll() {
        return objectives.values();
    }

    @Override
    public String toString() {
        return "Objective Manager (" + objectives.size() + " objective" + (objectives.size() == 1 ? "" : "s") + ")";
    }
}
