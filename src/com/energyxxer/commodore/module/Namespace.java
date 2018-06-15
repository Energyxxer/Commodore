package com.energyxxer.commodore.module;

import com.energyxxer.commodore.functions.FunctionManager;
import com.energyxxer.commodore.tags.TagManager;
import com.energyxxer.commodore.types.defaults.TypeManager;

public class Namespace {
    private final CommandModule owner;
    final String name;

    private final FunctionManager fncMgr;
    private final TagManager tagMgr;
    private final TypeManager typeMgr;

    public Namespace(CommandModule owner, String name) {
        this.owner = owner;
        this.name = name;

        this.fncMgr = new FunctionManager(this);
        this.tagMgr = new TagManager(this);
        this.typeMgr = new TypeManager(this);
    }

    public void compile() {
        fncMgr.resolveAccessLogs();
    }

    @Override
    public String toString() {
        return name;
    }

    public CommandModule getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public FunctionManager getFunctionManager() {
        return fncMgr;
    }

    public TagManager getTagManager() {
        return tagMgr;
    }

    public TypeManager getTypeManager() {
        return typeMgr;
    }

    public Namespace clone(CommandModule newOwner) {
        Namespace clone = new Namespace(newOwner, name);

        clone.fncMgr.join(this.fncMgr);
        clone.tagMgr.join(this.tagMgr);
        clone.typeMgr.join(this.typeMgr);

        return clone;
    }
}
