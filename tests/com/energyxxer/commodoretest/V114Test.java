package com.energyxxer.commodoretest;

import com.energyxxer.commodore.functionlogic.commands.data.DataModifyCommand;
import com.energyxxer.commodore.functionlogic.commands.data.ModifySourceFromEntity;
import com.energyxxer.commodore.functionlogic.commands.data.ModifySourceValue;
import com.energyxxer.commodore.functionlogic.commands.drop.*;
import com.energyxxer.commodore.functionlogic.commands.setblock.SetblockCommand;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.GenericEntity;
import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.nbt.TagByte;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.functionlogic.nbt.TagInt;
import com.energyxxer.commodore.functionlogic.nbt.path.*;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.loottables.LootEmptyEntry;
import com.energyxxer.commodore.loottables.LootNestedEntry;
import com.energyxxer.commodore.loottables.LootTable;
import com.energyxxer.commodore.loottables.Pool;
import com.energyxxer.commodore.loottables.items.LootItemEntry;
import com.energyxxer.commodore.loottables.functions.LootFunction;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.ModulePackGenerator;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.module.options.UnusedCommandPolicy;
import com.energyxxer.commodore.standard.StandardDefinitionPacks;
import com.energyxxer.commodore.util.attributes.Attribute;

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

        Function function = ns.getFunctionManager().create("func", true, new GenericEntity(new Selector(Selector.BaseSelector.NEAREST_PLAYER)));

        function.append(new SetblockCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), module.minecraft.types.block.get("bamboo")));

        NBTPath path = new NBTPath(new NBTPathKey("Items"), new NBTListMatch(new TagCompound(new TagByte("Count", 1))), new NBTPathKey("tag"), new NBTObjectMatch(new TagCompound(new TagByte("LevelUp", 1))), new NBTPathKey("Enchantments"), new NBTListMatch(new TagCompound(new TagInt("lvl", 1))));
        function.append(new DataModifyCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), path, DataModifyCommand.MERGE(), new ModifySourceValue(new TagCompound(new TagInt("lvl", 2)))));
        function.append(new DataModifyCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), path, DataModifyCommand.MERGE(), new ModifySourceFromEntity(function.getSender(), new NBTPath(new NBTPathKey("SelectedItem"), new NBTPathKey("tag"), new NBTPathKey("Enchantments"), new NBTPathIndex(0), new NBTPathKey("lvl")))));

        function.append(new DropCommand(new DropToWorld(new CoordinateSet(0, 64, 0)), new DropFromKill(function.getSender())));
        function.append(new DropCommand(new DropToPlayer(function.getSender()), new DropFromFish("minecraft:entities/wither_skeleton", new CoordinateSet(1000, 62, 32), ToolOrHand.MAINHAND)));
        function.append(new DropCommand(new DropToEntity(function.getSender(), module.minecraft.types.slot.get("weapon.offhand")), new DropFromLoot("minecraft:entities/wither_skeleton")));
        function.append(new DropCommand(new DropToBlock(new CoordinateSet(0, 64, 0), module.minecraft.types.slot.get("container.4"), true), new DropFromMine(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE))));
        function.append(new DropCommand(new DropToBlock(new CoordinateSet(0, 64, 0)), new DropFromMine(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), new ToolOrHand(module.minecraft.types.item.get("bamboo")))));

        LootTable moaDrop = ns.lootTables.create("mobs/moa");
        Pool first = new Pool();
        first.setMaxRolls(3);
        first.setBonusRolls(2);
        moaDrop.addPool(first);
        LootItemEntry feathers = new LootItemEntry(module.minecraft.types.item.get("feather"));
        feathers.functions.add(new LootFunction.SetCount(1, 4));
        feathers.setWeight(3);
        LootItemEntry beef = new LootItemEntry(module.minecraft.types.item.get("beef"));
        beef.functions.add(new LootFunction.FurnaceSmelt());
        first.addEntry(feathers);
        first.addEntry(beef);

        Pool second = new Pool();
        moaDrop.addPool(second);
        LootItemEntry crossbow = new LootItemEntry(module.minecraft.types.item.get("crossbow"));
        crossbow.functions.add(new LootFunction.EnchantRandomly());
        crossbow.functions.add(new LootFunction.SetAttributes(new Attribute("Speedup", Attribute.MOVEMENT_SPEED, Attribute.Operation.ADDITION, 0.1, Attribute.Slot.MAINHAND)));
        crossbow.setQuality(2);
        second.addEntry(crossbow);
        LootNestedEntry button = new LootNestedEntry("minecraft:blocks/acacia_button");
        second.addEntry(button);
        LootEmptyEntry empty = new LootEmptyEntry();
        empty.setWeight(48);
        second.addEntry(empty);

        try {
            module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output"), ModulePackGenerator.OutputType.FOLDER);
        } catch(IOException x) {
            x.printStackTrace();
        }

        System.out.println(function.getResolvedContent());
    }
}
