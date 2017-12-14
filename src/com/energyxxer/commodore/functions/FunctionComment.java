package com.energyxxer.commodore.functions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class FunctionComment implements FunctionWriter {

    protected Collection<String> lines;

    protected FunctionComment() {
    }

    public FunctionComment(String... lines) {
        this.lines = Arrays.asList(lines);
    }

    @Override
    public String toFunctionContent(Function function) {
        StringBuilder sb = new StringBuilder();

        Iterator<String> it = lines.iterator();
        while (it.hasNext()) {
            sb.append("# ");
            sb.append(it.next());
            if (it.hasNext()) sb.append('\n');
        }
        return sb.toString();
    }
}
