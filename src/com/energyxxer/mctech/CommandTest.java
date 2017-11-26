package com.energyxxer.mctech;

import com.energyxxer.mctech.block.Block;
import com.energyxxer.mctech.block.Blockstate;
import com.energyxxer.mctech.commands.DataCommand;
import com.energyxxer.mctech.commands.ExecuteCommand;
import com.energyxxer.mctech.commands.SayCommand;
import com.energyxxer.mctech.commands.SetblockCommand;
import com.energyxxer.mctech.commands.TagCommand;
import com.energyxxer.mctech.commands.TellrawCommand;
import com.energyxxer.mctech.commands.execute.ExecuteAsEntity;
import com.energyxxer.mctech.coordinates.Coordinate;
import com.energyxxer.mctech.coordinates.CoordinateSet;
import com.energyxxer.mctech.coordinates.ExecuteAlignment;
import com.energyxxer.mctech.coordinates.ExecuteAtEntity;
import com.energyxxer.mctech.entity.Entity;
import com.energyxxer.mctech.entity.GenericEntity;
import com.energyxxer.mctech.functions.Function;
import com.energyxxer.mctech.nbt.TagByte;
import com.energyxxer.mctech.nbt.TagCompound;
import com.energyxxer.mctech.nbt.TagInt;
import com.energyxxer.mctech.nbt.TagShort;
import com.energyxxer.mctech.nbt.TagString;
import com.energyxxer.mctech.selector.NBTArgument;
import com.energyxxer.mctech.selector.Selector;
import com.energyxxer.mctech.selector.TagArgument;
import com.energyxxer.mctech.selector.TypeArgument;
import com.energyxxer.mctech.textcomponents.ListTextComponent;
import com.energyxxer.mctech.textcomponents.StringTextComponent;
import com.energyxxer.mctech.textcomponents.TextColor;
import com.energyxxer.mctech.textcomponents.TextStyle;
import com.energyxxer.mctech.textcomponents.events.HoverEvent;
import com.energyxxer.mctech.types.BlockType;

public class CommandTest {
    public static void main(String[] args) {
        Function function = new Function("test:function");


        Entity entity0 = new GenericEntity(new Selector(Selector.BaseSelector.ALL_ENTITIES));
        entity0.getSelector().addArguments(new TypeArgument("bat"), new TagArgument("a"), new TagArgument("!b"));
        Entity entity1 = new GenericEntity(new Selector(Selector.BaseSelector.RANDOM_PLAYER));
        entity1.getSelector().addArguments(new NBTArgument(new TagCompound(new TagByte("OnGround",1))));

        //System.out.println("entity0 = " + entity0.getSelector());
        //System.out.println("entity1 = " + entity1.getSelector());

        //System.out.println();

        SayCommand sayHi = new SayCommand("hi");
        Block block = new Block(new BlockType("furnace"), Blockstate.parseRaw("facing=west"), new TagCompound(new TagShort("BurnTime", (short) 10)));
        SetblockCommand setblock = new SetblockCommand(new CoordinateSet(0, 3, 0, Coordinate.Type.RELATIVE), block);
        ExecuteCommand exec = new ExecuteCommand(setblock);
        exec.addModifier(new ExecuteAsEntity(entity0));
        exec.addModifier(new ExecuteAtEntity(entity0));
        exec.addModifier(new ExecuteAlignment(true, false, true));
        exec.addModifier(new CoordinateSet(0.5, 0, 0.5, Coordinate.Type.RELATIVE));
        function.append(exec);
        //System.out.println("exec = " + exec.getRawCommand());

        //System.out.println();

        TextStyle style0 = new TextStyle(TextColor.BLUE, TextStyle.BOLD + TextStyle.STRIKETHROUGH + TextStyle.OBFUSCATED);
        TextStyle style1 = new TextStyle(TextStyle.ITALIC + TextStyle.STRIKETHROUGH + TextStyle.UNDERLINE);

        //System.out.println("style0 = " + style0);
        //System.out.println("style1 = " + style1);

        //System.out.println("style1 (masked to style0) = " + style1.toString(style0));

        ListTextComponent ltc = new ListTextComponent();

        StringTextComponent tc = new StringTextComponent("Hello World!");
        tc.addEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new StringTextComponent("why", style1)));
        tc.setStyle(style0);
        ltc.append(tc);

        StringTextComponent tc2 = new StringTextComponent(" Salutations Universe!");
        tc2.setStyle(style0);
        ltc.append(tc2);
        function.append(new TellrawCommand(new GenericEntity(new Selector(Selector.BaseSelector.ALL_PLAYERS)), ltc));
        //System.out.println("ltc = " + ltc);

        Block cb = new Block(
                new BlockType("command_block"),
                Blockstate.parseRaw("facing=down,powered=true"),
                new TagCompound(new TagString("Command","say hi"), new TagInt("SuccessCount",2))
                );
        //System.out.println("cb = " + cb);

        TagCommand tagcmd = new TagCommand(TagCommand.Action.ADD, entity1, "on_ground");
        function.append(tagcmd);
        //System.out.println("tagcmd = " + tagcmd.getRawCommand());

        DataCommand datacmd = new DataCommand(entity0, "BatFlags", 1);
        function.append(datacmd);
        //System.out.println("datacmd = " + datacmd.getRawCommand());

        System.out.println(function.getContent());
    }
}
