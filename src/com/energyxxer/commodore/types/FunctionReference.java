package com.energyxxer.commodore.types;

import com.energyxxer.commodore.functions.Function;

public class FunctionReference extends Type {

    private Function function;

    public FunctionReference(Function function) {
        super(function.getNamespace(), function.getPath());
        this.function = function;
    }

    protected FunctionReference(String namespace, String path) {
        super(namespace, path);
    }

    @Override
    public boolean isConcrete() {
        return false;
    }


}
