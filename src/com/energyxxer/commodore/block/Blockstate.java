package com.energyxxer.commodore.block;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Blockstate {
    private HashMap<String, String> map = new HashMap<>();

    public Blockstate() {
    }

    public void put(String key, String value) {
        map.put(key, value);
    }

    public static Blockstate parseRaw(String raw) {
        String[] states = raw.split(",");
        Blockstate blockstate = new Blockstate();
        for(String state : states) {
            String[] pair = state.split("=");
            if(pair.length != 2) throw new IllegalArgumentException("Illegal format at '" + state + "' state for raw blockstate '" + raw + "'");
            blockstate.put(pair[0], pair[1]);
        }
        return blockstate;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blockstate that = (Blockstate) o;

        return map.equals(that.map);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }
}
