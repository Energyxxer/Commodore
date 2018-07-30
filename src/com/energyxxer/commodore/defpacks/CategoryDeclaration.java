package com.energyxxer.commodore.defpacks;

class CategoryDeclaration {
    final String category;
    String tagDirectory;
    boolean useNamespace;
    String importFrom;

    public CategoryDeclaration(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryDeclaration{" +
                "category='" + category + '\'' +
                ", tagDirectory='" + tagDirectory + '\'' +
                ", useNamespace=" + useNamespace +
                ", importFrom='" + importFrom + '\'' +
                '}';
    }
}
