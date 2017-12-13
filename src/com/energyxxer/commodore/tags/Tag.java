package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.types.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public interface Tag<T extends Type> {

    enum OverridePolicy {
        REPLACE(true), APPEND(false);

        boolean valueBool;

        public static final OverridePolicy DEFAULT_POLICY = APPEND;

        OverridePolicy(boolean valueBool) {
            this.valueBool = valueBool;
        }
    }

    ArrayList<T> getValues();
    default String getJSONContent() {
        StringBuilder sb = new StringBuilder("{\n\t\"values\":[");

        Iterator<T> it = getValues().iterator();

        while(it.hasNext()) {
            sb.append("\n\t\t\"");
            sb.append(CommandUtils.escape(it.next().toString()));
            sb.append("\"");
            if(it.hasNext()) sb.append(",");
        }

        sb.append("\n\t]");
        if(this.getOverridePolicy() != OverridePolicy.DEFAULT_POLICY) {
            sb.append(",\n\t\"replace\": ");
            sb.append(this.getOverridePolicy().valueBool);
            sb.append('"');
        }
        sb.append("\n}");

        return sb.toString();
    }

    OverridePolicy getOverridePolicy();
    void setOverridePolicy(OverridePolicy newPolicy);
}
