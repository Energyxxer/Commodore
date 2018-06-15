package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class TypeManager {
    private final Namespace owner;

    public final TypeDictionary<BlockType> block;
    public final TypeDictionary<FluidType> fluid;
    public final TypeDictionary<ItemType> item;
    public final TypeDictionary<EffectType> effect;
    public final TypeDictionary<EntityType> entity;
    public final TypeDictionary<ParticleType> particle;
    public final TypeDictionary<EnchantmentType> enchantment;
    public final TypeDictionary<DimensionType> dimension;

    public final TypeDictionary<DifficultyType> difficulty;
    public final TypeDictionary<GamemodeType> gamemode;
    public final TypeDictionary<GameruleType> gamerule;
    public final TypeDictionary<StructureType> structure;

    public final TypeDictionary<ItemSlot> slot;

    public TypeManager(Namespace owner) {
        this.owner = owner;

        this.block = new TypeDictionary<>(owner, "block", (id) -> new BlockType(this.owner, id));
        this.fluid = new TypeDictionary<>(owner, "fluid", (id) -> new FluidType(this.owner, id));
        this.item = new TypeDictionary<>(owner, "item", (id) -> new ItemType(this.owner, id));
        this.effect = new TypeDictionary<>(owner, "effect", (id) -> new EffectType(this.owner, id));
        this.entity = new TypeDictionary<>(owner, "entity", (id) -> new EntityType(this.owner, id));
        this.particle = new TypeDictionary<>(owner, "particle", (id) -> new ParticleType(this.owner, id));
        this.enchantment = new TypeDictionary<>(owner, "enchantment", (id) -> new EnchantmentType(this.owner, id));
        this.dimension = new TypeDictionary<>(owner, "dimension", (id) -> new DimensionType(this.owner, id));

        this.difficulty = new TypeDictionary<>(owner, "difficulty", DifficultyType::new);
        this.gamemode = new TypeDictionary<>(owner, "gamemode", GamemodeType::new);
        this.gamerule = new TypeDictionary<>(owner, "gamerule", GameruleType::new);
        this.structure = new TypeDictionary<>(owner, "structure", StructureType::new);

        this.slot = new TypeDictionary<>(owner, "slot", ItemSlot::new);
    }


    public void join(TypeManager other) {
        this.block.join(other.block);
    }
}
