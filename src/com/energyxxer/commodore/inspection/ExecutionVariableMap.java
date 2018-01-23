package com.energyxxer.commodore.inspection;

import java.util.*;

public class ExecutionVariableMap {
    private HashMap<ExecutionVariable, Boolean> variables = new HashMap<>();

    public ExecutionVariableMap(ExecutionVariable... usedVariables) {
        List<ExecutionVariable> usedVariablesList = Arrays.asList(usedVariables);
        for(ExecutionVariable variable : ExecutionVariable.values()) {
            variables.put(variable, usedVariablesList.contains(variable));
        }
    }

    public ExecutionVariableMap(ExecutionVariableMap map0, ExecutionVariableMap map1) {
        for(ExecutionVariable variable : ExecutionVariable.values()) {
            variables.put(variable, map0.isUsed(variable) || map1.isUsed(variable));
        }
    }

    public boolean isUsed(ExecutionVariable variable) {
        return variables.get(variable);
    }

    public void setUsed(ExecutionVariable variable) {
        setUsed(variable, true);
    }

    public void setUsed(ExecutionVariable variable, boolean used) {
        variables.put(variable, used);
    }

    public void merge(ExecutionVariableMap other) {
        if(other == null) return;
        for(ExecutionVariable variable : ExecutionVariable.values()) {
            variables.put(variable, this.isUsed(variable) || other.isUsed(variable));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        Iterator<Map.Entry<ExecutionVariable, Boolean>> it = variables.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<ExecutionVariable, Boolean> entry = it.next();
            if(entry.getValue()) sb.append(entry.getKey());
            if(it.hasNext()) sb.append(", ");
        }

        sb.append("}");

        return sb.toString();
    }
}
