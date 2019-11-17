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
    private static final ThreadLocal<VersionFeatures> activeFeatureMap = new ThreadLocal<>();

    public static VersionFeatures getFeaturesForVersion(Version version) {
        if(version == null) return null;
        for(VersionFeatures feat : featureMaps) {
            if (version.getEditionString().equalsIgnoreCase(feat.getEdition()) && version.getVersionString().matches(feat.getVersionRegex())) {
                return feat;
            }
        }
        return null;
    }

    static {
        try {
            loadDefaultFeatureMaps();
        } catch (IOException x) {
            System.err.println("Commodore ERROR: While loading default feature maps:");
            x.printStackTrace();
        }
    }

    private static void loadDefaultFeatureMaps() throws IOException {
        CompoundInput source = CompoundInput.Static.chooseInputForClasspath("/featuremaps/", VersionFeatureManager.class);
        source.open();
        InputStream in = source.get("");
        if (in != null) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String filename;
                while ((filename = br.readLine()) != null) {
                    VersionFeatures feat = parseFeatureMap(new InputStreamReader(source.get(filename)));
                    featureMaps.add(0, feat);
                    defaultFeatureMaps.add(0, feat);
                }
            }
        }
        source.close();
    }

    //Load

    public static void loadFeatureMap(Reader reader) {
        VersionFeatures feat = parseFeatureMap(reader);
        featureMaps.add(0, feat);
    }

    public static void setActiveFeatureMap(Reader reader) {
        activeFeatureMap.set(parseFeatureMap(reader));
    }

    public static void setActiveFeatureMap(VersionFeatures features) {
        activeFeatureMap.set(features);
    }

    public static void clearActiveFeatureMap() {
        activeFeatureMap.set(null);
    }

    private static VersionFeatures getActiveFeatures() {
        if(activeFeatureMap.get() != null) return activeFeatureMap.get();
        return getFeaturesForVersion(ModuleSettingsManager.getActive().getTargetVersion());
    }

    public static void clearLoadedFeatures() {
        featureMaps.clear();
        featureMaps.addAll(defaultFeatureMaps);
    }

    public static VersionFeatures parseFeatureMap(Reader reader) {
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

        return feat;
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
        VersionFeatures features = getActiveFeatures();
        if (features != null) {
            return features.getBoolean(key, defaultValue);
        } else return defaultValue;
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        VersionFeatures features = getActiveFeatures();
        if (features != null) {
            return features.getInt(key, defaultValue);
        } else return defaultValue;
    }

    public static String getString(String key) {
        return getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        VersionFeatures features = getActiveFeatures();
        if (features != null) {
            return features.getString(key, defaultValue);
        } else return defaultValue;
    }
}
