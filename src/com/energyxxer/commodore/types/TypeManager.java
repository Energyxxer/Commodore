package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

import java.util.HashMap;

public class TypeManager {
    private final Namespace owner;

    private HashMap<String, HashMap<String, Type>> types;

    public final TypeInstantiator<BlockType> block;
    public final TypeInstantiator<ItemType> item;
    public final TypeInstantiator<EffectType> effect;
    public final TypeInstantiator<EntityType> entity;
    public final TypeInstantiator<ParticleType> particle;

    public final TypeInstantiator<DifficultyType> difficulty;
    public final TypeInstantiator<DimensionType> dimension;
    public final TypeInstantiator<GamemodeType> gamemode;

    public TypeManager(Namespace owner) {
        this.owner = owner;
        this.types = new HashMap<>();
        types.put("block",new HashMap<>());
        types.put("item",new HashMap<>());
        types.put("effect",new HashMap<>());
        types.put("entity",new HashMap<>());
        types.put("particle",new HashMap<>());

        types.put("difficulty",new HashMap<>());
        types.put("dimension",new HashMap<>());
        types.put("gamemode",new HashMap<>());

        this.block = (id) -> {
            Type existing = types.get("block").get(id);
            if(existing != null) return (BlockType) existing;

            BlockType t = new BlockType(this.owner, id);
            types.get("block").put(id, t);
            return t;
        };
        this.item = (id) -> {
            Type existing = types.get("item").get(id);
            if(existing != null) return (ItemType) existing;

            ItemType t = new ItemType(this.owner, id);
            types.get("item").put(id, t);
            return t;
        };
        this.effect = (id) -> {
            Type existing = types.get("effect").get(id);
            if(existing != null) return (EffectType) existing;

            EffectType t = new EffectType(this.owner, id);
            types.get("effect").put(id, t);
            return t;
        };
        this.entity = (id) -> {
            Type existing = types.get("entity").get(id);
            if(existing != null) return (EntityType) existing;

            EntityType t = new EntityType(this.owner, id);
            types.get("entity").put(id, t);
            return t;
        };
        this.particle = (id) -> {
            Type existing = types.get("particle").get(id);
            if(existing != null) return (ParticleType) existing;

            ParticleType t = new ParticleType(this.owner, id);
            types.get("particle").put(id, t);
            return t;
        };

        this.difficulty = (id) -> {
            Type existing = types.get("difficulty").get(id);
            if(existing != null) return (DifficultyType) existing;

            DifficultyType t = new DifficultyType(id);
            types.get("difficulty").put(id, t);
            return t;
        };
        this.dimension = (id) -> {
            Type existing = types.get("dimension").get(id);
            if(existing != null) return (DimensionType) existing;

            DimensionType t = new DimensionType(id);
            types.get("dimension").put(id, t);
            return t;
        };
        this.gamemode = (id) -> {
            Type existing = types.get("gamemode").get(id);
            if(existing != null) return (GamemodeType) existing;

            GamemodeType t = new GamemodeType(id);
            types.get("gamemode").put(id, t);
            return t;
        };
    }


}
