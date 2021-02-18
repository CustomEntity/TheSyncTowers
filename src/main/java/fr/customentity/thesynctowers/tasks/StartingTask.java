package fr.customentity.thesynctowers.tasks;

import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.settings.Settings;
import fr.customentity.thesynctowers.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

/**
 *  Copyright (c) 2021. By CustomEntity
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * @Author: CustomEntity
 * @Date: 18/02/2021
 *
 */
public class StartingTask extends BukkitRunnable {

    private final RunningTowerSync runningTowerSync;
    private int timeRemaining;
    private final List<Integer> messages;

    public StartingTask(RunningTowerSync runningTowerSync) {
        this.runningTowerSync = runningTowerSync;

        this.timeRemaining = Settings.START_COOLDOWN_IN_SECOND.getValue();
        this.messages = Settings.START_COOLDOWN_MESSAGES_IN_SECOND.getValue();
    }

    @Override
    public void run() {
        if (this.messages.contains(this.timeRemaining)) {
            Bukkit.getOnlinePlayers().forEach(player ->
                    Tl.sendConfigMessage(player, Tl.GAME_COOLDOWN_MESSAGE, this.runningTowerSync, "%minutes%",
                            TimeUtils.minutesFromSeconds(timeRemaining) + "", "%seconds%", TimeUtils.secondsFromSeconds(timeRemaining) + ""));
        }
        if (timeRemaining == 0) {
            this.runningTowerSync.start();
            cancel();
        }
        timeRemaining--;
    }
}
