package fr.customentity.thesynctowers.tasks;

import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.participant.IParticipant;
import fr.customentity.thesynctowers.data.tower.Tower;
import fr.customentity.thesynctowers.locale.Tl;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;


/**
 * Copyright (c) 2021. By CustomEntity
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * @Author: CustomEntity
 * @Date: 18/02/2021
 */

public class SynchronizationTask extends BukkitRunnable {

    private final IParticipant participant;
    private final RunningTowerSync runningTowerSync;
    private final long firstBreakTime;

    public SynchronizationTask(IParticipant participant, RunningTowerSync runningTowerSync, long firstBreakTime) {
        this.participant = participant;
        this.runningTowerSync = runningTowerSync;
        this.firstBreakTime = firstBreakTime;
    }

    @Override
    public void run() {
        if (runningTowerSync == null) {
            this.cancel();
            return;
        }
        Map<Tower, Long> rowMap = runningTowerSync.getParticipantsTowerTable().row(participant);
        int interval = runningTowerSync.getTowerSync().getTimeInterval();
        if (this.firstBreakTime + interval <= System.currentTimeMillis()) {
            Tl.sendConfigMessageToPlayers(participant.getPlayerParticipants(), Tl.GAME_SYNCHRONIZATION_FAILED);
            rowMap.clear();
            runningTowerSync.getSynchronizationTaskMap().remove(participant);
            this.cancel();
        } else {
            Tl.sendConfigMessageToPlayers(participant.getPlayerParticipants(), Tl.GAME_SYNCHRONIZATION_PROGRESSION,
                    "%current%", rowMap.size() + "",
                    "%goal%", runningTowerSync.getTowerSync().getTowers().size() + "",
                    "%time%", String.format("%.3f", (double)(this.firstBreakTime + interval - System.currentTimeMillis()) / 1000)
            );
        }

    }
}
