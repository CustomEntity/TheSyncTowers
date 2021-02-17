package fr.customentity.thesynctowers.tasks;

import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import org.bukkit.scheduler.BukkitRunnable;


public class RunningTowerSyncTask extends BukkitRunnable {

    private final TheSyncTowers plugin;
    private final RunningTowerSync runningTowerSync;

    public RunningTowerSyncTask(TheSyncTowers plugin, RunningTowerSync runningTowerSync) {
        this.plugin = plugin;
        this.runningTowerSync = runningTowerSync;
    }

    @Override
    public void run() {

    }
}
