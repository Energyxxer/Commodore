package com.energyxxer.commodoretest;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.commands.CommandGroup;
import com.energyxxer.commodore.commands.chunk.ChunkForceCommand;
import com.energyxxer.commodore.commands.chunk.ChunkUnforceCommand;
import com.energyxxer.commodore.commands.execute.ExecuteCommand;
import com.energyxxer.commodore.commands.execute.ExecuteInDimension;
import com.energyxxer.commodore.commands.say.SayCommand;
import com.energyxxer.commodore.commands.teleport.TeleportCommand;
import com.energyxxer.commodore.commands.teleport.destination.BlockDestination;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.GenericEntity;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.ModulePackGenerator;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.module.options.UnusedCommandPolicy;
import com.energyxxer.commodore.selector.Selector;
import com.energyxxer.commodore.standard.StandardDefinitionPacks;

import java.io.File;
import java.io.IOException;

public class CommandGroupTest {
    public static void main(String[] args) {
        CommandModule module = new CommandModule("Command Group Test", "cgt");

        //DefinitionPack AETHER_TEST = new DefinitionPack(new DirectoryCompoundInput(new File("C:\\Users\\Usuario\\Commodore\\defpacks\\aetherii")));

        module.getOptionManager().UNUSED_COMMAND_POLICY.setValue(UnusedCommandPolicy.COMMENT_OUT);
        try {
            module.importDefinitions(StandardDefinitionPacks.MINECRAFT_J_1_13);
            //module.importDefinitions(AETHER_TEST);
        } catch(IOException x) {
            x.printStackTrace();
        }

        Namespace cgt = module.createNamespace("cgt");

        Function function = cgt.getFunctionManager().create("func");

        ExecuteCommand exec = new ExecuteCommand(new SayCommand("hi"), new ExecuteInDimension(module.minecraft.getTypeManager().dimension.get("the_nether")));
        CommandGroup cg = new CommandGroup(function);
        cg.append(exec);
        ExecuteCommand outerExec = new ExecuteCommand(cg, new ExecuteInDimension(module.minecraft.getTypeManager().dimension.get("the_end")));

        System.out.println(module.minecraft.getTypeManager().block.get("stone").getProperties());
        System.out.println(module.minecraft.getTypeManager().enchantment.get("binding_curse").getProperties());

        function.append(new TeleportCommand(new GenericEntity(new Selector(Selector.BaseSelector.ALL_ENTITIES)), new BlockDestination(new CoordinateSet(5,0.000000001,0))));

        function.append(outerExec);

        function.append(new ChunkForceCommand(625, 625));
        function.append(new ChunkForceCommand(625, 625, module.minecraft.getTypeManager().dimension.get("the_nether")));
        function.append(new ChunkUnforceCommand(625, 625, module.minecraft.getTypeManager().dimension.get("overworld")));

        System.out.println(CommandUtils.numberToPlainString(0.0000001));

        try {
            module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output"), ModulePackGenerator.OutputType.FOLDER);
        } catch(IOException x) {
            x.printStackTrace();
        }

        System.out.println(function.getResolvedContent());
    }
}
