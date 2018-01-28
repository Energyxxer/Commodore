package com.energyxxer.commodore.inspection;

import com.energyxxer.commodore.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;

public class ExecutionContext {
    private Entity originalSender;
    private ArrayList<ExecuteModifier> modifiers = new ArrayList<>();
    private Entity finalSender;

    public ExecutionContext(Entity sender) {
        this.originalSender = this.finalSender = sender;
    }

    public ExecutionContext(Entity originalSender, Collection<ExecuteModifier> modifiers) {
        this.originalSender = originalSender;
        this.modifiers.addAll(modifiers);
        updateFinalSender();
    }

    private void updateFinalSender() {
        for(int i = modifiers.size() - 1; i >= 0; i--) {
            ExecuteModifier modifier = modifiers.get(i);
            if(modifier.getNewSender() != null) {
                finalSender = modifier.getNewSender();
                return;
            }
        }
        finalSender = originalSender;
    }

    public Entity getOriginalSender() {
        return originalSender;
    }

    public Collection<ExecuteModifier> getModifiers() {
        return modifiers;
    }

    public Entity getFinalSender() {
        return finalSender;
    }
}
