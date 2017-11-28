package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.nbt.TagCompound;

public class DataCommand implements Command {
    /*
    * data get entity <selector> <path> <scale>
    * data remove entity <selector> <path>
    * data merge entity <selector> <nbt>
    * data get block <x y z> <path> <scale>
    * data remove block <x y z> <path>
    * data merge block <x y z> <nbt>
    *
    * DataCommand(Entity entity, String path, float scale) //data get entity <selector> <path> <scale>
    * DataCommand(Entity entity, String path) //data remove entity <selector> <path>
    * DataCommand(Entity entity, TagCompound nbt) //data merge entity <selector> <nbt>
    *
    * DataCommand(CoordinateSet pos, String path, float scale) //data get block <x y z> <path> <scale>
    * DataCommand(CoordinateSet pos, String path) //data remove block <x y z> <path>
    * DataCommand(CoordinateSet pos, TagCompound nbt) //data merge block <x y z> <nbt>
    * */

    private enum Subject {
        ENTITY, BLOCK
    }
    private enum Action {
        GET, REMOVE, MERGE
    }

    private Entity entity;
    private CoordinateSet pos;

    private String path;
    private float scale = 1;
    private TagCompound nbt;

    private Subject subject;
    private Action action;

    /**
     * Creates a data command with the following structure:
     * <br>
     * <code>data get entity &lt;selector&gt; &lt;path&gt; &lt;scale&gt;</code>
     * */
    public DataCommand(Entity entity, String path, float scale) { //data get entity <selector> <path> <scale>
        this.subject = Subject.ENTITY;
        this.action = Action.GET;

        this.entity = entity;
        this.path = path;
        this.scale = scale;
    }
    /**
     * Creates a data command with the following structure:
     * <br>
     * <code>data remove entity &lt;selector&gt; &lt;path&gt;</code>
     * */
    public DataCommand(Entity entity, String path) { //data remove entity <selector> <path>
        this.subject = Subject.ENTITY;
        this.action = Action.REMOVE;

        this.entity = entity;
        this.path = path;
    }
    /**
     * Creates a data command with the following structure:
     * <br>
     * <code>data merge entity &lt;selector&gt; &lt;nbt&gt;</code>
     * */
    public DataCommand(Entity entity, TagCompound nbt) { //data merge entity <selector> <nbt>
        this.subject = Subject.ENTITY;
        this.action = Action.MERGE;

        this.entity = entity;
        this.nbt = nbt;
    }

    /**
     * Creates a data command with the following structure:
     * <br>
     * <code>data get block &lt;x y z&gt; &lt;path&gt; &lt;scale&gt;</code>
     * */
    public DataCommand(CoordinateSet pos, String path, float scale) { //data get block <x y z> <path> <scale>
        this.subject = Subject.BLOCK;
        this.action = Action.GET;

        this.pos = pos;
        this.path = path;
        this.scale = scale;
    }
    /**
     * Creates a data command with the following structure:
     * <br>
     * <code>data remove block &lt;x y z&gt; &lt;path&gt;</code>
     * */
    public DataCommand(CoordinateSet pos, String path) { //data remove block <x y z> <path>
        this.subject = Subject.BLOCK;
        this.action = Action.REMOVE;

        this.pos = pos;
        this.path = path;
    }
    /**
     * Creates a data command with the following structure:
     * <br>
     * <code>data merge block &lt;x y z&gt; &lt;nbt&gt;</code>
     * */
    public DataCommand(CoordinateSet pos, TagCompound nbt) { //data merge block <x y z> <nbt>
        this.subject = Subject.BLOCK;
        this.action = Action.MERGE;

        this.pos = pos;
        this.nbt = nbt;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "data " +
                action.toString().toLowerCase() + " " + // Append the action enum element as first argument
                subject.toString().toLowerCase() + " " + // Append the subject enum element as second argument
                (
                        subject == Subject.ENTITY ?
                                entity.getSelectorAs(sender).toString() : // If subject is an entity, add the
                                                                          // selector as third argument
                                pos.toString()) + " " + // Otherwise, it's a block; therefore add
                                                        // the coordinates of the block
                (
                        action == Action.MERGE ?
                                nbt.toHeadlessString() : // If action is "merge", add the headless TagCompound
                                                         // and return (merge actions have fewer arguments)
                                (path + ( // Otherwise, it's either "get" or "remove". In either case, the next
                                          // argument is the path. Append the path.
                                        action == Action.GET ?
                                                " " + CommandUtils.toString(scale) : // If the action is "get", append
                                                                                     // the scale factor
                                                "") // Otherwise, return without any other arguments (remove)
                                )
                );
    }
}
