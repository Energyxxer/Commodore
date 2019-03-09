package com.energyxxer.commodoretest;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.commands.CommandGroup;
import com.energyxxer.commodore.functionlogic.commands.chunk.ForceLoadAddCommand;
import com.energyxxer.commodore.functionlogic.commands.chunk.ForceLoadRemoveCommand;
import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteCommand;
import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteInDimension;
import com.energyxxer.commodore.functionlogic.commands.function.FunctionCommand;
import com.energyxxer.commodore.functionlogic.commands.say.SayCommand;
import com.energyxxer.commodore.functionlogic.commands.scoreboard.ScoreGet;
import com.energyxxer.commodore.functionlogic.commands.scoreboard.ScoreSet;
import com.energyxxer.commodore.functionlogic.commands.teleport.TeleportCommand;
import com.energyxxer.commodore.functionlogic.commands.teleport.destination.BlockDestination;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.standard.StandardDefinitionPacks;

import java.io.File;
import java.io.IOException;

import static com.energyxxer.commodore.functionlogic.selector.Selector.BaseSelector.ALL_ENTITIES;
import static com.energyxxer.commodore.functionlogic.selector.Selector.BaseSelector.NEAREST_PLAYER;

public class CommandGroupTest {
    public static void main(String[] args) {
        CommandModule module = new CommandModule("Command Group Test", "cgt");

        //DefinitionPack AETHER_TEST = new DefinitionPack(new DirectoryCompoundInput(new File("C:\\Users\\Usuario\\Commodore\\defpacks\\aetherii")));

        try {
            module.importDefinitions(StandardDefinitionPacks.MINECRAFT_JAVA_LATEST_RELEASE);
            //module.importDefinitions(AETHER_TEST);
        } catch(IOException x) {
            x.printStackTrace();
        }

        Namespace cgt = module.getNamespace("cgt");

        Function function = cgt.getFunctionManager().create("func");

        ExecuteCommand exec = new ExecuteCommand(new SayCommand("hi"), new ExecuteInDimension(module.minecraft.getTypeManager().dimension.get("the_nether")));
        CommandGroup cg = new CommandGroup(function);
        cg.append(exec);
        ExecuteCommand outerExec = new ExecuteCommand(cg, new ExecuteInDimension(module.minecraft.getTypeManager().dimension.get("the_end")));

        System.out.println(module.minecraft.getTypeManager().block.get("stone").getProperties());
        System.out.println(module.minecraft.getTypeManager().enchantment.get("binding_curse").getProperties());

        function.append(new TeleportCommand(new Selector(ALL_ENTITIES), new BlockDestination(new CoordinateSet(5, 0.000000001, 0))));

        function.append(outerExec);

        function.append(new ForceLoadAddCommand(new CoordinateSet(625, 0, 625)));
        function.append(new ExecuteCommand(new ForceLoadAddCommand(new CoordinateSet(625, 0, 625)),
                new ExecuteInDimension(module.minecraft.getTypeManager().dimension.get("the_nether"))));
        function.append(new ExecuteCommand(new ForceLoadRemoveCommand(new CoordinateSet(625, 0, 625)),
                new ExecuteInDimension(module.minecraft.getTypeManager().dimension.get("the_nether"))));

        System.out.println(CommandUtils.numberToPlainString(0.0000001));


        Function a = cgt.getFunctionManager().create("a");
        Function b = cgt.getFunctionManager().create("b");

        Entity all = new Selector(ALL_ENTITIES);
        Entity one = new Selector(NEAREST_PLAYER);
        Objective obj = module.getObjectiveManager().create("temp");

        a.append(new ScoreSet(new LocalScore(obj, all), 5));
        a.append(new ScoreSet(new LocalScore(obj, one), 9));

        a.append(new ScoreGet(new LocalScore(obj, all)));

        a.append(new FunctionCommand(a));


        try {
            module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output" + File.separator + module.getName() + File.separator));
        } catch(IOException x) {
            x.printStackTrace();
        }

        System.out.println(function.getResolvedContent());
    }
}
