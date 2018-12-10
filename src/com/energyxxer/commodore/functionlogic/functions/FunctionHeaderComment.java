package com.energyxxer.commodore.functionlogic.functions;

import org.jetbrains.annotations.NotNull;

public class FunctionHeaderComment extends FunctionComment {

    public FunctionHeaderComment(@NotNull String... lines) {
        super(lines);
    }

    @NotNull
    @Override
    public String toFunctionContent(@NotNull FunctionSection section) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append("#\n");

        for(String line : lines) {
            sb.append("# ");
            sb.append(line);
            sb.append('\n');
        }

        sb.append("#\n");
        return sb.toString();
    }
}
