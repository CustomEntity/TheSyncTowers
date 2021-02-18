package fr.customentity.thesynctowers.tasks;

import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.EndReason;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.TowerSync;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

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
public class RunningTowerSyncTask extends BukkitRunnable {

    private final TheSyncTowers plugin;
    private final RunningTowerSync runningTowerSync;

    public RunningTowerSyncTask(TheSyncTowers plugin, RunningTowerSync runningTowerSync) {
        this.plugin = plugin;
        this.runningTowerSync = runningTowerSync;

        if (runningTowerSync.getTowerSync().getType() == TowerSync.Type.TIME)
            runningTowerSync.setTimeBeforeEnd((int) runningTowerSync.getTowerSync().getValueToWin());
    }

    @Override
    public void run() {
        TowerSync towerSync = runningTowerSync.getTowerSync();

        runningTowerSync.setTimeBeforeEnd(runningTowerSync.getTimeBeforeEnd() - 1);
        if (runningTowerSync.getTimeBeforeEnd() == 0) {
            towerSync.stop(EndReason.TIMEUP);
        }
    }
}
