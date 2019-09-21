package com.energyxxer.commodore.standard;

import com.energyxxer.commodore.defpacks.DefinitionPack;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.util.io.CompoundInput;
import org.jetbrains.annotations.NotNull;

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
    @NotNull
    public static final DefinitionPack MINECRAFT_JAVA_1_13 = new DefinitionPack(CompoundInput.Static.chooseInputForClasspath("/defpacks/minecraft_j_1_13/", StandardDefinitionPacks.class));
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
    @NotNull
    public static final DefinitionPack MINECRAFT_JAVA_1_14 = new DefinitionPack(CompoundInput.Static.chooseInputForClasspath("/defpacks/minecraft_j_1_14/", StandardDefinitionPacks.class));
    /**
     * Contains the definitions for all game data for the 1.15 version of Minecraft Java Edition. Contains:
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
    @NotNull
    public static final DefinitionPack MINECRAFT_JAVA_1_15 = new DefinitionPack(CompoundInput.Static.chooseInputForClasspath("/defpacks/minecraft_j_1_15/", StandardDefinitionPacks.class));
    @NotNull
    public static final DefinitionPack MINECRAFT_JAVA_LATEST_RELEASE = MINECRAFT_JAVA_1_14;
    @NotNull
    public static final DefinitionPack MINECRAFT_JAVA_LATEST_SNAPSHOT = MINECRAFT_JAVA_1_15;
    /**
     * Contains the definitions for all game data for the 1.11 version of Minecraft Bedrock Edition. Contains:
     *
     * <ol>
     * <li>Blocks</li>
     * <li>Fluids</li>
     * <li>Items</li>
     * <li>Entities</li>
     * <li>Effects (including their numeric IDs and type)</li>
     * <li>Particles (including their argument type)</li>
     * <li>Gamemodes</li>
     * <li>Biomes</li>
     * <li>Dimensions</li>
     * <li>Structures (used by the /locate command)</li>
     * <li>Gamerules (including their expected value type)</li>
     * <li>Slots (used by the /replaceitem command)</li>
     * </ol>
     * <p>
     * This also includes all the tags in the vanilla data pack, for both blocks, fluids and items.
     */
    @NotNull
    public static final DefinitionPack MINECRAFT_BEDROCK_LATEST_RELEASE = new DefinitionPack(CompoundInput.Static.chooseInputForClasspath("/defpacks/minecraft_b_1_11/", StandardDefinitionPacks.class));
}
