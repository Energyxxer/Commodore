package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.functions.FunctionSection;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecuteCommand implements Command {
    @NotNull
    private final Command chainedCommand;
    @NotNull
    private final ArrayList<ExecuteModifier> modifiers = new ArrayList<>();

    public ExecuteCommand(@NotNull Command chainedCommand) {
        this(chainedCommand, Collections.emptyList());
    }

    public ExecuteCommand(@NotNull Command chainedCommand, @NotNull ExecuteModifier... modifiers) {
        this(chainedCommand, Arrays.asList(modifiers));
    }

    public ExecuteCommand(@NotNull Command chainedCommand, @NotNull Collection<ExecuteModifier> modifiers) {
        this.chainedCommand = chainedCommand;
        this.modifiers.addAll(modifiers);
    }

    public void addModifier(@NotNull ExecuteModifier modifier) {
        this.modifiers.add(modifier);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        ArrayList<ExecuteModifier> joinedModifiers = execContext.getModifiers();
        joinedModifiers.addAll(modifiers);
        ExecutionContext chainedContext = new ExecutionContext(joinedModifiers);
        return chainedCommand.resolveCommand(chainedContext);
    }

    @Override
    public void onAppend(@NotNull FunctionSection section, @NotNull ExecutionContext execContext) {
        Command.super.onAppend(section, execContext);
        chainedCommand.onAppend(section, new ExecutionContext(this.modifiers));
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("execute_modifiers");
        for(ExecuteModifier modifier : modifiers) {
            modifier.assertAvailable();
        }
        chainedCommand.assertAvailable();
    }

    private static final Pattern OLD_EXECUTE_PATTERN = Pattern.compile("execute (@(?:[pears]|initiator)(?:\\[.*?\\])?) ?((?:(?:[~^](?:[-+]?\\d*(?:\\.\\d+)?)|(?:[-+]?(?:\\d*\\.)?\\d+?))\\s?){3})\\s(?:detect ((?:(?:[~^](?:[-+]?\\d*(?:\\.\\d+)?)|(?:[-+]?(?:\\d*\\.)?\\d+?))\\s?){3})\\s([a-zA-Z0-9_:]+|\".+\")\\s?(-?\\d+|\\[.+?\\]))?\\s?((?!detect).+)");

    public static String convert(String oldExecute) {
        Matcher matcher = OLD_EXECUTE_PATTERN.matcher(oldExecute);
        if(matcher.matches()) {
            String base = "execute as " + matcher.group(1) + " at @s";
            if(!"~~~".equals(matcher.group(2).replace(" ",""))) {
                base += " positioned " + matcher.group(2);
            }
            if(matcher.group(3) != null) {
                //detect
                base += " if block " + matcher.group(3) + " " + matcher.group(4) + " " + matcher.group(5);
            }
            String followUpCommand = matcher.group(6);
            if(followUpCommand.startsWith("execute @")) {
                followUpCommand = convert(followUpCommand);
            }
            return base + " run " + followUpCommand;
        }
        throw new IllegalArgumentException();
    }
}
