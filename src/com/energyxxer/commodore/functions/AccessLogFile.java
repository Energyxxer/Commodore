package com.energyxxer.commodore.functions;

import com.energyxxer.commodore.module.Exportable;

public class AccessLogFile implements Exportable {
    private final Function function;

    public AccessLogFile(Function function) {
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
