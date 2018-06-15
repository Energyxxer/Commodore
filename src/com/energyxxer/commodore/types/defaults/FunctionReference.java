package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;

public class FunctionReference extends Type {
    public static final String CATEGORY = "function_reference";

    private Function function;

    public FunctionReference(Function function) {
        super("function_reference", function.getNamespace(), function.getPath());
        this.function = function;
    }

    protected FunctionReference(Namespace namespace, String path) {
        super(CATEGORY, namespace, path);
    }

    @Override
    public boolean isConcrete() {
        return false;
    }

    public Function getFunction() {
        return function;
    }
}
