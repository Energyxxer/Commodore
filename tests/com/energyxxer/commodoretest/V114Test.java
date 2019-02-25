package com.energyxxer.commodoretest;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.commands.clear.ClearCommand;
import com.energyxxer.commodore.functionlogic.commands.data.DataModifyCommand;
import com.energyxxer.commodore.functionlogic.commands.data.ModifySourceFromEntity;
import com.energyxxer.commodore.functionlogic.commands.data.ModifySourceValue;
import com.energyxxer.commodore.functionlogic.commands.loot.*;
import com.energyxxer.commodore.functionlogic.commands.setblock.SetblockCommand;
import com.energyxxer.commodore.functionlogic.commands.tellraw.TellrawCommand;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.nbt.TagByte;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.functionlogic.nbt.TagInt;
import com.energyxxer.commodore.functionlogic.nbt.TagIntArray;
import com.energyxxer.commodore.functionlogic.nbt.path.*;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.loottables.LootEmptyEntry;
import com.energyxxer.commodore.loottables.LootNestedEntry;
import com.energyxxer.commodore.loottables.LootTable;
import com.energyxxer.commodore.loottables.Pool;
import com.energyxxer.commodore.loottables.functions.LootFunction;
import com.energyxxer.commodore.loottables.items.LootItemEntry;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.standard.StandardDefinitionPacks;
import com.energyxxer.commodore.tags.ItemTag;
import com.energyxxer.commodore.textcomponents.*;
import com.energyxxer.commodore.textcomponents.events.InsertionEvent;
import com.energyxxer.commodore.util.attributes.Attribute;

import java.io.File;
import java.io.IOException;

import static com.energyxxer.commodore.functionlogic.selector.Selector.BaseSelector.ALL_PLAYERS;
import static com.energyxxer.commodore.functionlogic.selector.Selector.BaseSelector.NEAREST_PLAYER;
import static com.energyxxer.commodore.functionlogic.selector.Selector.BaseSelector.SENDER;

public class V114Test {
    public static void main(String[] args) {
        CommandModule module = new CommandModule("1.14 Test", "vp");

        try {
            module.importDefinitions(StandardDefinitionPacks.MINECRAFT_JAVA_LATEST_SNAPSHOT);
            System.out.println(module.minecraft.tags.itemTags.getAll());
        } catch(IOException x) {
            x.printStackTrace();
        }

        System.out.println(module.minecraft.tags.functionTags.getAll());

        Namespace ns = module.createNamespace("test");

        Function function = ns.getFunctionManager().create("func", true, new Selector(NEAREST_PLAYER));

        function.append(new SetblockCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), module.minecraft.types.block.get("bamboo")));

        function.append(new ClearCommand(function.getSender(), new Item(module.minecraft.tags.itemTags.get("wool"))));

        NBTPath path = new NBTPath(new NBTPathKey("Items"), new NBTListMatch(new TagCompound(new TagByte("Count", 1))), new NBTPathKey("tag"), new NBTObjectMatch(new TagCompound(new TagByte("LevelUp", 1))), new NBTPathKey("Enchantments"), new NBTListMatch(new TagCompound(new TagInt("lvl", 1))));
        function.append(new DataModifyCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), path, DataModifyCommand.MERGE(), new ModifySourceValue(new TagCompound(new TagInt("lvl", 2)))));
        function.append(new DataModifyCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), path, DataModifyCommand.MERGE(), new ModifySourceFromEntity(function.getSender(), new NBTPath(new NBTPathKey("SelectedItem"), new NBTPathKey("tag"), new NBTPathKey("Enchantments"), new NBTPathIndex(0), new NBTPathKey("lvl")))));

        function.append(new LootCommand(new LootSpawn(new CoordinateSet(0, 64, 0)), new LootFromKill(function.getSender())));
        function.append(new LootCommand(new LootGive(function.getSender()), new LootFromFish("minecraft:entities/wither_skeleton", new CoordinateSet(1000, 62, 32), ToolOrHand.MAINHAND)));
        function.append(new LootCommand(new LootReplaceEntity(function.getSender(), module.minecraft.types.slot.get("weapon.offhand")), new LootFromLoot("minecraft:entities/wither_skeleton")));
        function.append(new LootCommand(new LootReplaceBlock(new CoordinateSet(0, 64, 0), module.minecraft.types.slot.get("container.4")), new LootFromMine(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE))));
        function.append(new LootCommand(new LootInsertBlock(new CoordinateSet(0, 64, 0)), new LootFromMine(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), new ToolOrHand(module.minecraft.types.item.get("bamboo")))));

        TranslateTextComponent txt = new TranslateTextComponent("translation.example");
        txt.addEvent(new InsertionEvent("woo\"o"));
        txt.setStyle(new TextStyle(TextStyle.EMPTY_STYLE).setColor(TextColor.BLUE));
        txt.addExtra(new StringTextComponent("hi"));
        txt.addExtra(new KeybindTextComponent("key.use"));
        txt.addExtra(new NBTTextComponent(new NBTPath(new NBTPathKey("Inventory"), new NBTPathIndex(0)), new Selector(SENDER)));
        txt.addWith(new StringTextComponent("this works now", new TextStyle(TextStyle.EMPTY_STYLE).setColor(TextColor.BLUE)));
        txt.addWith(new StringTextComponent("this works now too", new TextStyle(TextStyle.EMPTY_STYLE).setColor(TextColor.BLUE)));

        function.append(new TellrawCommand(new Selector(ALL_PLAYERS), txt));

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

        ItemTag all = module.minecraft.tags.itemTags.create("all");
        module.minecraft.types.item.list().forEach(all::addValue);

        try {
            module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output" + File.separator + module.getName() + File.separator));
        } catch(IOException x) {
            x.printStackTrace();
        }

        System.out.println(function.getResolvedContent());

        System.out.println(module.minecraft.types.item.list());

        System.out.println(CommandUtils.escape(CommandUtils.parseQuotedString("\"\\uFEA7\"")));
        System.out.println(CommandUtils.escape(CommandUtils.parseQuotedString("\"hello\"")));
        System.out.println(CommandUtils.escape(CommandUtils.parseQuotedString("\"\\u00FE\"")));
        System.out.println(CommandUtils.escape(CommandUtils.parseQuotedString("\"\\n\"")));

        TagCompound root = new TagCompound();
        root.add(new TagIntArray("CookTime", 1, 1, 1, 1));
        root.add(new TagIntArray("CookTimeTotal", 1, 1, 1, 1));


        ListTextComponent lt = new ListTextComponent();
        lt.append(new StringTextComponent("AAA", new TextStyle() {
            {
                this.setMask(ITALIC);
                this.setFlags(ITALIC);
            }
        }));
        lt.append(new StringTextComponent("BBB", new TextStyle() {
            {
                this.setMask(ITALIC);
            }
        }));

        System.out.println(lt.toString());

        System.out.println(root.toHeadlessString());
    }
}
