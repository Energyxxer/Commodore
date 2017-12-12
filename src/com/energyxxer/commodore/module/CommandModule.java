package com.energyxxer.commodore.module;

import com.energyxxer.commodore.functions.FunctionManager;
import com.energyxxer.commodore.score.ObjectiveManager;
import com.energyxxer.commodore.tags.TagManager;

public class CommandModule {

    private String name;
    private String prefix;

    private ObjectiveManager objMgr;
    private FunctionManager fncMgr;
    private TagManager tagMgr;

    public CommandModule(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;

        this.objMgr = new ObjectiveManager(this);
        this.fncMgr = new FunctionManager(this);
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public ObjectiveManager getObjectiveManager() {
        return objMgr;
    }

    public FunctionManager getFunctionManager() {
        return fncMgr;
    }

    public void compile() {
        objMgr.resolveAccessLogs();
    }

    @Override
    public String toString() {
        return "Module [" + name + "]";
    }
}
