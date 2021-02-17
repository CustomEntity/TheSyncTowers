package fr.customentity.thesynctowers.tasks;

import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.settings.Settings;
import fr.customentity.thesynctowers.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

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
