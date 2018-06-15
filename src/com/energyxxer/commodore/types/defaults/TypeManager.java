package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.GenericType;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.TypeDictionary;

import java.util.HashMap;

public class TypeManager {
    private final Namespace owner;

    private final HashMap<String, TypeDictionary<? extends Type>> types = new HashMap<>();

    public final TypeDictionary<BlockType> block;
    public final TypeDictionary<FluidType> fluid;
    public final TypeDictionary<ItemType> item;
    public final TypeDictionary<EffectType> effect;
    public final TypeDictionary<EntityType> entity;
    public final TypeDictionary<ParticleType> particle;
    public final TypeDictionary<EnchantmentType> enchantment;
    public final TypeDictionary<DimensionType> dimension;
    public final TypeDictionary<BiomeType> biome;

    public final TypeDictionary<DifficultyType> difficulty;
    public final TypeDictionary<GamemodeType> gamemode;
    public final TypeDictionary<GameruleType> gamerule;
    public final TypeDictionary<StructureType> structure;

    public final TypeDictionary<ItemSlot> slot;

    public TypeManager(Namespace owner) {
        this.owner = owner;

        put(this.block = new TypeDictionary<>(owner, "block", (id) -> new BlockType(this.owner, id)));
        put(this.fluid = new TypeDictionary<>(owner, "fluid", (id) -> new FluidType(this.owner, id)));
        put(this.item = new TypeDictionary<>(owner, "item", (id) -> new ItemType(this.owner, id)));
        put(this.effect = new TypeDictionary<>(owner, "effect", (id) -> new EffectType(this.owner, id)));
        put(this.entity = new TypeDictionary<>(owner, "entity", (id) -> new EntityType(this.owner, id)));
        put(this.particle = new TypeDictionary<>(owner, "particle", (id) -> new ParticleType(this.owner, id)));
        put(this.enchantment = new TypeDictionary<>(owner, "enchantment", (id) -> new EnchantmentType(this.owner, id)));
        put(this.dimension = new TypeDictionary<>(owner, "dimension", (id) -> new DimensionType(this.owner, id)));
        put(this.biome = new TypeDictionary<>(owner, "biome", (id) -> new BiomeType(this.owner, id)));

        put(this.difficulty = new TypeDictionary<>(owner, "difficulty", DifficultyType::new));
        put(this.gamemode = new TypeDictionary<>(owner, "gamemode", GamemodeType::new));
        put(this.gamerule = new TypeDictionary<>(owner, "gamerule", GameruleType::new));
        put(this.structure = new TypeDictionary<>(owner, "structure", StructureType::new));

        put(this.slot = new TypeDictionary<>(owner, "slot", ItemSlot::new));
    }

    public void put(TypeDictionary<?> dict) {
        types.put(dict.getCategory(), dict);
    }

    public void join(TypeManager other) {
        for(TypeDictionary<? extends Type> fromThat : other.types.values()) {
            boolean useNamespace = !fromThat.list().isEmpty() && fromThat.list().toArray(new Type[0])[0].useNamespace();
            TypeDictionary<?> dict = createDictionary(fromThat.getCategory(), useNamespace);
            for(Type t : fromThat.list()) {
                dict.create(t.getName());
            }
        }
    }

    public TypeDictionary<?> createDictionary(String category, boolean useNamespace) {
        if(types.containsKey(category)) return types.get(category);
        TypeDictionary<?> newDict = new TypeDictionary<>(owner, category, (id) -> new GenericType(category, (useNamespace) ? this.owner : null, id));
        put(newDict);
        return newDict;
    }

    public TypeDictionary<?> getDictionary(String category) {
        return types.get(category);
    }
}
