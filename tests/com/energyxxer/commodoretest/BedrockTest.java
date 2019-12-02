package com.energyxxer.commodoretest;

import com.energyxxer.commodore.functionlogic.commands.effect.EffectClearCommand;
import com.energyxxer.commodore.functionlogic.commands.effect.EffectGiveCommand;
import com.energyxxer.commodore.functionlogic.commands.scoreboard.ScoreSet;
import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.functions.FunctionComment;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.PlayerName;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.standard.StandardDefinitionPacks;
import com.energyxxer.commodore.util.StatusEffect;
import com.energyxxer.commodore.versioning.BedrockEditionVersion;

import java.io.File;
import java.io.IOException;

import static com.energyxxer.commodore.functionlogic.selector.Selector.BaseSelector.ALL_PLAYERS;
import static com.energyxxer.commodore.functionlogic.selector.Selector.BaseSelector.NEAREST_PLAYER;

public class BedrockTest {
    public static void main(String[] args) {
        CommandModule module = new CommandModule("Bedrock Test", "bt");
        module.setSettingsActive();
        /*module.getSettingsManager().setTargetVersion(new JavaEditionVersion(1, 14, 0));
        //*/
        module.getSettingsManager().setTargetVersion(new BedrockEditionVersion(1, 11, 0));

        try {
            /*module.importDefinitions(StandardDefinitionPacks.MINECRAFT_JAVA_LATEST_SNAPSHOT);
            //*/
            module.importDefinitions(StandardDefinitionPacks.MINECRAFT_BEDROCK_LATEST_RELEASE);
        } catch (IOException x) {
            x.printStackTrace();
        }

        Namespace ns = module.getNamespace("test");

        Function function = ns.getFunctionManager().create("func", true, new Selector(NEAREST_PLAYER));
        function.append(new FunctionComment(module.getSettingsManager().getTargetVersion().toString()));

        function.append(new EffectClearCommand(new Selector(ALL_PLAYERS), module.minecraft.types.effect.get("poison")));
        function.append(new EffectClearCommand(new Selector(ALL_PLAYERS)));

        function.append(new EffectGiveCommand(new Selector(ALL_PLAYERS), new StatusEffect(module.minecraft.types.effect.get("regeneration"))));


        function.append(new ScoreSet(new LocalScore(new PlayerName("a b"), module.getObjectiveManager().get("c d")), 1));

        System.out.println(function.getResolvedContent());

        try {
            module.compile(new File(System.getProperty("user.home") + File.separator + "Commodore Output" + File.separator + module.getName() + File.separator));
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
