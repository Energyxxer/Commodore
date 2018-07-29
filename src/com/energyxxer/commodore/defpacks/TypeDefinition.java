package com.energyxxer.commodore.defpacks;

class TypeDefinition {
    final String category;
    String tagDirectory;
    boolean useNamespace;
    String importFrom;

    public TypeDefinition(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "TypeDefinition{" +
                "category='" + category + '\'' +
                ", tagDirectory='" + tagDirectory + '\'' +
                ", useNamespace=" + useNamespace +
                ", importFrom='" + importFrom + '\'' +
                '}';
    }
}
