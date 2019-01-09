package com.energyxxer.commodore.functionlogic.commands;

import com.energyxxer.commodore.functionlogic.functions.FunctionSection;
import com.energyxxer.commodore.functionlogic.functions.FunctionWriter;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolutionLine;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.module.Namespace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class CommandGroup implements Command, FunctionSection {
    @NotNull
    private final FunctionSection parent;
    @Nullable
    private ExecutionContext groupExecContext = null;
    @NotNull
    private final ArrayList<FunctionWriter> writers = new ArrayList<>();

    public CommandGroup(@NotNull FunctionSection parent) {
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
    }

    @Override
    public void append(@NotNull Collection<FunctionWriter> writers) {
        this.writers.addAll(writers);
    }

    @NotNull
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

}
