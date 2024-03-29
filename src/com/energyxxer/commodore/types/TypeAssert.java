package com.energyxxer.commodore.types;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.types.defaults.*;

import java.util.Locale;

/**
 * Utility class whose purpose is to aid commands to validate types passed as parameters.<br>
 *
 * As the standard is to never use Type subclasses to narrow down possible types, and instead compare types via their
 * {@link Type#category} value, this class' methods do just that, throwing an exception if a type is not of a desired
 * type.
 * */
public class TypeAssert {

    /**
     * Throws an exception if the given type is not of the given category.
     *
     * @param type The type whose category is to be tested.
     * @param category The category the given type is expected to be.
     *
     * @throws IllegalTypeException If the given type is not of the given category.
     * */
    public static void assertType(Type type, String category) {
        if(!type.namespace.resolveAlias(category).equals(type.category)) throw new CommodoreException(CommodoreException.Source.TYPE_ERROR, "Expected type of category '" + category + "', instead got '" + type.category + "'", type);
    }

    /**
     * Throws an exception if the given type cannot be set on its own. To be used in cases where a type must
     * not be a tag.
     *
     * @param type The type whose standalone property is to be tested.
     *
     * @throws IllegalTypeException If the given type is not standalone; that is, if it is a tag.
     * */
    public static void assertStandalone(Type type) {
        if(!type.isStandalone()) throw new CommodoreException(CommodoreException.Source.TYPE_ERROR, type.getCategory().toUpperCase(Locale.ENGLISH).charAt(0) + type.getCategory().substring(1).toLowerCase(Locale.ENGLISH) + " tags aren't allowed in this context", type);
    }

    /**
     * Throws an exception if the given type is not of the category <i>block</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>block</i> category.
     * */
    public static void assertBlock(Type type) {
        assertType(type, BlockType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>item</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>item</i> category.
     * */
    public static void assertItem(Type type) {
        assertType(type, ItemType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>fluid</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>fluid</i> category.
     * */
    public static void assertFluid(Type type) {
        assertType(type, FluidType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>biome</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>biome</i> category.
     * */
    public static void assertBiome(Type type) {
        assertType(type, BiomeType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>attribute</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>attribute</i> category.
     * */
    public static void assertAttribute(Type type) {
        assertType(type, AttributeType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>difficulty</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>difficulty</i> category.
     * */
    public static void assertDifficulty(Type type) {
        assertType(type, DifficultyType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>dimension</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>dimension</i> category.
     * */
    public static void assertDimension(Type type) {
        assertType(type, DimensionType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>entity</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>entity</i> category.
     * */
    public static void assertEntity(Type type) {
        assertType(type, EntityType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>effect</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>effect</i> category.
     * */
    public static void assertEffect(Type type) {
        assertType(type, EffectType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>enchantment</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>enchantment</i> category.
     * */
    public static void assertEnchantment(Type type) {
        assertType(type, EnchantmentType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>gamemode</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>gamemode</i> category.
     * */
    public static void assertGamemode(Type type) {
        assertType(type, GamemodeType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>gamerule</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>gamerule</i> category.
     * */
    public static void assertGamerule(Type type) {
        assertType(type, GameruleType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>slot</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>slot</i> category.
     * */
    public static void assertSlot(Type type) {
        assertType(type, ItemSlot.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>particle</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>particle</i> category.
     * */
    public static void assertParticle(Type type) {
        assertType(type, ParticleType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>structure</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>structure</i> category.
     * */
    public static void assertStructure(Type type) {
        assertType(type, StructureType.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>function_reference</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>function_reference</i> category.
     * */
    public static void assertFunction(Type type) {
        assertType(type, FunctionReference.CATEGORY);
    }

    /**
     * Throws an exception if the given type is not of the category <i>bossbar_reference</i>.
     *
     * @param type The type whose category is to be tested.
     *
     * @throws IllegalTypeException If the given type is not of the <i>bossbar_reference</i> category.
     * */
    public static void assertBossbar(Type type) {
        assertType(type, BossbarReference.CATEGORY);
    }

    /**
     * TypeAssert must not be instantiated.
     * */
    private TypeAssert() {}
}
