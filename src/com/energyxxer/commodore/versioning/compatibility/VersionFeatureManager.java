package com.energyxxer.commodore.versioning.compatibility;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.module.settings.ModuleSettingsManager;
import com.energyxxer.commodore.versioning.BedrockEditionVersion;
import com.energyxxer.commodore.versioning.JavaEditionVersion;
import com.energyxxer.commodore.versioning.Version;

import java.util.HashMap;

public class VersionFeatureManager {
    private static final HashMap<Version, VersionFeatures> featureMap = new HashMap<>();

    public static VersionFeatures getFeaturesForVersion(Version version) {
        return featureMap.get(version);
    }

    static {
        VersionFeatures j1_13f = new VersionFeatures();
        j1_13f.put("objectives.max_length", 16);
        j1_13f.put("identifiers.regex", "[A-Za-z0-9_.\\-+]*");
        j1_13f.put("identifiers.accept_strings", false);
        j1_13f.put("objectives.regex", "[A-Za-z0-9_.\\-+]*");
        j1_13f.put("objectives.accept_strings", false);
        j1_13f.put("player_names.regex", "[A-Za-z0-9_.\\-+]*");
        j1_13f.put("player_names.accept_strings", true);
        j1_13f.put("score_holders.regex", "[^ @][^ ]*");
        j1_13f.put("score_holders.accept_strings", false);
        j1_13f.put("game_profiles.regex", "[^ @][^ ]*");
        j1_13f.put("game_profiles.accept_strings", false);
        j1_13f.put("ips.regex", "[A-Za-z0-9_.\\-+]*");
        j1_13f.put("ips.accept_strings", false);
        j1_13f.put("function.namespace_separator", ":");
        j1_13f.put("data_packs", true);
        j1_13f.put("functions", true);
        j1_13f.put("advancements", true);
        j1_13f.put("bossbars", true);
        j1_13f.put("nbt.access", true);
        j1_13f.put("block.data_values", false);
        j1_13f.put("block.blockstates", true);
        j1_13f.put("item.data_values", false);
        j1_13f.put("scoreboard.criteria", true);
        j1_13f.put("execute_modifiers", true);
        j1_13f.put("command.schedule", true);
        j1_13f.put("command.data_modify", false);
        j1_13f.put("command.scoreboard_random", false);
        j1_13f.put("command.scoreboard_test", false);
        j1_13f.put("command.scoreboard_get", true);
        j1_13f.put("command.scoreboard_display_sort", false);
        j1_13f.put("command.trigger", true);
        j1_13f.put("command.kick", false);
        j1_13f.put("server_commands", false);
        j1_13f.put("command.forceload", false);
        j1_13f.put("command.tickingarea", false);
        j1_13f.put("command.effect.explicit", true);
        j1_13f.put("command.weather_query", false);
        j1_13f.put("add.entity", false);
        j1_13f.put("add.particle", false);

        featureMap.put(new JavaEditionVersion(1, 13, 0), j1_13f);
        featureMap.put(new JavaEditionVersion(1, 13, 1), j1_13f);
        featureMap.put(new JavaEditionVersion(1, 13, 2), j1_13f);

        VersionFeatures j1_14f = j1_13f.duplicate();
        j1_14f.put("command.teammsg", true);
        j1_14f.put("command.datapack", true);
        j1_14f.put("command.reload", true);
        j1_14f.put("command.debug", true);
        j1_14f.put("command.forceload", true);
        j1_14f.put("command.kick", true);
        j1_14f.put("server_commands", true);
        j1_14f.put("command.data_modify", true);

        VersionFeatures j1_15f = j1_14f.duplicate();
        j1_15f.put("command.data.storage", true);
        j1_15f.put("command.schedule_clear", true);
        j1_15f.put("command.schedule_function_append", true);

        featureMap.put(new JavaEditionVersion(1, 14, 0), j1_14f);
        featureMap.put(new JavaEditionVersion(1, 14, 1), j1_14f);
        featureMap.put(new JavaEditionVersion(1, 14, 2), j1_14f);
        featureMap.put(new JavaEditionVersion(1, 15, 0), j1_15f);

        VersionFeatures b1_11f = new VersionFeatures();
        b1_11f.put("objectives.max_length", 16);
        b1_11f.put("identifiers.regex", "[A-Za-z0-9()_§#\\-.]+");
        b1_11f.put("identifiers.accept_strings", true);
        b1_11f.put("objectives.regex", "[A-Za-z0-9()_§#\\-.]+");
        b1_11f.put("objectives.accept_strings", true);
        b1_11f.put("player_names.regex", "[A-Za-z0-9()_§#\\-.]+");
        b1_11f.put("player_names.accept_strings", true);
        b1_11f.put("score_holders.regex", "[A-Za-z0-9()_§#\\-.]+");
        b1_11f.put("score_holders.accept_strings", true);
        b1_11f.put("function.namespace_separator", "/");
        b1_11f.put("data_packs", false);
        b1_11f.put("functions", true);
        b1_11f.put("advancements", false);
        b1_11f.put("bossbars", false);
        b1_11f.put("block.data_values", true);
        b1_11f.put("block.blockstates", false);
        b1_11f.put("item.data_values", true);
        b1_11f.put("nbt.access", false);
        b1_11f.put("scoreboard.criteria", false);
        b1_11f.put("execute_modifiers", false);
        j1_14f.put("command.teammsg", false);
        b1_11f.put("command.data_modify", false);
        b1_11f.put("command.scoreboard_random", true);
        b1_11f.put("command.scoreboard_test", true);
        b1_11f.put("command.scoreboard_get", false);
        b1_11f.put("command.scoreboard_display_sort", true);
        b1_11f.put("command.trigger", false);
        b1_11f.put("command.kick", false);
        b1_11f.put("command.forceload", false);
        b1_11f.put("command.tickingarea", true);
        b1_11f.put("command.effect.explicit", false);
        b1_11f.put("command.weather_query", true);
        b1_11f.put("add.entity", true);
        b1_11f.put("add.particle", true);

        featureMap.put(new BedrockEditionVersion(1, 11, 0), b1_11f);
        featureMap.put(new BedrockEditionVersion(1, 11, 1), b1_11f);
        featureMap.put(new BedrockEditionVersion(1, 11, 2), b1_11f);

    }

    public static void assertEnabled(String key) {
        if (ModuleSettingsManager.getActive() == null) return;
        VersionFeatures features = getFeaturesForVersion(ModuleSettingsManager.getActive().getTargetVersion());
        if (features == null || !features.getBoolean(key)) {
            throw new CommodoreException(CommodoreException.Source.VERSION_ERROR, "The feature for the key '" + key + "' is not compatible with " + ModuleSettingsManager.getActive().getTargetVersion());
        }
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        if (ModuleSettingsManager.getActive() == null) {
            return defaultValue;
        }
        VersionFeatures features = getFeaturesForVersion(ModuleSettingsManager.getActive().getTargetVersion());
        if (features != null) {
            return features.getBoolean(key, defaultValue);
        } else return defaultValue;
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        if (ModuleSettingsManager.getActive() == null) {
            return defaultValue;
        }
        VersionFeatures features = getFeaturesForVersion(ModuleSettingsManager.getActive().getTargetVersion());
        if (features != null) {
            return features.getInt(key, defaultValue);
        } else return defaultValue;
    }

    public static String getString(String key) {
        return getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        if (ModuleSettingsManager.getActive() == null) {
            return defaultValue;
        }
        VersionFeatures features = getFeaturesForVersion(ModuleSettingsManager.getActive().getTargetVersion());
        if (features != null) {
            return features.getString(key, defaultValue);
        } else return defaultValue;
    }
}
