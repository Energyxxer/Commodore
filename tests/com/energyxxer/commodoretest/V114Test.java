package com.energyxxer.commodoretest;

import com.energyxxer.commodore.commands.setblock.SetblockCommand;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.ModulePackGenerator;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.module.options.UnusedCommandPolicy;
import com.energyxxer.commodore.standard.StandardDefinitionPacks;

import java.io.File;
import java.io.IOException;

public class V114Test {
    public static void main(String[] args) {
        CommandModule module = new CommandModule("1.14 Test", "vp");

        module.getOptionManager().UNUSED_COMMAND_POLICY.setValue(UnusedCommandPolicy.COMMENT_OUT);
        try {
            module.importDefinitions(StandardDefinitionPacks.MINECRAFT_JAVA_LATEST_SNAPSHOT);
        } catch(IOException x) {
            x.printStackTrace();
        }

        Namespace ns = module.createNamespace("test");

        Function function = ns.getFunctionManager().create("func");

        function.append(new SetblockCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), module.minecraft.getTypeManager().block.get("bamboo")));

        try {
            module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output"), ModulePackGenerator.OutputType.FOLDER);
        } catch(IOException x) {
            x.printStackTrace();
        }

        System.out.println(function.getResolvedContent());
    }
}
