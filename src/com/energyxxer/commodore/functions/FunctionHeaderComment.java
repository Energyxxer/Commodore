package com.energyxxer.commodore.functions;

import java.util.Iterator;

public class FunctionHeaderComment extends FunctionComment {

    public FunctionHeaderComment(String... lines) {
        super(lines);
    }

    @Override
    public String toFunctionContent(Function function) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append("#\n");

        Iterator<String> it = lines.iterator();
        while(it.hasNext()) {
            sb.append("# ");
            sb.append(it.next());
            sb.append('\n');
        }
        sb.append("#\n");
        return sb.toString();
    }
}
