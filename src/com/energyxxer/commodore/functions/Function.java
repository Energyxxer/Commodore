package com.energyxxer.commodore.functions;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.entity.GenericEntity;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.score.access.ScoreAccessLog;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.selector.Selector;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Function {
    private final FunctionManager parent;

    private Namespace namespace;
    private String path;
    private ArrayList<FunctionWriter> content = new ArrayList<>();
    private ExecutionContext execContext;

    private boolean contentResolved = false;
    private String resolvedContent = null;

    private ScoreAccessLog accessLog = new ScoreAccessLog(this);

    Function(FunctionManager parent, Namespace namespace, String path) {
        this(parent, namespace, path, new ExecutionContext(new GenericEntity(new Selector(Selector.BaseSelector.SENDER))));
    }

    Function(FunctionManager parent, Namespace namespace, String path, ExecutionContext execContext) {
        this.parent = parent;
        this.execContext = execContext;

        this.namespace = namespace;
        this.path = path;
    }

    public Entity getSender() {
        return execContext.getFinalSender();
    }

    public void append(FunctionWriter... writers) {
        append(Arrays.asList(writers));
    }

    public void append(Collection<FunctionWriter> writers) {
        this.content.addAll(writers);
        writers.forEach(w -> w.onAppend(this));
        contentResolved = false;
    }

    public String getFullName() {
        return namespace.toString() + ':' + path;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public String getPath() {
        return path;
    }

    public String getFilePath() {
        return path.replace(".", File.separator);
    }

    public FunctionManager getParent() {
        return parent;
    }

    public String getResolvedContent() {
        if(!contentResolved) {
            StringBuilder sb = new StringBuilder("# ");
            sb.append(getFullName());
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

    public ScoreAccessLog getAccessLog() {
        return accessLog;
    }

    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return accessLog.getScoreboardAccesses();
    }

    @Override
    public String toString() {
        return "[Function " + getFullName() + " : " + content.size() + " " + ((content.size() == 1) ? "entry" : "entries") + "]";
    }
}
