package com.energyxxer.commodore.functions;

public class FunctionHeaderComment extends FunctionComment {

    public FunctionHeaderComment(String... lines) {
        super(lines);
    }

    @Override
    public String toFunctionContent(Function function) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append("#\n");

        for (String line : lines) {
            sb.append("# ");
            sb.append(line);
            sb.append('\n');
        }

        sb.append("#\n");
        return sb.toString();
    }
}
