package com.energyxxer.commodoretest;

import com.energyxxer.commodore.functionlogic.commands.data.DataMergeCommand;
import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteAtEntity;
import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteCommand;
import com.energyxxer.commodore.functionlogic.commands.scoreboard.ScoreAdd;
import com.energyxxer.commodore.functionlogic.commands.tag.TagCommand;
import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.functions.FunctionComment;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.functionlogic.nbt.TagList;
import com.energyxxer.commodore.functionlogic.nbt.TagShort;
import com.energyxxer.commodore.functionlogic.nbt.TagString;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.functionlogic.selector.arguments.*;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.standard.StandardDefinitionPacks;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.util.DoubleRange;
import com.energyxxer.commodore.util.IntegerRange;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.energyxxer.commodore.functionlogic.commands.tag.TagCommand.Action.ADD;
import static com.energyxxer.commodore.functionlogic.selector.Selector.BaseSelector.ALL_ENTITIES;

public class EnhancedHoes {

    private static final int CHANCE_WOOD = 5,
            CHANCE_STONE = 10,
            CHANCE_IRON = 20,
            CHANCE_GOLD = 30,
            CHANCE_DIAMOND = 55;


    public static void main(String[] args) {
        CommandModule module = new CommandModule("Enhanced Hoes", "eh");
        try {
            StandardDefinitionPacks.MINECRAFT_JAVA_LATEST_RELEASE.populate(module);
        } catch(IOException x) {
            x.printStackTrace();
        }

        Objective count = module.getObjectiveManager().create("count");
        Objective tier = module.getObjectiveManager().create("tier");
        Objective crowd = module.getObjectiveManager().create("crowd");

        Namespace minecraft = module.minecraft;
        Type itemEntityType = minecraft.getTypeManager().entity.get("item");

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
                tick.append(new TagCommand(ADD, sel, entry.getKey()));
            }
        }

        Selector allCropsSelector = new Selector(ALL_ENTITIES, new TypeArgument(itemEntityType), new TagArgument("crop"));
        Selector selfCropsSelector = new Selector(ALL_ENTITIES, new TypeArgument(itemEntityType), new TagArgument("self_crop"));

        Selector allCrops = allCropsSelector;
        Selector allSelfCrops = selfCropsSelector;

        tick.append(new TagCommand(TagCommand.Action.ADD, allSelfCrops, "crop"));

        tick.append(new FunctionComment("Prevent item frames from duplicating crops"));
        {
            Selector nearbyCrops = allCrops.clone();
            nearbyCrops.addArguments(new DistanceArgument(new DoubleRange(null, 1.0)));

            ExecuteCommand exec = new ExecuteCommand(new TagCommand(TagCommand.Action.REMOVE, nearbyCrops, "crop"));
            exec.addModifier(new ExecuteAtEntity(new Selector(ALL_ENTITIES, new TypeArgument(minecraft.getTypeManager().entity.get("item_frame")))));
            tick.append(exec);
        }

        tick.append(new FunctionComment("Prevent duplication of premature harvests"));
        {
            Selector nearbyCrops = allSelfCrops.clone();
            nearbyCrops.addArguments(new DistanceArgument(new DoubleRange(null, 1.0)));
            ExecuteCommand exec = new ExecuteCommand(new ScoreAdd(new LocalScore(crowd, nearbyCrops), 1));
            exec.addModifier(new ExecuteAtEntity(allSelfCrops));
            tick.append(exec);
        }
        {
            ScoreArgument scores = new ScoreArgument();
            scores.put(crowd, new IntegerRange(1));
            Selector loneCrops = allSelfCrops.clone();
            loneCrops.addArguments(scores);
            tick.append(new DataMergeCommand(loneCrops, new TagCompound(new TagList("Tags"))));
        }

        try {
            module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output" + File.separator + module.getName() + File.separator));
        } catch(IOException x) {
            x.printStackTrace();
        }

        System.out.println(tick.getResolvedContent());
    }
}
