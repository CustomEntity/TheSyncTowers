package fr.customentity.thesynctowers.tasks;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.SubCommandExecutor;
import fr.customentity.thesynctowers.data.EndReason;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.data.tower.Tower;
import fr.customentity.thesynctowers.scoreboard.ScoreboardManager;
import fr.customentity.thesynctowers.settings.Settings;
import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private final ScoreboardManager scoreboardManager;

    @Inject
    public RunningTowerSyncTask(TheSyncTowers plugin, ScoreboardManager scoreboardManager, @Assisted RunningTowerSync runningTowerSync) {
        this.plugin = plugin;
        this.scoreboardManager = scoreboardManager;

        this.runningTowerSync = runningTowerSync;
    }

    public interface Factory {
        RunningTowerSyncTask create(RunningTowerSync runningTowerSync);
    }

    @Override
    public void run() {
        TowerSync towerSync = runningTowerSync.getTowerSync();


        boolean isScoreboardEnabled = Settings.SCOREBOARD_ENABLED.getValue();
        boolean isCheckingDistance = Settings.SCOREBOARD_CHECK_DISTANCE_TO_APPLY.getValue();

        if (isScoreboardEnabled) {
            if (isCheckingDistance) {
                int distanceToApply = Settings.SCOREBOARD_DISTANCE_TO_APPLY_VALUE.getValue();

                for (Player player : Bukkit.getOnlinePlayers()) {
                    Optional<Tower> nearestTower = towerSync.getTowers().stream()
                            .filter(tower -> Objects.equals(tower.getLocation().getWorld(), player.getWorld()) &&
                                    tower.getLocation().distance(player.getLocation()) <= distanceToApply)
                            .findFirst();

                    if(nearestTower.isPresent()) {
                        this.scoreboardManager.applyScoreboard(runningTowerSync, player);
                    } else {
                        this.scoreboardManager.removeScoreboard(player);
                    }
                }
            } else {
                for (Player player : Bukkit.getOnlinePlayers()) this.scoreboardManager.applyScoreboard(runningTowerSync, player);

            }
        }

        runningTowerSync.setTimeBeforeEnd(runningTowerSync.getTimeBeforeEnd() - 1);
        if (runningTowerSync.getTimeBeforeEnd() == 0) {
            towerSync.stop(EndReason.TIMEUP);
        }
    }
}
