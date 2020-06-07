package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.TypeDictionary;

import java.util.Collection;
import java.util.HashMap;

/**
 * Contains, retrieves and controls all the types under a namespace.
 *
 * @see Type
 * @see TypeDictionary
 * @see Namespace
 * @see GenericType
 *
 * */
public class TypeManager {
    /**
     * The namespace this type manager belongs to.
     * */
    private final Namespace owner;

    /**
     * A map of all the dictionaries in this type manager, where the key is the case-sensitive category for the types
     * in the dictionary, and the value is said dictionary.
     * */
    private final HashMap<String, TypeDictionary> dictionaries = new HashMap<>();

    /**
     * A pre-defined type dictionary for all block types.
     * */
    public final TypeDictionary block;
    /**
     * A pre-defined type dictionary for all fluid types.
     * */
    public final TypeDictionary fluid;
    /**
     * A pre-defined type dictionary for all item types.
     * */
    public final TypeDictionary item;
    /**
     * A pre-defined type dictionary for all effect types.
     * */
    public final TypeDictionary effect;
    /**
     * A pre-defined type dictionary for all potion types.
     * */
    public final TypeDictionary potion;
    /**
     * A pre-defined type dictionary for all entity types.
     * */
    public final TypeDictionary entity;
    /**
     * A pre-defined type dictionary for all block entity types.
     * */
    public final TypeDictionary blockEntity;
    /**
     * A pre-defined type dictionary for all particle types.
     * */
    public final TypeDictionary particle;
    /**
     * A pre-defined type dictionary for all enchantment types.
     * */
    public final TypeDictionary enchantment;
    /**
     * A pre-defined type dictionary for all dimension types.
     * */
    public final TypeDictionary dimension;
    /**
     * A pre-defined type dictionary for all biome types.
     * */
    public final TypeDictionary biome;
    /**
     * A pre-defined type dictionary for all attribute types.
     * */
    public final TypeDictionary attribute;
    /**
     * A pre-defined type dictionary for all painting motive types.
     * */
    public final TypeDictionary motive;
    /**
     * A pre-defined type dictionary for all villager types.
     * */
    public final TypeDictionary villagerType;
    /**
     * A pre-defined type dictionary for all villager types.
     * */
    public final TypeDictionary villagerProfession;


    /**
     * A pre-defined type dictionary for all difficulty types.
     * */
    public final TypeDictionary difficulty;
    /**
     * A pre-defined type dictionary for all gamemode types.
     * */
    public final TypeDictionary gamemode;
    /**
     * A pre-defined type dictionary for all gamerule types.
     * */
    public final TypeDictionary gamerule;
    /**
     * A pre-defined type dictionary for all structure types.
     * */
    public final TypeDictionary structure;
    /**
     * A pre-defined type dictionary for all item slot types.
     * */
    public final TypeDictionary slot;
    /**
     * A pre-defined type dictionary for all score display types.
     * */
    public final TypeDictionary scoreDisplay;

    /**
     * Creates a type manager belonging to the given namespace, filled with the standard type dictionaries.
     *
     * @param owner The parent namespace for this type manager.
     * */
    public TypeManager(Namespace owner) {
        this.owner = owner;

        put(this.block = new TypeDictionary(owner, BlockType.CATEGORY, BlockType::new));
        put(this.fluid = new TypeDictionary(owner, FluidType.CATEGORY, FluidType::new));
        put(this.item = new TypeDictionary(owner, ItemType.CATEGORY, ItemType::new));
        put(this.effect = new TypeDictionary(owner, EffectType.CATEGORY, EffectType::new));
        put(this.potion = new TypeDictionary(owner, PotionType.CATEGORY, PotionType::new));
        put(this.entity = new TypeDictionary(owner, EntityType.CATEGORY, EntityType::new));
        put(this.blockEntity = new TypeDictionary(owner, BlockEntityType.CATEGORY, BlockEntityType::new));
        put(this.particle = new TypeDictionary(owner, ParticleType.CATEGORY, ParticleType::new));
        put(this.enchantment = new TypeDictionary(owner, EnchantmentType.CATEGORY, EnchantmentType::new));
        put(this.dimension = new TypeDictionary(owner, DimensionType.CATEGORY, DimensionType::new));
        put(this.biome = new TypeDictionary(owner, BiomeType.CATEGORY, BiomeType::new));
        put(this.attribute = new TypeDictionary(owner, AttributeType.CATEGORY, AttributeType::new));
        put(this.motive = new TypeDictionary(owner, MotiveType.CATEGORY, MotiveType::new));
        put(this.villagerType = new TypeDictionary(owner, VillagerType.CATEGORY, VillagerType::new));
        put(this.villagerProfession = new TypeDictionary(owner, VillagerProfessionType.CATEGORY, VillagerProfessionType::new));

        put(this.difficulty = new TypeDictionary(owner, "difficulty", DifficultyType::new));
        put(this.gamemode = new TypeDictionary(owner, "gamemode", GamemodeType::new));
        put(this.gamerule = new TypeDictionary(owner, "gamerule", GameruleType::new));
        put(this.structure = new TypeDictionary(owner, "structure", StructureType::new));

        put(this.slot = new TypeDictionary(owner, "slot", ItemSlot::new));
        put(this.scoreDisplay = new TypeDictionary(owner, "score_display", ScoreDisplayType::new));
    }

    /**
     * Adds the given dictionary to this type manager's list of dictionaries.
     *
     * @param dict The dictionary to add to this type manager.
     * */
    private void put(TypeDictionary dict) {
        dictionaries.put(dict.getCategory(), dict);
    }

    /**
     * Adds all the type definitions from the passed object into this type manager's dictionaries.
     *
     * @param other The type manager object whose types should be added to this type manager.
     * */
    public void join(TypeManager other) {
        for(TypeDictionary fromThat : other.dictionaries.values()) {
            boolean useNamespace = !fromThat.list().isEmpty() && fromThat.list().toArray(new Type[0])[0].useNamespace();
            TypeDictionary dict = createDictionary(fromThat.getCategory(), useNamespace);
            for(Type t : fromThat.list()) {
                Type newType = dict.getOrCreate(t.getName());
                newType.putProperties(t.getProperties());
            }
        }
    }

    /**
     * Creates a dictionary for the specified category, and adds it to this type manager.
     * The type this new dictionary will create is of the class {@link GenericType}.<br>
     * If the category specified already exists in this type manager, that will be returned instead.
     *
     * @param category The case-sensitve category string that represents types created by the new dictionary.
     * @param useNamespace Whether the types created by the new dictionary should be printed onto
     *                     commands with the <code>namespace:</code> prefix. <code>true</code> if it should,
     *                     <code>false</code> if it shouldn't.
     *                     If the specified category already exists in this type manager, this parameter will have
     *                     no effect.
     *
     * @return The dictionary for the specified category. If the category exists before the method call,
     * that will be returned. Otherwise, a new dictionary is created and returned.
     * */
    public TypeDictionary createDictionary(String category, boolean useNamespace) {
        if (dictionaries.containsKey(category)) {
            TypeDictionary typeDict = dictionaries.get(category);
            typeDict.usesNamespace = useNamespace;
            return typeDict;
        }
        TypeDictionary newDict = new TypeDictionary(owner, category, (ns, id) -> new GenericType(category, ns, id));
        put(newDict);
        return newDict;
    }

    /**
     * Retrieves a dictionary for a specified category.
     *
     * @param category The category whose dictionary is to be returned.
     *
     * @return The type dictionary for the specified category, if it exists. <code>null</code> if it doesn't exist.
     * */
    public TypeDictionary getDictionary(String category) {
        return dictionaries.get(category);
    }

    /**
     * Retrieves a collection with all the dictionaries in this type manager.
     *
     * @return A collection with all the dictionaries in this type manager.
     * */
    public Collection<TypeDictionary> getAllDictionaries() {
        return dictionaries.values();
    }
}
