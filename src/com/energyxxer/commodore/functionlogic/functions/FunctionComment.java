package com.energyxxer.commodore.functionlogic.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class FunctionComment implements FunctionWriter {
    @NotNull
    protected final Collection<@NotNull String> lines;

    public FunctionComment(String... lines) {
        this.lines = Arrays.asList(lines);
    }

    @NotNull
    @Override
    public String toFunctionContent(@NotNull FunctionSection section) {
        StringBuilder sb = new StringBuilder();

        Iterator<String> it = lines.iterator();
        while(it.hasNext()) {
            sb.append("#");
            sb.append(it.next());
            if(it.hasNext()) sb.append('\n');
        }
        return sb.toString();
    }
}
