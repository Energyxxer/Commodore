package com.energyxxer.commodore.versioning.compatibility;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.module.settings.ModuleSettingsManager;
import com.energyxxer.commodore.util.io.CompoundInput;
import com.energyxxer.commodore.versioning.Version;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class VersionFeatureManager {
    private static final ArrayList<VersionFeatures> defaultFeatureMaps = new ArrayList<>();
    private static final ArrayList<VersionFeatures> featureMaps = new ArrayList<>();

    public static VersionFeatures getFeaturesForVersion(Version version) {
        for(VersionFeatures feat : featureMaps) {
            if (version.getEditionString().equalsIgnoreCase(feat.getEdition()) && version.getVersionString().matches(feat.getVersionRegex())) {
                return feat;
            }
        }
        return null;
    }

    static {
        CompoundInput source = CompoundInput.Static.chooseInputForClasspath("/featuremaps/", VersionFeatureManager.class);
        try {
            InputStream in = source.get("/");
            if (in != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String filename;
                while ((filename = br.readLine()) != null) {
                    loadFeatureMap(new InputStreamReader(source.get(filename)), true);
                }

                br.close();
            }
        } catch(IOException x) {
            x.printStackTrace();
        }
    }

    //Load

    public static void loadFeatureMap(Reader reader) {
        loadFeatureMap(reader, false);
    }

    public static void clearLoadedFeatures() {
        featureMaps.clear();
        featureMaps.addAll(defaultFeatureMaps);
    }

    private static void loadFeatureMap(Reader reader, boolean isDefault) {
        Gson gson = new Gson();
        JsonObject root = gson.fromJson(reader, JsonObject.class);

        String edition = root.get("edition").getAsString();
        String applicableVersion = root.get("applicable_version").getAsString();

        String versionRegex = applicableVersion.replace(".","\\.").replace("*","\\d+");

        VersionFeatures feat = new VersionFeatures(edition, versionRegex);

        JsonObject features = root.getAsJsonObject("features");
        for(Map.Entry<String, JsonElement> entry : features.entrySet()) {
            JsonPrimitive primitive = entry.getValue().getAsJsonPrimitive();
            Object value = null;
            if(primitive.isString()) value = primitive.getAsString();
            if(primitive.isBoolean()) value = primitive.getAsBoolean();
            if(primitive.isNumber()) {
                if(primitive.getAsInt() != primitive.getAsDouble()) {
                    value = primitive.getAsDouble();
                } else {
                    value = primitive.getAsInt();
                }
            }
            if(value != null) {
                feat.put(entry.getKey(), value);
            }
        }

        if(isDefault) {
            defaultFeatureMaps.add(0, feat);
        }
        featureMaps.add(0, feat);
    }

    //Query

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
