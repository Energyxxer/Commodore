package com.energyxxer.commodore.functionlogic.functions;

import com.energyxxer.commodore.module.Exportable;
import org.jetbrains.annotations.NotNull;

public class AccessLogFile implements Exportable {
    @NotNull
    private final Function function;

    public AccessLogFile(@NotNull Function function) {
        this.function = function;
    }

    @Override
    public boolean shouldExport() {
        return true;
    }

    @Override
    public void setExport(boolean export) {
    }

    @Override
    public String getExportPath() {
        return "functions/" + function.getPath() + ".accesslog";
    }

    @Override
    public String getContents() {
        return function.getAccessLog().toString();
    }
}
