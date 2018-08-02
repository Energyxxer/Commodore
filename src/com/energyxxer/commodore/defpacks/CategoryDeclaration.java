package com.energyxxer.commodore.defpacks;

/**
 * Represents a category declaration extracted from the pack.json of a definition pack.
 * */
class CategoryDeclaration {
    /**
     * The category this declaration refers to.
     * */
    final String category;
    /**
     * The directory name inside the <code><i>namespace/</i>tags/</code> directory in which the declaration specifies
     * that tags of this category should be imported and exported. May be <code>null</code> if not defined.
     * */
    String tagDirectory;
    /**
     * Whether types of this category should use the namespace prefix when printed onto a command.
     * */
    boolean useNamespace;
    /**
     * The path to the file from inside the definition pack root which contains the list of definitions for each type
     * of this category, excluding the <code>.json</code> extension. May be <code>null</code> if not defined.
     * */
    String importFrom;

    /**
     * Creates a category declaration object for the specified category.
     * */
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
