package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class TypeManager {
    private final Namespace owner;

    //private ArrayList<Type> types = new ArrayList<>();

    public final TypeInstantiator<BlockType>    block;
    public final TypeInstantiator<ItemType>     item;
    public final TypeInstantiator<EffectType>   effect;
    public final TypeInstantiator<EntityType>   entity;

    public TypeManager(Namespace owner) {
        this.owner = owner;

        this.block   = (id) -> new BlockType (this.owner, id);
        this.item    = (id) -> new ItemType  (this.owner, id);
        this.effect  = (id) -> new EffectType(this.owner, id);
        this.entity  = (id) -> new EntityType(this.owner, id);
    }


}
