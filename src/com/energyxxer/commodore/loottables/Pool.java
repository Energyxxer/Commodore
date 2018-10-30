package com.energyxxer.commodore.loottables;

import com.energyxxer.commodore.CommandUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collection;

public class Pool {
    private int minRolls = 1;
    private int maxRolls = 1;
    private int minBonusRolls = 0;
    private int maxBonusRolls = 0;
    private ArrayList<LootEntry> entries = new ArrayList<>();

    public JsonObject construct() {
        JsonObject object = new JsonObject();
        object.add("rolls", CommandUtils.constructRange(minRolls, maxRolls));
        if(minBonusRolls != 0 && maxBonusRolls != 0) {
            object.add("bonus_rolls", CommandUtils.constructRange(minBonusRolls, maxBonusRolls));
        }
        JsonArray entryList = new JsonArray();
        object.add("entries", entryList);
        for(LootEntry entry : entries) {
            entryList.add(entry.construct());
        }
        return object;
    }

    public int getMinRolls() {
        return minRolls;
    }

    public void setMinRolls(int minRolls) {
        this.minRolls = minRolls;
    }

    public int getMaxRolls() {
        return maxRolls;
    }

    public void setMaxRolls(int maxRolls) {
        this.maxRolls = maxRolls;
    }

    public void setRolls(int rolls) {
        setMinRolls(rolls);
        setMaxRolls(rolls);
    }

    public int getMinBonusRolls() {
        return minBonusRolls;
    }

    public void setMinBonusRolls(int minBonusRolls) {
        this.minBonusRolls = minBonusRolls;
    }

    public int getMaxBonusRolls() {
        return maxBonusRolls;
    }

    public void setMaxBonusRolls(int maxBonusRolls) {
        this.maxBonusRolls = maxBonusRolls;
    }

    public void setBonusRolls(int bonusRolls) {
        setMinBonusRolls(bonusRolls);
        setMaxBonusRolls(bonusRolls);
    }

    public void addEntry(LootEntry entry) {
        entries.add(entry);
    }

    public Collection<LootEntry> getEntries() {
        return entries;
    }

    public void addAllEntries(Collection<LootEntry> entries) {
        this.entries.addAll(entries);
    }

    public void clearEntries() {
        entries.clear();
    }
}
