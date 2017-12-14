package com.energyxxer.commodoretest;

import com.energyxxer.commodore.commands.DataMergeCommand;
import com.energyxxer.commodore.commands.ExecuteCommand;
import com.energyxxer.commodore.commands.TagCommand;
import com.energyxxer.commodore.commands.scoreboard.ScoreAdd;
import com.energyxxer.commodore.coordinates.ExecuteAtEntity;
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
import com.energyxxer.commodore.score.Objective;
import com.energyxxer.commodore.selector.DistanceArgument;
import com.energyxxer.commodore.selector.NBTArgument;
import com.energyxxer.commodore.selector.ScoreArgument;
import com.energyxxer.commodore.selector.Selector;
import com.energyxxer.commodore.selector.SelectorNumberArgument;
import com.energyxxer.commodore.selector.TagArgument;
import com.energyxxer.commodore.selector.TypeArgument;
import com.energyxxer.commodore.types.EntityType;

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

        Objective count = module.getObjectiveManager().create("count");
        Objective tier = module.getObjectiveManager().create("tier");
        Objective crowd = module.getObjectiveManager().create("crowd");

        Namespace minecraft = module.minecraft;
        EntityType itemEntityType = minecraft.getTypeManager().entity.create("item");

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

        Entity allCrops = new GenericEntity(new Selector(ALL_ENTITIES, new TagArgument("crop")));
        Entity allSelfCrops = new GenericEntity(new Selector(ALL_ENTITIES, new TagArgument("selfcrop")));

        tick.append(new TagCommand(TagCommand.Action.ADD, allSelfCrops, "crop"));

        tick.append(new FunctionComment("Prevent item frames from duplicating crops"));
        {
            ExecuteCommand exec = new ExecuteCommand(new TagCommand(TagCommand.Action.REMOVE, new GenericEntity(new Selector(ALL_ENTITIES, new TagArgument("crop"), new DistanceArgument(new SelectorNumberArgument<>(null, 1.0)))), "crop"));
            exec.addModifier(new ExecuteAtEntity(new GenericEntity(new Selector(ALL_ENTITIES, new TypeArgument(minecraft.getTypeManager().entity.create("item_frame"))))));
            tick.append(exec);
        }

        tick.append(new FunctionComment("Prevent duplication of premature harvests"));
        {
            Entity nearbyCrops = new GenericEntity(new Selector(ALL_ENTITIES, new TagArgument("crop"), new DistanceArgument(new SelectorNumberArgument<>(null, 1.0))));
            ExecuteCommand exec = new ExecuteCommand(new ScoreAdd(new LocalScore(crowd, nearbyCrops), 1));
            exec.addModifier(new ExecuteAtEntity(allCrops));
            tick.append(exec);
        }
        {
            ScoreArgument scores = new ScoreArgument();
            scores.put(crowd, new SelectorNumberArgument<>(1));
            tick.append(new DataMergeCommand(new GenericEntity(new Selector(ALL_ENTITIES, new TagArgument("crop"), scores)), new TagCompound(new TagList("Tags"))));
        }

        module.compile();

        System.out.println(tick.getResolvedContent());
    }
}
