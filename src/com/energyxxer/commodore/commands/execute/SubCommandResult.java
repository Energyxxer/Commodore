package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubCommandResult {

    public enum ExecutionChange {
        X, Y, Z, YAW, PITCH, DIMENSION
    }

    private String subCommand;
    private Entity newSender;
    private List<ExecutionChange> changes;

    public SubCommandResult(String subCommand) {
        this(subCommand, null, Collections.emptyList());
    }

    public SubCommandResult(String subCommand, Entity newSender) {
        this(subCommand, newSender, Collections.emptyList());
    }

    public SubCommandResult(String subCommand, ExecutionChange... changes) {
        this(subCommand, null, changes);
    }

    public SubCommandResult(String subCommand, Entity newSender, ExecutionChange... changes) {
        this(subCommand, newSender, Arrays.asList(changes));
    }

    public SubCommandResult(String subCommand, Entity newSender, List<ExecutionChange> changes) {
        this.subCommand = subCommand;
        this.newSender = newSender;
        this.changes = changes;
    }

    public String getSubCommand() {
        return subCommand;
    }

    public Entity getNewSender() {
        return newSender;
    }

    public List<ExecutionChange> getChanges() {
        return changes;
    }
}
