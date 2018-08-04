package com.energyxxer.commodore.functions;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.entity.GenericEntity;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.module.Exportable;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.score.MacroScore;
import com.energyxxer.commodore.score.MacroScoreHolder;
import com.energyxxer.commodore.score.access.ScoreAccessLog;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.selector.Selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Function implements FunctionSection, Exportable {
    private final FunctionManager parent;

    private final Namespace namespace;
    private final String path;
    private final ArrayList<FunctionWriter> content = new ArrayList<>();
    private final MacroScoreHolder senderMacro;

    private ExecutionContext execContext;
    private byte accessesResolvedState = 0;
    private boolean contentResolved = false;
    private String resolvedContent = null;

    private final ScoreAccessLog accessLog = new ScoreAccessLog(this);

    private boolean export = true;

    Function(FunctionManager parent, Namespace namespace, String path) {
        this(parent, namespace, path, null);
    }

    Function(FunctionManager parent, Namespace namespace, String path, Entity sender) {
        this.parent = parent;

        this.namespace = namespace;
        this.path = path;

        Entity newSender = (sender != null) ? sender.clone() : new GenericEntity(new Selector(Selector.BaseSelector.SENDER));
        this.senderMacro = new MacroScoreHolder("FS#" + path);
        newSender.addMacroHolder(this.senderMacro);

        this.execContext = new ExecutionContext(newSender);
    }

    public Entity getSender() {
        return execContext.getFinalSender();
    }

    @Override
    public void append(FunctionWriter... writers) {
        append(Arrays.asList(writers));
    }

    @Override
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

    @Override
    public String getExportPath() {
        return "functions/" + getPath() + ".mcfunction";
    }

    public FunctionManager getParent() {
        return parent;
    }

    public String getResolvedContent() {
        if(!contentResolved) {
            StringBuilder sb = new StringBuilder();

            {
                sb.append("# ");
                sb.append("-------- ");
                sb.append(getHeader());
                sb.append(" --------");
                sb.append('\n');
            }

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

    public void resolveAccessLogs() {
        if(accessesResolvedState >= 1) return;
        accessesResolvedState = 1;
        content.forEach(w -> {
            if(w instanceof Command) {
                for(ScoreboardAccess access : ((Command) w).getScoreboardAccesses()) {
                    access.setFunction(this);
                    accessLog.filterAccess(access);
                }
            }
        });
        accessLog.resolve();
        accessesResolvedState = 2;
    }

    public Collection<ScoreboardAccess> getScoreboardAccesses(ExecutionContext execContext) {
        Collection<MacroScoreHolder> replacementMacros = execContext.getFinalSender().getMacroHolders();

        ArrayList<ScoreboardAccess> newAccesses = new ArrayList<>();
        for(ScoreboardAccess access : accessLog.getScoreboardAccesses()) {
            ArrayList<MacroScore> newScores = new ArrayList<>();
            for(MacroScore score : access.getScores()) {
                if(score.getHolder() != senderMacro) {
                    newScores.add(score);
                } else {
                    for(MacroScoreHolder replacement : replacementMacros) {
                        newScores.add(new MacroScore(replacement, score.getObjective()));
                    }
                }
            }
            ScoreboardAccess newAccess = new ScoreboardAccess(newScores, access.getType(), access.getDependencies());
            newAccess.setResolution(access.getResolution());
            newAccesses.add(newAccess);
        }

        return newAccesses;
    }

    public ExecutionContext getExecutionContext() {
        return execContext;
    }

    public void setExecutionContext(ExecutionContext execContext) {
        if(content.isEmpty()) {
            this.execContext = execContext;
        } else throw new IllegalStateException("Cannot change function execution context when it already has entries");
    }

    public String getHeader() {
        return "[Function " + getFullName() + " : " + content.size() + " " + ((content.size() == 1) ? "entry" : "entries") + "]";
    }

    public int getEntryCount() {
        return content.size();
    }

    @Override
    public boolean shouldExport() {
        return export;
    }

    @Override
    public void setExport(boolean export) {
        this.export = export;
    }

    @Override
    public String getContents() {
        return getResolvedContent();
    }

    @Override
    public String toString() {
        return getHeader();
    }
}
