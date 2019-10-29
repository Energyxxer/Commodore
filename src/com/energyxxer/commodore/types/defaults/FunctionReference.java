package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;

public class FunctionReference extends Type {
    public static final String CATEGORY = "function_reference";

    private Function function;

    public FunctionReference(Function function) {
        super(CATEGORY, function.getNamespace(), function.getPath());
        this.function = function;
    }

    public FunctionReference(Namespace namespace, String path) {
        super(CATEGORY, namespace, path);
        if(namespace.functions.exists(path)) this.function = namespace.functions.get(path);
    }

    @Override
    public boolean isStandalone() {
        return true;
    }

    public Function getFunction() {
        return function;
    }
}
