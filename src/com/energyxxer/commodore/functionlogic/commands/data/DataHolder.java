package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;

public interface DataHolder {

    DataHolder STORAGE = new DataHolder() {
        @Override
        public String resolve() {
            return "storage";
        }

        @Override
        public void assertSingle() {

        }

        @Override
        public void assertAvailable() {
            VersionFeatureManager.assertEnabled("command.data.storage");
        }
    };


    String resolve();

    void assertSingle();

    void assertAvailable();
}
