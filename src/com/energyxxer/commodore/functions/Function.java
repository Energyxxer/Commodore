package com.energyxxer.commodore.functions;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.entity.GenericEntity;
import com.energyxxer.commodore.selector.Selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Function {
    private final FunctionManager parent;

    private String name;
    private ArrayList<FunctionWriter> content = new ArrayList<>();
    private Entity sender;

    private boolean contentResolved = false;
    private String resolvedContent = null;

    Function(FunctionManager parent, String name) {
        this(parent, name, new GenericEntity(new Selector(Selector.BaseSelector.SENDER)));
    }

    Function(FunctionManager parent, String name, Entity sender) {
        this.parent = parent;
        this.name = name;
        this.sender = sender;
    }

    public Entity getSender() {
        return sender;
    }

    public void append(FunctionWriter... writers) {
        append(Arrays.asList(writers));
    }

    public void append(Collection<FunctionWriter> writers) {
        this.content.addAll(writers);
        writers.forEach(FunctionWriter::onAppend);
        contentResolved = false;
    }

    public String getName() {
        return name;
    }

    public FunctionManager getParent() {
        return parent;
    }

    public String getResolvedContent() {
        if(!contentResolved) {
            StringBuilder sb = new StringBuilder("# ");
            sb.append(name);
            sb.append('\n');

            for(FunctionWriter writer : content) {
                String content = writer.toFunctionContent(this);
                if(content != null) {
                    sb.append(content);
                    sb.append('\n');
                }
            }
            resolvedContent = sb.toString();
        }
        return resolvedContent;
    }

    @Override
    public String toString() {
        return "[Function " + name + " : " + content.size() + " " + ((content.size() == 1) ? "entry" : "entries") + "]";
    }
}
