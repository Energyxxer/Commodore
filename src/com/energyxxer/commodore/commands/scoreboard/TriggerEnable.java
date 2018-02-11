package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.Objective;

public class TriggerEnable {
    private Entity player;
    private Objective objective;

    public TriggerEnable(Entity player, Objective objective) {
        this.player = player;
        this.objective = objective;
    }
}
