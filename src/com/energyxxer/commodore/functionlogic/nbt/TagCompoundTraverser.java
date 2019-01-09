package com.energyxxer.commodore.functionlogic.nbt;

import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPathIndex;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPathKey;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPathNode;

import java.util.ArrayList;
import java.util.Stack;

public class TagCompoundTraverser {
    private final Stack<ComplexNBTTag> tagStack = new Stack<>();
    private final Stack<Integer> indexStack = new Stack<>();

    public TagCompoundTraverser(ComplexNBTTag parent) {
        tagStack.push(parent);
        indexStack.push(0);
    }

    public PathContents next() {
        while(!indexStack.isEmpty() && indexStack.peek() >= tagStack.peek().size()) {
            tagStack.pop();
            indexStack.pop();
        }
        if(indexStack.isEmpty()) return null;
        if(indexStack.peek() < tagStack.peek().size()) {
            NBTTag next = tagStack.peek().getAllTags().get(indexStack.peek());
            NBTPath path = getPathInStack(next);

            indexStack.push(indexStack.pop()+1);

            if(next instanceof ComplexNBTTag) {
                tagStack.push(((ComplexNBTTag) next));
                indexStack.push(0);
            }

            return new PathContents(path, next);
        }
        return null;
    }

    public void skipThisNode() {
        if(indexStack.isEmpty()) return;
        if(indexStack.peek() == 0) {
            tagStack.pop();
            indexStack.pop();
        }
    }

    private NBTPath getPathInStack(NBTTag leaf) {
        ArrayList<NBTPathNode> nodes = new ArrayList<>();

        for(int i = 0; i < tagStack.size(); i++) {
            ComplexNBTTag parent = tagStack.get(i);
            NBTTag tag = (i < tagStack.size()-1) ? tagStack.get(i+1) : leaf;
            int index = indexStack.get(i);
            if(i < tagStack.size() -1) index--;

            nodes.add(getNodeFor(parent, tag, index));
        }

        return new NBTPath(nodes.toArray(new NBTPathNode[0]));
    }

    private NBTPathNode getNodeFor(ComplexNBTTag parent, NBTTag tag, int index) {
        if(parent instanceof TagCompound) {
            return new NBTPathKey(tag.name);
        } else if(parent instanceof TagList || parent instanceof ArrayNBTTag) {
            return new NBTPathIndex(index);
        }
        throw new IllegalArgumentException("Unrecognized ComplexNBTTag subclass '" + parent.getClass().getSimpleName() + "' for parameter 'parent'");
    }

    public static class PathContents {
        private final NBTPath path;
        private final NBTTag value;

        public PathContents(NBTPath path, NBTTag value) {
            this.path = path;
            this.value = value;
        }

        public NBTPath getPath() {
            return path;
        }

        public NBTTag getValue() {
            return value;
        }

        @Override
        public String toString() {
            return path + " = " + value.toHeadlessString();
        }
    }
}
