package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.functions.FunctionSection;
import com.energyxxer.commodore.functions.FunctionWriter;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.CommandResolutionLine;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class CommandGroup implements Command, FunctionSection {
    private FunctionSection parent;
    private ExecutionContext groupExecContext = null;
    private ArrayList<FunctionWriter> writers = new ArrayList<>();
    private ArrayList<ScoreboardAccess> accesses = new ArrayList<>();

    public CommandGroup(FunctionSection parent) {
        this.parent = parent;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        ArrayList<CommandResolutionLine> lines = new ArrayList<>();
        for(FunctionWriter w : writers) {
            if(w instanceof Command) {
                CommandResolution resolution = ((Command) w).resolveCommand(execContext);
                lines.addAll(resolution.getLines());
            }
        }
        return new CommandResolution(lines);
    }/*

    @Override
    public String toFunctionContent(FunctionSection section) {
        StringBuilder sb = new StringBuilder();
        for(FunctionWriter w : writers) {
            if(w instanceof Command) {
                try {
                    UnusedCommandPolicy policy = section.getNamespace().getOwner().getOptionManager().UNUSED_COMMAND_POLICY.getValue();
                    if(policy == KEEP) {
                        sb.append(((Command) w).resolveCommand(section.getExecutionContext()).construct());
                    } else {
                        boolean used = !isScoreboardManipulation() || isUsed();

                        if(!used && policy == REMOVE) return null;

                        String content = ((Command) w).resolveCommand(section.getExecutionContext()).construct();

                        if(!used && policy == COMMENT_OUT) content = "# [UNUSED]: " + content;

                        sb.append(content);
                    }
                    sb.append('\n');
                } catch(IllegalStateException x) {
                    if(section instanceof Function) System.out.println(((Function) section).getAccessLog());
                    throw x;
                }
            } else {
                sb.append(w.toFunctionContent(section));
                sb.append('\n');
            }
        }
    }*/

    @Override
    public void append(Collection<FunctionWriter> writers) {
        this.writers.addAll(writers);
    }

    @Override
    public Namespace getNamespace() {
        return parent.getNamespace();
    }

    @Override
    public ExecutionContext getExecutionContext() {
        return groupExecContext;
    }

    @Override
    public void onAppend(FunctionSection section, ExecutionContext execContext) {
        this.groupExecContext = execContext;
        writers.forEach(w -> {
            if(w instanceof Command) {
                ((Command) w).onAppend(section, execContext);
            } else {
                w.onAppend(section);
            }
        });
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        if(accesses == null) {
            accesses = new ArrayList<>();
            writers.forEach(w -> {
                if(w instanceof Command) {
                    accesses.addAll(((Command) w).getScoreboardAccesses());
                }
            });
        }
        return accesses;
    }

    @Override
    public boolean isScoreboardManipulation() {
        return false;
    }
}
