package com.energyxxer.commodore.module;

import com.energyxxer.commodore.functions.FunctionManager;
import com.energyxxer.commodore.tags.TagManager;
import com.energyxxer.commodore.types.TypeManager;

public class Namespace {
    private final CommandModule owner;
    final String name;

    private FunctionManager fncMgr;
    private TagManager tagMgr;
    private TypeManager typeMgr;

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
}
