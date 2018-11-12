package com.energyxxer.commodore.standard;

import com.energyxxer.commodore.defpacks.DefinitionPack;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.util.io.CompoundInput;

/**
 * Holds definition packs built into Commodore that can be utilized by command modules.
 *
 * @see DefinitionPack
 * @see CommandModule
 * */
public class StandardDefinitionPacks {
    /**
     * Contains the definitions for all game data for the 1.13 version of Minecraft Java Edition. Contains:
     *
     * <ol>
     *     <li>Blocks</li>
     *     <li>Fluids</li>
     *     <li>Items</li>
     *     <li>Entities</li>
     *     <li>Effects (including their numeric IDs and type)</li>
     *     <li>Particles (including their argument type)</li>
     *     <li>Gamemodes</li>
     *     <li>Biomes</li>
     *     <li>Dimensions</li>
     *     <li>Structures (used by the /locate command)</li>
     *     <li>Gamerules (including their expected value type)</li>
     *     <li>Slots (used by the /replaceitem command)</li>
     * </ol>
     *
     * This also includes all the tags in the vanilla data pack, for both blocks, fluids and items.
     * */
    public static final DefinitionPack MINECRAFT_JAVA_LATEST_RELEASE = new DefinitionPack(CompoundInput.Static.chooseInputForClasspath("/defpacks/minecraft_j_1_13/", StandardDefinitionPacks.class));
    /**
     * Contains the definitions for all game data for the 1.14 version of Minecraft Java Edition. Contains:
     *
     * <ol>
     *     <li>Blocks</li>
     *     <li>Fluids</li>
     *     <li>Items</li>
     *     <li>Entities</li>
     *     <li>Effects (including their numeric IDs and type)</li>
     *     <li>Particles (including their argument type)</li>
     *     <li>Gamemodes</li>
     *     <li>Biomes</li>
     *     <li>Dimensions</li>
     *     <li>Structures (used by the /locate command)</li>
     *     <li>Gamerules (including their expected value type)</li>
     *     <li>Slots (used by the /replaceitem command)</li>
     * </ol>
     *
     * This also includes all the tags in the vanilla data pack, for both blocks, fluids, items and entity types.
     * */
    public static final DefinitionPack MINECRAFT_JAVA_LATEST_SNAPSHOT = new DefinitionPack(CompoundInput.Static.chooseInputForClasspath("/defpacks/minecraft_j_1_14/", StandardDefinitionPacks.class));
}
