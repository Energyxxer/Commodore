package com.energyxxer.commodoretest;

import com.energyxxer.commodore.commands.DataMergeCommand;
import com.energyxxer.commodore.commands.TagCommand;
import com.energyxxer.commodore.commands.execute.ExecuteAtEntity;
import com.energyxxer.commodore.commands.execute.ExecuteCommand;
import com.energyxxer.commodore.commands.scoreboard.ScoreAdd;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.entity.GenericEntity;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.functions.FunctionComment;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.nbt.TagCompound;
import com.energyxxer.commodore.nbt.TagList;
import com.energyxxer.commodore.nbt.TagShort;
import com.energyxxer.commodore.nbt.TagString;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.MacroScoreHolder;
import com.energyxxer.commodore.score.Objective;
import com.energyxxer.commodore.selector.*;
import com.energyxxer.commodore.standard.StandardDefinitionPacks;
import com.energyxxer.commodore.types.EntityType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.energyxxer.commodore.selector.Selector.BaseSelector.ALL_ENTITIES;

public class EnhancedHoes {

    private static final int CHANCE_WOOD = 5,
            CHANCE_STONE = 10,
            CHANCE_IRON = 20,
            CHANCE_GOLD = 30,
            CHANCE_DIAMOND = 55;


    public static void main(String[] args) {
        CommandModule module = new CommandModule("Enhanced Hoes", "eh");
        StandardDefinitionPacks.MINECRAFT_J_1_13.initialize(module);

        Objective count = module.getObjectiveManager().create("count");
        Objective tier = module.getObjectiveManager().create("tier");
        Objective crowd = module.getObjectiveManager().create("crowd");

        Namespace minecraft = module.minecraft;
        EntityType itemEntityType = minecraft.getTypeManager().entity.get("item");

        Namespace namespace = module.getNamespace(module.getPrefix());

        Function tick = namespace.getFunctionManager().create("tick");

        HashMap<String, String[]> cropTypes = new HashMap<>();

        cropTypes.put("crop", "wheat,beetroot".split(","));
        cropTypes.put("self_crop", "wheat_seeds,melon_seeds,pumpkin_seeds,beetroot_seeds,carrot,potato,nether_wart".split(","));

        tick.append(new FunctionComment("Add 'crop' and 'self_crop' tags to items"));

        for(Map.Entry<String, String[]> entry : cropTypes.entrySet()) {
            for(String crop : entry.getValue()) {
                TagCompound nbt = new TagCompound(
                        new TagCompound("Item",
                                new TagString("id", "minecraft:" + crop)),
                        new TagShort("Age", 0),
                        new TagShort("PickupDelay", 10));
                Selector sel = new Selector(ALL_ENTITIES, new TypeArgument(itemEntityType), new NBTArgument(nbt));
                tick.append(new TagCommand(TagCommand.Action.ADD, new GenericEntity(sel), entry.getKey()));
            }
        }

        MacroScoreHolder cropsMacro = new MacroScoreHolder("All Crops");
        MacroScoreHolder selfCropsMacro = new MacroScoreHolder("Self Crops");

        Selector allCropsSelector = new Selector(ALL_ENTITIES, new TypeArgument(itemEntityType), new TagArgument("crop"));
        Selector selfCropsSelector = new Selector(ALL_ENTITIES, new TypeArgument(itemEntityType), new TagArgument("self_crop"));

        GenericEntity allCrops = new GenericEntity(allCropsSelector);
        GenericEntity allSelfCrops = new GenericEntity(selfCropsSelector);

        allCrops.addMacroHolder(cropsMacro);
        allSelfCrops.addMacroHolders(cropsMacro, selfCropsMacro);

        tick.append(new TagCommand(TagCommand.Action.ADD, allSelfCrops, "crop"));

        tick.append(new FunctionComment("Prevent item frames from duplicating crops"));
        {
            Entity nearbyCrops = allCrops.clone();
            nearbyCrops.getSelector().addArguments(new DistanceArgument(new SelectorNumberArgument<>(null, 1.0)));

            ExecuteCommand exec = new ExecuteCommand(new TagCommand(TagCommand.Action.REMOVE, nearbyCrops, "crop"));
            exec.addModifier(new ExecuteAtEntity(new GenericEntity(new Selector(ALL_ENTITIES, new TypeArgument(minecraft.getTypeManager().entity.get("item_frame"))))));
            tick.append(exec);
        }

        tick.append(new FunctionComment("Prevent duplication of premature harvests"));
        {
            Entity nearbyCrops = allSelfCrops.clone();
            nearbyCrops.getSelector().addArguments(new DistanceArgument(new SelectorNumberArgument<>(null, 1.0)));
            ExecuteCommand exec = new ExecuteCommand(new ScoreAdd(new LocalScore(crowd, nearbyCrops), 1));
            exec.addModifier(new ExecuteAtEntity(allSelfCrops));
            tick.append(exec);
        }
        {
            ScoreArgument scores = new ScoreArgument();
            scores.put(crowd, new SelectorNumberArgument<>(1));
            Entity loneCrops = allSelfCrops.clone();
            loneCrops.getSelector().addArguments(scores);
            tick.append(new DataMergeCommand(loneCrops, new TagCompound(new TagList("Tags"))));
        }

        module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output"));

        System.out.println(tick.getResolvedContent());
    }
}
