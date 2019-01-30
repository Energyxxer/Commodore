package com.energyxxer.commodore.block;

import com.energyxxer.commodore.CommodoreException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Defines a set of properties for a block.
 * */
public class Blockstate {
    /**
     * The map containing each of the properties of the blockstate, in key-value pairs.
     * */
    private final HashMap<String, String> map = new HashMap<>();

    /**
     * Creates an empty Blockstate object with no properties.
     * */
    public Blockstate() {
    }

    /**
     * Adds the given property to this blockstate. If a value associated with the given key already
     * exists in this blockstate, it will be replaced with the new value.
     *
     * @param key The key with which the value will be associated with.
     * @param value The value to be associated with the given key.
     * */
    public void put(@NotNull String key, @NotNull String value) {
        map.put(key, value);
    }

    /**
     * Dissociates a value from the given key, if it exists, removing it from the blockstate.
     *
     * @param key The key whose associated value is to be removed.
     * */
    public void remove(@NotNull String key) {
        map.remove(key);
    }

    /**
     * Retrieves the value associated with the property of the given key.
     *
     * @param key The key whose associated value is to be retrieved.
     *
     * @return The value associated with the given key. If such key is not found in the blockstate, <code>null</code>
     * is returned.
     * */
    public @NotNull String get(@NotNull String key) {
        return map.get(key);
    }

    /**
     * Retrieves the map containing the properties for this blockstate.
     *
     * @return The map containing this blockstate's properties. Any changes made to the returned map will also be made
     * to this blockstate's properties.
     * */
    public @NotNull HashMap<String, String> getProperties() {
        return map;
    }

    /**
     * Parses a string containing the properties of a blockstate and creates a blockstate object containing those
     * same properties. Example inputs:
     *
     * <code>axis=y</code>
     * <code>facing=south,half=bottom,shape=straight</code>
     * <code>facing=east,lit=true</code>
     *
     * @param raw The string containing the blockstate to parse
     *
     * @return The Blockstate object corresponding to the properties in the parameter.
     *
     * @throws IllegalArgumentException If the passed parameter is malformed and doesn't correspond to a blockstate.
     * */
    public static @NotNull Blockstate parseRaw(@NotNull String raw) {
        String[] states = raw.split(",");
        Blockstate blockstate = new Blockstate();
        for(String state : states) {
            String[] pair = state.split("=");
            if(pair.length != 2)
                throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Illegal format at '" + state + "' state for raw blockstate '" + raw + "'", state);
            blockstate.put(pair[0].trim(), pair[1].trim());
        }
        return blockstate;
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public @NotNull Blockstate clone() {
        Blockstate newState = new Blockstate();
        for(Map.Entry<String, String> entry : map.entrySet()) {
            newState.put(entry.getKey(), entry.getValue());
        }
        return newState;
    }

    public @NotNull Blockstate merge(@NotNull Blockstate other) {
        if(other.isEmpty()) return this.clone();
        if(this.isEmpty()) return other.clone();
        Blockstate merged = this.clone();
        for(Map.Entry<String, String> entry : other.map.entrySet()) {
            merged.put(entry.getKey(), entry.getValue());
        }
        return merged;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, String> entry = it.next();

            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            if(it.hasNext()) sb.append(',');
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Blockstate that = (Blockstate) o;

        return map.equals(that.map);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }
}
