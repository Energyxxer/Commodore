package com.energyxxer.commodoretest;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.commands.clear.ClearCommand;
import com.energyxxer.commodore.functionlogic.commands.data.DataModifyCommand;
import com.energyxxer.commodore.functionlogic.commands.data.ModifySourceFromEntity;
import com.energyxxer.commodore.functionlogic.commands.data.ModifySourceValue;
import com.energyxxer.commodore.functionlogic.commands.kill.KillCommand;
import com.energyxxer.commodore.functionlogic.commands.loot.*;
import com.energyxxer.commodore.functionlogic.commands.scoreboard.ScoreSet;
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
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.PlayerName;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.functionlogic.selector.arguments.TypeArgument;
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
import com.energyxxer.commodore.util.DoubleRange;
import com.energyxxer.commodore.util.IntegerRange;
import com.energyxxer.commodore.util.attributes.Attribute;
import com.energyxxer.commodore.versioning.JavaEditionVersion;

import java.io.File;
import java.io.IOException;

import static com.energyxxer.commodore.functionlogic.selector.Selector.BaseSelector.*;

public class V114Test {
    public static void main(String[] args) {
        CommandModule module = new CommandModule("1.14 Test", "vp");
        module.setSettingsActive();
        module.getSettingsManager().setTargetVersion(new JavaEditionVersion(1, 14, 0));
        //*/module.getSettingsManager().setTargetVersion(new BedrockEditionVersion(1, 11, 0));

        try {
            module.importDefinitions(StandardDefinitionPacks.MINECRAFT_JAVA_LATEST_SNAPSHOT);
            System.out.println(module.minecraft.tags.itemTags.getAll());
        } catch(IOException x) {
            x.printStackTrace();
        }

        //module.getObjectiveManager().create("air", "air");

        System.out.println(module.minecraft.tags.functionTags.getAll());

        Namespace ns = module.getNamespace("test");

        Function function = ns.getFunctionManager().create("func", true, new Selector(NEAREST_PLAYER));

        function.append(new SetblockCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), module.minecraft.types.block.get("bamboo")));

        function.append(new ClearCommand(new Selector(SENDER), new Item(module.minecraft.tags.itemTags.get("wool"))));

        NBTPath path = new NBTPath(new NBTPathKey("Items"), new NBTListMatch(new TagCompound(new TagByte("Count", 1))), new NBTPathKey("tag"), new NBTObjectMatch(new TagCompound(new TagByte("LevelUp", 1))), new NBTPathKey("Enchantments"), new NBTListMatch(new TagCompound(new TagInt("lvl", 1))));
        function.append(new DataModifyCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), path, DataModifyCommand.MERGE(), new ModifySourceValue(new TagCompound(new TagInt("lvl", 2)))));
        function.append(new DataModifyCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), path, DataModifyCommand.MERGE(), new ModifySourceFromEntity(new Selector(SENDER), new NBTPath(new NBTPathKey("SelectedItem"), new NBTPathKey("tag"), new NBTPathKey("Enchantments"), new NBTPathIndex(0), new NBTPathKey("lvl")))));

        function.append(new LootCommand(new LootSpawn(new CoordinateSet(0, 64, 0)), new LootFromKill(new Selector(SENDER))));
        function.append(new LootCommand(new LootGive(new Selector(SENDER)), new LootFromFish("minecraft:entities/wither_skeleton", new CoordinateSet(1000, 62, 32), ToolOrHand.MAINHAND)));
        function.append(new LootCommand(new LootReplaceEntity(new Selector(SENDER), module.minecraft.types.slot.get("weapon.offhand")), new LootFromLoot("minecraft:entities/wither_skeleton")));
        function.append(new LootCommand(new LootReplaceBlock(new CoordinateSet(0, 64, 0), module.minecraft.types.slot.get("container.4")), new LootFromMine(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE))));
        function.append(new LootCommand(new LootInsertBlock(new CoordinateSet(0, 64, 0)), new LootFromMine(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), new ToolOrHand(module.minecraft.types.item.get("bamboo")))));


        function.append(new ScoreSet(new LocalScore(null, module.getObjectiveManager().create("obj")), 1));
        function.append(new KillCommand(new PlayerName("a b")));
        function.append(new KillCommand(new PlayerName("a=b")));
        function.append(new ScoreSet(new LocalScore(new PlayerName("a=b"), module.getObjectiveManager().get("obj")), 1));
        System.out.println(module.minecraft.tags);
        function.append(new KillCommand(new Selector(ALL_ENTITIES, new TypeArgument(module.minecraft.tags.entityTypeTags.get("skeletons")))));
        //function.append(new KillCommand(new Selector(ALL_ENTITIES, new PredicateArgument(new PredicateReference(module.getNamespace("iet"), "fire")))));
        //function.append(new ScoreSet(new LocalScore(new PlayerName("a b"), module.getObjectiveManager().get("obj")), 1));


        //function.append(new SetblockCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), new Block(module.minecraft.types.block.get("chest"), new TagCompound(new TagString("Lock", "bruh moment")))));

        TranslateTextComponent txt = new TranslateTextComponent("translation.example");
        txt.addEvent(new InsertionEvent("woo\"o"));
        txt.setStyle(new TextStyle(TextStyle.EMPTY_STYLE).setColor(TextColor.BLUE));
        txt.addExtra(new StringTextComponent("hi"));
        txt.addExtra(new KeybindTextComponent("key.use"));
        txt.addExtra(new NBTTextComponent(new NBTPath(new NBTPathKey("Inventory"), new NBTPathIndex(0)), new Selector(SENDER)));
        txt.addWith(new StringTextComponent("this works now", new TextStyle(TextStyle.EMPTY_STYLE).setColor(TextColor.BLUE)));
        txt.addWith(new StringTextComponent("this works now too", new TextStyle(TextStyle.EMPTY_STYLE).setColor(TextColor.BLUE)));

        function.append(new TellrawCommand(new Selector(ALL_ENTITIES, new TypeArgument(module.minecraft.types.entity.get("player"))), txt));

        //function.append(new BanCommand(new Selector(SENDER)));

        //function.append(new SetblockCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), new DVBlock(module.minecraft.types.block.get("stone"), 1)));

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

        module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output" + File.separator + module.getName() + File.separator));

        System.out.println(function.getResolvedContent());

        System.out.println(module.minecraft.types.item.list());

        System.out.println(CommandUtils.escape(CommandUtils.parseQuotedString("\"\\uFEA7\"")));
        System.out.println(CommandUtils.escape(CommandUtils.parseQuotedString("\"hello\"")));
        System.out.println(CommandUtils.escape(CommandUtils.parseQuotedString("\"\\u00FE\"")));
        System.out.println(CommandUtils.escape(CommandUtils.parseQuotedString("\"\\n\"")));

        TagCompound root = new TagCompound();
        root.add(new TagIntArray("CookTime", 1, 1, 1, 1));
        root.add(new TagIntArray("CookTimeTotal", 1, 1, 1, 1));

        System.out.println(new IntegerRange(null, null));
        System.out.println(new IntegerRange(0, 100));
        System.out.println(new IntegerRange(null, 100));

        System.out.println(new DoubleRange(null, null));
        System.out.println(new DoubleRange(0.0, 100.0));
        System.out.println(new DoubleRange(null, 100.0));

        System.out.println(Double.NEGATIVE_INFINITY);

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

        System.out.println(module.getResource("sounds.json"));
    }
}
