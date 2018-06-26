package com.energyxxer.commodore.types;

import com.energyxxer.commodore.types.defaults.*;

public class TypeAssert {

    public static void assertType(Type type, String category) {
        if(!category.equals(type.category)) throw new IllegalArgumentException("Expected type of category '" + category + "', instead got '" + type + "'");
    }

    public static void assertConcrete(Type type) {
        if(!type.isConcrete()) throw new IllegalArgumentException("Expected concrete type");
    }

    public static void assertBlock(Type type) {
        assertType(type, BlockType.CATEGORY);
    }

    public static void assertItem(Type type) {
        assertType(type, ItemType.CATEGORY);
    }

    public static void assertFluid(Type type) {
        assertType(type, FluidType.CATEGORY);
    }

    public static void assertBiome(Type type) {
        assertType(type, BiomeType.CATEGORY);
    }

    public static void assertDifficulty(Type type) {
        assertType(type, DifficultyType.CATEGORY);
    }

    public static void assertDimension(Type type) {
        assertType(type, DimensionType.CATEGORY);
    }

    public static void assertEntity(Type type) {
        assertType(type, EntityType.CATEGORY);
    }

    public static void assertEffect(Type type) {
        assertType(type, EffectType.CATEGORY);
    }

    public static void assertEnchantment(Type type) {
        assertType(type, EnchantmentType.CATEGORY);
    }

    public static void assertGamemode(Type type) {
        assertType(type, GamemodeType.CATEGORY);
    }

    public static void assertGamerule(Type type) {
        assertType(type, GameruleType.CATEGORY);
    }

    public static void assertSlot(Type type) {
        assertType(type, ItemSlot.CATEGORY);
    }

    public static void assertParticle(Type type) {
        assertType(type, ParticleType.CATEGORY);
    }

    public static void assertStructure(Type type) {
        assertType(type, StructureType.CATEGORY);
    }

    public static void assertFunction(Type type) {
        assertType(type, FunctionReference.CATEGORY);
    }

    public static void assertBossbar(Type type) {
        assertType(type, BossbarReference.CATEGORY);
    }
}
