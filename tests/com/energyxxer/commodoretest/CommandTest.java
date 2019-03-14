package com.energyxxer.commodoretest;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.functionlogic.commands.CommandGroup;
import com.energyxxer.commodore.functionlogic.commands.advancement.AdvancementCommand;
import com.energyxxer.commodore.functionlogic.commands.bossbar.BossbarAddCommand;
import com.energyxxer.commodore.functionlogic.commands.bossbar.BossbarCommand;
import com.energyxxer.commodore.functionlogic.commands.bossbar.set.BossbarSetColorCommand;
import com.energyxxer.commodore.functionlogic.commands.bossbar.set.BossbarSetPlayersCommand;
import com.energyxxer.commodore.functionlogic.commands.bossbar.set.BossbarSetStyleCommand;
import com.energyxxer.commodore.functionlogic.commands.clone.CloneCommand;
import com.energyxxer.commodore.functionlogic.commands.clone.CloneFilteredCommand;
import com.energyxxer.commodore.functionlogic.commands.clone.CloneMaskedCommand;
import com.energyxxer.commodore.functionlogic.commands.effect.EffectClearCommand;
import com.energyxxer.commodore.functionlogic.commands.effect.EffectGiveCommand;
import com.energyxxer.commodore.functionlogic.commands.enchant.EnchantCommand;
import com.energyxxer.commodore.functionlogic.commands.execute.*;
import com.energyxxer.commodore.functionlogic.commands.experience.ExperienceCommand;
import com.energyxxer.commodore.functionlogic.commands.experience.ExperienceSetCommand;
import com.energyxxer.commodore.functionlogic.commands.fill.*;
import com.energyxxer.commodore.functionlogic.commands.function.FunctionCommand;
import com.energyxxer.commodore.functionlogic.commands.gamemode.GamemodeCommand;
import com.energyxxer.commodore.functionlogic.commands.give.GiveCommand;
import com.energyxxer.commodore.functionlogic.commands.locate.LocateCommand;
import com.energyxxer.commodore.functionlogic.commands.particle.ParticleCommand;
import com.energyxxer.commodore.functionlogic.commands.playsound.PlaySoundCommand;
import com.energyxxer.commodore.functionlogic.commands.recipe.RecipeCommand;
import com.energyxxer.commodore.functionlogic.commands.say.SayCommand;
import com.energyxxer.commodore.functionlogic.commands.scoreboard.*;
import com.energyxxer.commodore.functionlogic.commands.spreadplayers.SpreadPlayersCommand;
import com.energyxxer.commodore.functionlogic.commands.stopsound.StopSoundCommand;
import com.energyxxer.commodore.functionlogic.commands.summon.SummonCommand;
import com.energyxxer.commodore.functionlogic.commands.team.TeamCreateCommand;
import com.energyxxer.commodore.functionlogic.commands.team.TeamModifyCommand;
import com.energyxxer.commodore.functionlogic.commands.teleport.TeleportCommand;
import com.energyxxer.commodore.functionlogic.commands.teleport.destination.TeleportDestination;
import com.energyxxer.commodore.functionlogic.commands.teleport.facing.TeleportFacing;
import com.energyxxer.commodore.functionlogic.commands.tellraw.TellrawCommand;
import com.energyxxer.commodore.functionlogic.commands.time.TimeQueryCommand;
import com.energyxxer.commodore.functionlogic.commands.trigger.TriggerCommand;
import com.energyxxer.commodore.functionlogic.commands.weather.WeatherCommand;
import com.energyxxer.commodore.functionlogic.commands.worldborder.WorldBorderSetCenter;
import com.energyxxer.commodore.functionlogic.commands.worldborder.WorldBorderSetDamageAmount;
import com.energyxxer.commodore.functionlogic.commands.worldborder.WorldBorderSetDistance;
import com.energyxxer.commodore.functionlogic.commands.worldborder.WorldBorderSetWarningDistance;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.functions.FunctionComment;
import com.energyxxer.commodore.functionlogic.functions.FunctionHeaderComment;
import com.energyxxer.commodore.functionlogic.nbt.NumericNBTType;
import com.energyxxer.commodore.functionlogic.nbt.TagByte;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.functionlogic.nbt.TagShort;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import com.energyxxer.commodore.functionlogic.rotation.Rotation;
import com.energyxxer.commodore.functionlogic.rotation.RotationUnit;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.functionlogic.score.ObjectiveManager;
import com.energyxxer.commodore.functionlogic.score.PlayerName;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.functionlogic.selector.arguments.*;
import com.energyxxer.commodore.functionlogic.selector.arguments.advancement.AdvancementCompletionEntry;
import com.energyxxer.commodore.functionlogic.selector.arguments.advancement.AdvancementCriterionEntry;
import com.energyxxer.commodore.functionlogic.selector.arguments.advancement.AdvancementCriterionGroupEntry;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.standard.StandardDefinitionPacks;
import com.energyxxer.commodore.tags.BlockTag;
import com.energyxxer.commodore.tags.FunctionTag;
import com.energyxxer.commodore.textcomponents.ScoreTextComponent;
import com.energyxxer.commodore.textcomponents.StringTextComponent;
import com.energyxxer.commodore.textcomponents.TextColor;
import com.energyxxer.commodore.textcomponents.TextStyle;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.BossbarReference;
import com.energyxxer.commodore.types.defaults.FunctionReference;
import com.energyxxer.commodore.types.defaults.TeamReference;
import com.energyxxer.commodore.util.*;

import java.io.File;
import java.io.IOException;

import static com.energyxxer.commodore.functionlogic.commands.playsound.PlaySoundCommand.Source.MASTER;
import static com.energyxxer.commodore.functionlogic.commands.recipe.RecipeCommand.Action.TAKE;
import static com.energyxxer.commodore.functionlogic.selector.Selector.BaseSelector.*;

public class CommandTest {
    public static void main(String[] args) {
        /*
        Function function = new Function("test:function");

        ObjectiveManager objMgr = new ObjectiveManager();

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

        //System.out.println("style1 (masked to style0) = " + style1.numberToPlainString(style0));

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
        //System.out.println("datacmd = " + datacmd.getRawCommand());*/

        //DefinitionPack aetherPack = new DefinitionPack("Aether II Test Definition Pack", "aether_temp");


        CommandModule module = new CommandModule("Commodore Test", "A simple Commodore test project", "ct");
        try {
            StandardDefinitionPacks.MINECRAFT_JAVA_LATEST_RELEASE.populate(module);
        } catch(IOException x) {
            x.printStackTrace();
        }
        ObjectiveManager objMgr = module.getObjectiveManager();
        objMgr.setPrefixEnabled(true);
        objMgr.setCreationFunction(module.getNamespace("ct").getFunctionManager().create("init_objectives"));
        objMgr.create("return", true);

        Function function = module.getNamespace("test").getFunctionManager().create("scores");

        Type bat = module.minecraft.getTypeManager().entity.get("bat");

        Selector entity = new Selector(ALL_ENTITIES);
        entity.addArguments(new TypeArgument(bat), new TagArgument("a"), new TagArgument("!b"));

        Selector playerSelector = new Selector(Selector.BaseSelector.ALL_PLAYERS);
        AdvancementArgument advArg = new AdvancementArgument();
        advArg.addEntry(new AdvancementCompletionEntry("foo", true));
        advArg.addEntry(new AdvancementCompletionEntry("bar", false));
        AdvancementCriterionGroupEntry criterionGroup = new AdvancementCriterionGroupEntry("custom:something");
        criterionGroup.addCriterion(new AdvancementCriterionEntry("criterion", true));
        advArg.addEntry(criterionGroup);
        playerSelector.addArguments(advArg);

        Selector player = playerSelector;

        function.append(new AdvancementCommand(AdvancementCommand.Action.GRANT, player, AdvancementCommand.Limit.ONLY, "bar"));

        function.append(new ExperienceSetCommand(player, 5, ExperienceCommand.Unit.LEVELS));

        Type diamondSword = module.getNamespace("minecraft").getTypeManager().item.get("diamond_sword");

        function.append(new GiveCommand(player, new Item(diamondSword, new TagCompound(new TagByte("Unbreakable", 1), new TagShort("Damage", 4))), 3));

        //function.append(new SummonCommand(module.minecraft.getTypeManager().entity.get("player")));

        function.append(new PlaySoundCommand("minecraft:ambient.cave", PlaySoundCommand.Source.MASTER, player));
        function.append(new PlaySoundCommand("minecraft:ambient.cave", PlaySoundCommand.Source.MASTER, player, new CoordinateSet(500, 87, 500)));
        function.append(new PlaySoundCommand("minecraft:ambient.cave", PlaySoundCommand.Source.MASTER, player, new CoordinateSet(500, 87, 500), 5));
        function.append(new PlaySoundCommand("minecraft:ambient.cave", PlaySoundCommand.Source.MASTER, player, new CoordinateSet(500, 87, 500), 5, 0));
        function.append(new PlaySoundCommand("minecraft:ambient.cave", PlaySoundCommand.Source.MASTER, player, new CoordinateSet(500, 87, 500), 5, 0, 1));

        function.append(new RecipeCommand(TAKE, new Selector(ALL_PLAYERS), "minecraft:banner_duplicate"));

        function.append(new SpreadPlayersCommand(player, new CoordinateSet(5, 0, 5), 3.2, 53.2, false));

        function.append(new StopSoundCommand(player, PlaySoundCommand.Source.MASTER, "minecraft:ambient.cave"));
        function.append(new StopSoundCommand(player, PlaySoundCommand.Source.MASTER));
        function.append(new StopSoundCommand(player));
        function.append(new StopSoundCommand(player, null, "minecraft:ambient.cave"));

        function.append(new SummonCommand(bat, new CoordinateSet(0, 0, 5, Coordinate.Type.LOCAL), new TagCompound(new TagByte("Glowing", 1))));

        function.append(new TeleportCommand(new PlayerName("Energyxxer"), TeleportDestination.create(new CoordinateSet(0, 0, 2, Coordinate.Type.LOCAL)), TeleportFacing.create(new Rotation(12.5, 0, RotationUnit.Type.RELATIVE))));
        Selector singlePlayerSelector = player.clone();
        singlePlayerSelector.addArguments(new LimitArgument(1));
        Entity singlePlayer = singlePlayerSelector;
        function.append(new TeleportCommand(entity, TeleportDestination.create(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE)), TeleportFacing.create(singlePlayer)));

        Objective t = module.getObjectiveManager().create("t", "trigger");

        function.append(new ExecuteCommand(new TriggerCommand(t)));
        function.append(new ExecuteCommand(new TriggerCommand(t, TriggerCommand.Action.ADD, 5)));
        function.append(new ExecuteCommand(new TriggerCommand(t, TriggerCommand.Action.SET, 1)));
        function.append(new ExecuteCommand(new TriggerCommand(t, TriggerCommand.Action.ADD, 1)));

        function.append(new WeatherCommand(WeatherCommand.Mode.RAIN, 10));
        function.append(new WeatherCommand(WeatherCommand.Mode.CLEAR, 300));

        function.append(new FunctionHeaderComment("SCOREBOARD ACCESS OPTIMIZATION"));

        LocalScore a = new LocalScore(objMgr.get("A"), entity);
        LocalScore b = new LocalScore(objMgr.get("B"), entity);

        function.append(new ScoreSet(a, 5));
        function.append(new ScorePlayersOperation(b, ScorePlayersOperation.Operation.ASSIGN, a));
        function.append(new ScoreAdd(b, 3));
        function.append(new ScoreReset(entity));
        function.append(new ScoreGet(b));

        function.append(new TellrawCommand(player, new ScoreTextComponent(b)));

        ExecuteCommand exec = new ExecuteCommand(new TimeQueryCommand(TimeQueryCommand.TimeCounter.DAY));
        exec.addModifier(new ExecuteStoreScore(a));
        exec.addModifier(new ExecuteStoreEntity(singlePlayer, new NBTPath("Position",new NBTPath(1)), NumericNBTType.DOUBLE));
        exec.addModifier(new ExecuteStoreBlock(new CoordinateSet(0, 3, 0), new NBTPath("Position",new NBTPath(1)), NumericNBTType.DOUBLE));
        exec.addModifier(new ExecuteConditionDataEntity(ExecuteCondition.ConditionType.IF, entity, new NBTPath("Silent")));

        function.append(exec);

        function.append(new FunctionComment("CLONE COMMANDS"));

        BlockTag buttons = module.minecraft.getTagManager().blockTags.create("buttons");
        buttons.addValue(module.minecraft.getTypeManager().block.get("stone_button"));
        buttons.addValue(module.minecraft.getTypeManager().block.get("oak_button"));
        buttons.addValue(module.minecraft.getTypeManager().block.get("spruce_button"));
        buttons.addValue(module.minecraft.getTypeManager().block.get("birch_button"));
        buttons.addValue(module.minecraft.getTypeManager().block.get("jungle_button"));
        buttons.addValue(module.minecraft.getTypeManager().block.get("acacia_button"));
        buttons.addValue(module.minecraft.getTypeManager().block.get("dark_oak_button"));

        buttons.setExport(false);

        function.append(new CloneCommand(new CoordinateSet(0, 0, 0, Coordinate.Type.RELATIVE), new CoordinateSet(2, 2, 2, Coordinate.Type.RELATIVE), new CoordinateSet(5, 5, 5)));
        function.append(new CloneCommand(new CoordinateSet(0.5, 0.5, 0.5), new CoordinateSet(2.5, 2.5, 2.5, Coordinate.Type.RELATIVE), new CoordinateSet(5, 5, 5), CloneCommand.SourceMode.FORCE));
        function.append(new CloneMaskedCommand(new CoordinateSet(0, 0, 0), new CoordinateSet(2, 2, 2), new CoordinateSet(5, 5, 5)));
        function.append(new CloneMaskedCommand(new CoordinateSet(0, 0, 0), new CoordinateSet(2, 2, 2), new CoordinateSet(5, 5, 5), CloneCommand.SourceMode.FORCE));
        function.append(new CloneFilteredCommand(new CoordinateSet(0, 0, 0), new CoordinateSet(2, 2, 2), new CoordinateSet(5, 5, 5), new Block(buttons)));
        function.append(new CloneFilteredCommand(new CoordinateSet(0, 0, 0), new CoordinateSet(2, 2, 2), new CoordinateSet(5, 5, 5), new Block(buttons), CloneCommand.SourceMode.FORCE));

        function.append(new FunctionComment("FILL COMMANDS"));

        CoordinateSet pos1 = new CoordinateSet(23, 40, -934);
        CoordinateSet pos2 = new CoordinateSet(49, 49, -920);

        function.append(new FillCommand(pos1, pos2, new Block(module.minecraft.getTypeManager().block.get("command_block"))));
        function.append(new FillCommand(pos1, pos2, new Block(module.minecraft.getTypeManager().block.get("command_block"))));
        function.append(new FillCommand(pos1, pos2, new Block(module.minecraft.getTypeManager().block.get("air")), new FillDestroyMode()));
        function.append(new FillCommand(pos1, pos2, new Block(module.minecraft.getTypeManager().block.get("white_concrete")), new FillOutlineMode()));
        function.append(new FillCommand(pos1, pos2, new Block(module.minecraft.getTypeManager().block.get("blue_concrete")), new FillHollowMode()));
        function.append(new FillCommand(pos1, pos2, new Block(module.minecraft.getTypeManager().block.get("fire")), new FillKeepMode()));
        function.append(new FillCommand(pos1, pos2, new Block(module.minecraft.getTypeManager().block.get("spruce_planks")), new FillReplaceMode(new Block(module.minecraft.getTypeManager().block.get("oak_planks")))));

        function.append(new FunctionComment("OTHERS"));

        function.append(new GamemodeCommand(module.minecraft.getTypeManager().gamemode.get("spectator"), new Selector(ALL_PLAYERS)));
        function.append(new EffectGiveCommand(new Selector(ALL_PLAYERS), new StatusEffect(module.minecraft.getTypeManager().effect.get("resistance"), 100, 4)));
        function.append(new EffectClearCommand(new Selector(ALL_PLAYERS), module.minecraft.getTypeManager().effect.get("resistance")));
        function.append(new EffectClearCommand(new Selector(ALL_PLAYERS)));

        Function otherFunction = module.getNamespace("test").getFunctionManager().create("some_other_function");
        otherFunction.append(new PlaySoundCommand("minecraft:block.note.harp", MASTER, new Selector(ALL_PLAYERS), new CoordinateSet(0, 0, 0), 1, 1, 1));
        otherFunction.append(new ScoreSet(new LocalScore(objMgr.get("return"), otherFunction.getSender()), 1));
        function.append(new FunctionCommand(otherFunction));
        function.append(new ScoreGet(new LocalScore(objMgr.get("return"), function.getSender())));

        {
            Entity entity1 = new Selector(ALL_PLAYERS);

            //otherFunction.append(new ScoreSet(new LocalScore(t, entity1), 4));

            ScoreArgument scoreArg = new ScoreArgument();
            scoreArg.put(t, new IntegerRange(1, 5));
            Entity entity2 = new Selector(ALL_PLAYERS, scoreArg);

            ExecuteCommand exec1 = new ExecuteCommand(new ParticleCommand(new Particle(module.minecraft.getTypeManager().particle.get("bubble")), new CoordinateSet(0,0,0, Coordinate.Type.RELATIVE), new Delta(0,0,0), 0, 100, true, entity1));
            exec1.addModifier(new ExecuteInDimension(module.minecraft.getTypeManager().dimension.get("the_end")));

            //otherFunction.append(exec1);
        }

        FunctionTag tick = module.minecraft.getTagManager().functionTags.create("tick");
        tick.addValue(new FunctionReference(otherFunction));

        function.append(new FunctionHeaderComment(new String(buttons.getContents()).split("\n")));

        otherFunction.append(new ParticleCommand(new Particle(module.minecraft.getTypeManager().particle.get("crit"))));
        otherFunction.append(new ParticleCommand(new Particle(module.minecraft.getTypeManager().particle.get("crit")), new CoordinateSet(0, 0, 5, Coordinate.Type.LOCAL)));
        otherFunction.append(new ParticleCommand(new Particle(module.minecraft.getTypeManager().particle.get("crit")), new CoordinateSet(0, 0, 5, Coordinate.Type.LOCAL), new Delta(2, 2, 2)));
        otherFunction.append(new ParticleCommand(new Particle(module.minecraft.getTypeManager().particle.get("crit")), new CoordinateSet(0, 0, 5, Coordinate.Type.LOCAL), new Delta(2, 2, 2), 0, 10));
        otherFunction.append(new ParticleCommand(new Particle(module.minecraft.getTypeManager().particle.get("crit")), new CoordinateSet(0, 0, 5, Coordinate.Type.LOCAL), new Delta(2, 2, 2), 0, 10, false));
        otherFunction.append(new ParticleCommand(new Particle(module.minecraft.getTypeManager().particle.get("crit")), new CoordinateSet(0, 0, 5, Coordinate.Type.LOCAL), new Delta(2, 2, 2), 0, 10, true));
        otherFunction.append(new ParticleCommand(new Particle(module.minecraft.getTypeManager().particle.get("crit")), new CoordinateSet(0, 0, 5, Coordinate.Type.LOCAL), new Delta(2, 2, 2), 0, 10, false, player));
        //otherFunction.append(new ParticleCommand(new Particle(module.minecraft.getTypeManager().particle.get("crit")), new CoordinateSet(0, 0, 5, Coordinate.Type.LOCAL), new Delta(2, 2, 2), 0, 10, false, entity));

        BossbarReference bb = new BossbarReference(module.minecraft, "stamina");

        otherFunction.append(new BossbarAddCommand(bb, new StringTextComponent("Stamina", new TextStyle(TextColor.WHITE))));
        otherFunction.append(new BossbarSetColorCommand(bb, BossbarCommand.BossbarColor.BLUE));
        otherFunction.append(new BossbarSetStyleCommand(bb, BossbarCommand.BossbarStyle.NOTCHED_6));
        otherFunction.append(new BossbarSetPlayersCommand(bb, player));

        TeamReference blu = new TeamReference("blu");

        otherFunction.append(new TeamCreateCommand(blu, "Blue Team"));
        otherFunction.append(new TeamModifyCommand(blu, TeamModifyCommand.TeamModifyKey.DEATH_MESSAGE_VISIBILITY, TeamModifyCommand.AppliesTo.OTHER_TEAMS));
        otherFunction.append(new TeamModifyCommand(blu, TeamModifyCommand.TeamModifyKey.COLLISION_RULE, TeamModifyCommand.AppliesTo.OTHER_TEAMS));
        otherFunction.append(new TeamModifyCommand(blu, TeamModifyCommand.TeamModifyKey.FRIENDLY_FIRE, true));
        otherFunction.append(new TeamModifyCommand(blu, TeamModifyCommand.TeamModifyKey.COLOR, TextColor.GOLD));

        otherFunction.append(new LocateCommand(module.minecraft.getTypeManager().structure.get("Village")));

        otherFunction.append(new EnchantCommand(new Selector(ALL_PLAYERS), module.minecraft.getTypeManager().enchantment.get("looting"), 3));

        otherFunction.append(new WorldBorderSetCenter(new CoordinateSet(-2000, 0, 70)));
        otherFunction.append(new WorldBorderSetDistance(200));
        otherFunction.append(new WorldBorderSetDistance(100, 10));
        otherFunction.append(new WorldBorderSetWarningDistance(8));
        otherFunction.append(new WorldBorderSetDamageAmount(2));

        Function scoreTest = module.getNamespace("ct").getFunctionManager().create("score-test");

        Objective obj = module.getObjectiveManager().create("obj", true);

        Entity entityA = new Selector(SENDER);

        Entity fakePlayer = new PlayerName("TEMPEST");

        scoreTest.append(new ScoreSet(new LocalScore(obj, entityA), 5));
        scoreTest.append(new ScoreSet(new LocalScore(obj, fakePlayer), 9));

        CommandGroup cg = new CommandGroup(scoreTest);
        cg.append(new SayCommand("Hello"));
        cg.append(new FunctionComment("Comment"));
        cg.append(new SayCommand("World"));

        ExecuteCommand exec2 = new ExecuteCommand(cg);
        exec2.addModifier(new ExecuteInDimension(module.minecraft.getTypeManager().dimension.get("the_nether")));

        scoreTest.append(exec2);

        scoreTest.append(new ObjectiveModifyCommand(objMgr.get("A"), ObjectiveModifyCommand.ObjectiveModifyKey.RENDER_TYPE, ObjectiveModifyCommand.ObjectiveRenderType.HEARTS));
        scoreTest.append(new ObjectiveModifyCommand(objMgr.get("A"), ObjectiveModifyCommand.ObjectiveModifyKey.DISPLAY_NAME, new StringTextComponent("hello", new TextStyle(TextColor.DARK_AQUA, TextStyle.BOLD + TextStyle.UNDERLINE))));

        TeamReference team = new TeamReference("blu");

        scoreTest.append(new TeamCreateCommand(team));
        scoreTest.append(new TeamModifyCommand(team, TeamModifyCommand.TeamModifyKey.COLLISION_RULE, TeamModifyCommand.AppliesTo.OTHER_TEAMS));
        scoreTest.append(new TeamModifyCommand(team, TeamModifyCommand.TeamModifyKey.DISPLAY_NAME, new StringTextComponent("Blue", new TextStyle(TextColor.RED, TextStyle.BOLD))));

        //scoreTest.setExport(false);
        //otherFunction.setExport(false);

        try {
            module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output" + File.separator + module.getName() + File.separator));
        } catch(IOException x) {
            x.printStackTrace();
        }

        System.out.println("module.minecraft.getTagManager().getBlockGroup() = " + module.minecraft.getTagManager().blockTags);
        System.out.println(module.minecraft.getTagManager().blockTags.get("coral_blocks").getValues());
        System.out.println(((BlockTag) module.minecraft.getTagManager().blockTags.get("coral_blocks").getValues().toArray(new Type[0])[0]).getValues());
        System.out.println(module.minecraft.getTagManager().blockTags.get("dead_coral_blocks").getValues());

        //System.out.println(function.getResolvedContent());

        //System.out.println(otherFunction.getResolvedContent());

        System.out.println(function.getResolvedContent());

        System.out.println(module.getNamespace("ct").getFunctionManager().get("init_objectives").getResolvedContent());

        System.out.println(module.minecraft.getTypeManager().biome.get("beach"));

        //System.out.println(module.getNamespace("aether").getTypeManager().getDictionary("dungeon").get("ancient_vaults"));

        //System.out.println(function.getAccessLog());
        //System.out.println(otherFunction.getAccessLog());

        /*System.out.println(Selector.parse("@s[name=\"something\",distance=..0.0001,tag=!try_ext_pwr,tag=!try_ext_ret]").toVerboseString());
        System.out.println(Selector.parse("@e[distance=..10,limit=1,tag=!HsR_TeslaTower,type=!armor_stand]").toVerboseString());
        System.out.println(Selector.parse("@e[type=!cow,x_rotation=5..10,tag=try_ext_thread,limit=1]").toVerboseString());
        System.out.println(Selector.parse("@a[x=-5,y=0,z=-5,dx=10,dy=255,dz=10]").toVerboseString());
        System.out.println(Selector.parse("@e[type=area_effect_cloud,tag=try_ext_base,sort=nearest]").toVerboseString());
        System.out.println(Selector.parse("@s[y=-32,dy=32]").toVerboseString());
        System.out.println(Selector.parse("@e[type=area_effect_cloud,limit=1,sort=nearest]").toVerboseString());
        System.out.println(Selector.parse("@e[limit=1,type=slime,sort=random]").toVerboseString());
        System.out.println(Selector.parse("@e[tag=temp,limit=1]").toVerboseString());
        System.out.println(Selector.parse("@r[sort=furthest]").toVerboseString());
        System.out.println(Selector.parse("@p").toVerboseString());
        System.out.println(Selector.parse("@a").toVerboseString());
        System.out.println(Selector.parse("@r").toVerboseString());
        System.out.println(Selector.parse("@e").toVerboseString());
        System.out.println(Selector.parse("@s").toVerboseString());*/
    }
}
