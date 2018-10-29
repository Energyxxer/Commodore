package com.energyxxer.commodoretest;

import com.energyxxer.commodore.commands.data.DataModifyCommand;
import com.energyxxer.commodore.commands.data.ModifySourceFromEntity;
import com.energyxxer.commodore.commands.data.ModifySourceValue;
import com.energyxxer.commodore.commands.setblock.SetblockCommand;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.ModulePackGenerator;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.module.options.UnusedCommandPolicy;
import com.energyxxer.commodore.nbt.TagByte;
import com.energyxxer.commodore.nbt.TagCompound;
import com.energyxxer.commodore.nbt.TagInt;
import com.energyxxer.commodore.nbt.path.*;
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

        NBTPath path = new NBTPath(new NBTPathKey("Items"), new NBTListMatch(new TagCompound(new TagByte("Count", 1))), new NBTPathKey("tag"), new NBTObjectMatch(new TagCompound(new TagByte("LevelUp", 1))), new NBTPathKey("Enchantments"), new NBTListMatch(new TagCompound(new TagInt("lvl", 1))));
        function.append(new DataModifyCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), path, DataModifyCommand.MERGE(), new ModifySourceValue(new TagCompound(new TagInt("lvl", 2)))));
        function.append(new DataModifyCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), path, DataModifyCommand.MERGE(), new ModifySourceFromEntity(function.getSender(), new NBTPath(new NBTPathKey("SelectedItem"), new NBTPathKey("tag"), new NBTPathKey("Enchantments"), new NBTPathIndex(0), new NBTPathKey("lvl")))));

        try {
            module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output"), ModulePackGenerator.OutputType.FOLDER);
        } catch(IOException x) {
            x.printStackTrace();
        }

        System.out.println(function.getResolvedContent());
    }
}
