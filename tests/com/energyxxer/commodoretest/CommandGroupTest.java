package com.energyxxer.commodoretest;

import com.energyxxer.commodore.commands.CommandGroup;
import com.energyxxer.commodore.commands.execute.ExecuteCommand;
import com.energyxxer.commodore.commands.execute.ExecuteInDimension;
import com.energyxxer.commodore.commands.say.SayCommand;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.ModulePackGenerator;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.module.options.UnusedCommandPolicy;
import com.energyxxer.commodore.standard.StandardDefinitionPacks;

import java.io.File;

public class CommandGroupTest {
    public static void main(String[] args) {
        CommandModule module = new CommandModule("Command Group Test", "cgt");
        module.getOptionManager().UNUSED_COMMAND_POLICY.setValue(UnusedCommandPolicy.COMMENT_OUT);
        StandardDefinitionPacks.MINECRAFT_J_1_13.initialize(module);

        Namespace cgt = module.getNamespace("cgt");

        Function function = cgt.getFunctionManager().create("func");

        ExecuteCommand exec = new ExecuteCommand(new SayCommand("hi"), new ExecuteInDimension(module.minecraft.getTypeManager().dimension.get("the_nether")));
        CommandGroup cg = new CommandGroup(function);
        cg.append(exec);
        ExecuteCommand outerExec = new ExecuteCommand(cg, new ExecuteInDimension(module.minecraft.getTypeManager().dimension.get("the_end")));

        function.append(outerExec);

        module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output"), ModulePackGenerator.OutputType.FOLDER);

        System.out.println(function.getResolvedContent());
    }
}
