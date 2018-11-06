package com.energyxxer.commodore.loottables.functions;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import com.energyxxer.commodore.loottables.conditions.LootConditions;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.util.attributes.Attribute;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.energyxxer.commodore.types.TypeAssert.assertEnchantment;

public abstract class LootFunction {

    private final String type;

    public final LootConditions conditions = new LootConditions();

    public String getType() {
        return type;
    }

    public LootFunction(String type) {
        this.type = type;
    }

    public JsonObject construct() {
        JsonObject object = new JsonObject();
        object.addProperty("function", type);
        return object;
    }

    public static class SetCount extends LootFunction {
        private int min, max;

        public SetCount(int count) {
            this(count, count);
        }

        public SetCount(int min, int max) {
            super("set_count");
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            object.add("count", CommandUtils.constructRange(min, max));
            return object;
        }
    }

    public static class SetDamage extends LootFunction {
        private int min, max;

        public SetDamage(int damage) {
            this(damage, damage);
        }

        public SetDamage(int min, int max) {
            super("set_damage");
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            object.add("damage", CommandUtils.constructRange(min, max));
            return object;
        }
    }

    public static class SetNBT extends LootFunction {
        private TagCompound tag;

        public SetNBT(TagCompound tag) {
            super("set_nbt");
            this.tag = tag;
        }

        public TagCompound getTag() {
            return tag;
        }

        public void setTag(TagCompound tag) {
            this.tag = tag;
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            object.addProperty("tag", CommandUtils.escape(tag.toHeadlessString()));
            return object;
        }
    }

    public static class SetAttributes extends LootFunction {
        private Collection<Attribute> attributes;

        public SetAttributes(Attribute... attributes) {
            this(Arrays.asList(attributes));
        }

        public SetAttributes(Collection<Attribute> attributes) {
            super("set_attributes");
            this.attributes = new ArrayList<>(attributes);
        }

        public Collection<Attribute> getAttributes() {
            return attributes;
        }

        public void setAttributes(Collection<Attribute> attributes) {
            this.attributes = attributes;
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            JsonArray list = new JsonArray();
            object.add("modifiers", list);
            for(Attribute attr : attributes) {
                JsonObject attrObj = new JsonObject();
                list.add(attrObj);
                attrObj.addProperty("attribute", attr.getAttributeName());
                attrObj.addProperty("name", attr.getName());
                if(attr.getId() != null) attrObj.addProperty("id", attr.getId());
                attrObj.add("amount", CommandUtils.constructRange(attr.getMinAmount(), attr.getMaxAmount()));
                attrObj.addProperty("operation", attr.getOperation().toString().toLowerCase());
                Attribute.Slot[] slots = attr.getSlots();
                if(slots.length > 0) {
                    if(slots.length == 1) {
                        attrObj.addProperty("slot", slots[0].toString().toLowerCase());
                    } else {
                        JsonArray slotList = new JsonArray();
                        attrObj.add("slot", slotList);
                        for(Attribute.Slot slot : slots) {
                            slotList.add(slot.toString().toLowerCase());
                        }
                    }
                }
            }
            return object;
        }
    }

    public static class EnchantRandomly extends LootFunction {
        private Collection<Type> enchantments;

        public EnchantRandomly(Type... enchantments) {
            this(Arrays.asList(enchantments));
        }

        public EnchantRandomly(Collection<Type> enchantments) {
            super("enchant_randomly");
            for(Type type : enchantments) {
                assertEnchantment(type);
            }
            this.enchantments = new ArrayList<>(enchantments);
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            if(!enchantments.isEmpty()) {
                JsonArray list = new JsonArray();
                object.add("enchantments", list);
                for (Type ench : enchantments) {
                    list.add(ench.toString());
                }
            }
            return object;
        }
    }

    public static class EnchantWithLevels extends LootFunction {
        private int minLevels;
        private int maxLevels;
        private boolean treasure = false;

        public EnchantWithLevels(int levels) {
            this(levels, levels);
        }

        public EnchantWithLevels(int minLevels, int maxLevels) {
            this(minLevels, maxLevels, false);
        }

        public EnchantWithLevels(int levels, boolean treasure) {
            this(levels, levels, treasure);
        }

        public EnchantWithLevels(int minLevels, int maxLevels, boolean treasure) {
            super("enchant_with_levels");
            this.minLevels = minLevels;
            this.maxLevels = maxLevels;
            this.treasure = treasure;
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            object.add("levels", CommandUtils.constructRange(minLevels, maxLevels));
            if(treasure) object.addProperty("treasure", treasure);
            return object;
        }
    }

    public static class LootingEnchant extends LootFunction {
        private int minCount;
        private int maxCount;
        private int limit;

        public LootingEnchant(int count) {
            this(count, -1);
        }

        public LootingEnchant(int count, int limit) {
            this(count, count, limit);
        }

        public LootingEnchant(int minCount, int maxCount, int limit) {
            super("looting_enchant");
            this.minCount = minCount;
            this.maxCount = maxCount;
            this.limit = limit;
        }

        @Override
        public JsonObject construct() {
            JsonObject object = super.construct();
            object.add("count", CommandUtils.constructRange(minCount, maxCount));
            if(limit >= 0) object.addProperty("limit", limit);
            return object;
        }
    }

    public static class FurnaceSmelt extends LootFunction {

        public FurnaceSmelt() {
            super("furnace_smelt");
        }
    }
}
