package com.energyxxer.commodore.util.attributes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Attribute {
    public enum Operation {
        ADDITION, MULTIPLY_BASE, MULTIPLY_TOTAL
    }

    public enum Slot {
        MAINHAND,
        OFFHAND,
        FEET,
        LEGS,
        CHEST,
        HEAD
    }

    public static final String MAX_HEALTH = "generic.maxHealth";
    public static final String FOLLOW_RANGE = "generic.followRange";
    public static final String KNOCKBACK_RESISTANCE = "generic.knockbackResistance";
    public static final String MOVEMENT_SPEED = "generic.movementSpeed";
    public static final String ATTACK_DAMAGE = "generic.attackDamage";
    public static final String ARMOR = "generic.armor";
    public static final String ARMOR_TOUGHNESS = "generic.armorToughness";
    public static final String ATTACK_SPEED = "generic.attackSpeed";
    public static final String LUCK = "generic.luck";
    public static final String ATTACK_KNOCKBACK = "generic.attackKnockback";
    public static final String FLYING_SPEED = "generic.flyingSpeed";
    public static final String HORSE_JUMP_STRENGTH = "horse.jumpStrength";
    public static final String ZOMBIE_SPAWN_REINFORCEMENTS = "zombie.spawnReinforcements";

    @NotNull
    private String name;
    @NotNull
    private String attributeName;
    @NotNull
    private Operation operation;
    private double minAmount;
    private double maxAmount;
    @Nullable
    private String id;
    @NotNull
    private Slot[] slots;

    public Attribute(@NotNull String name, @NotNull String attributeName, @NotNull Operation operation, double amount, @NotNull Slot... slots) {
        this(name, attributeName, operation, amount, amount, slots);
    }

    public Attribute(@NotNull String name, @NotNull String attributeName, @NotNull Operation operation, double minAmount, double maxAmount, @NotNull Slot... slots) {
        this(name, attributeName, operation, minAmount, maxAmount, null, slots);
    }

    public Attribute(@NotNull String name, @NotNull String attributeName, @NotNull Operation operation, double amount, @Nullable String id, @NotNull Slot... slots) {
        this(name, attributeName, operation, amount, amount, id, slots);
    }

    public Attribute(@NotNull String name, @NotNull String attributeName, @NotNull Operation operation, double minAmount, double maxAmount, @Nullable String id, @NotNull Slot... slots) {
        this.name = name;
        this.attributeName = attributeName;
        this.operation = operation;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.id = id;
        this.slots = slots;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(@NotNull String attributeName) {
        this.attributeName = attributeName;
    }

    @NotNull
    public Operation getOperation() {
        return operation;
    }

    public void setOperation(@NotNull Operation operation) {
        this.operation = operation;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public void setAmount(double amount) {
        setMinAmount(amount);
        setMaxAmount(amount);
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @NotNull
    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(@NotNull Slot[] slots) {
        this.slots = slots;
    }
}
