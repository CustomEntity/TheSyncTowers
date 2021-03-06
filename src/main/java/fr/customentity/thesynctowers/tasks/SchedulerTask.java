package fr.customentity.thesynctowers.tasks;

import com.google.inject.Inject;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.data.TowerSyncManager;
import fr.customentity.thesynctowers.scheduler.Scheduler;
import fr.customentity.thesynctowers.scheduler.SchedulerManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

public class SchedulerTask extends BukkitRunnable {

    @Inject private TheSyncTowers plugin;
    @Inject private SchedulerManager schedulerManager;
    @Inject private TowerSyncManager towerSyncManager;

    @Override
    public void run() {
        for (Scheduler scheduler : this.schedulerManager.getSchedulers()) {
            if (scheduler.canStart()) {
                Optional<TowerSync> towerSync = this.towerSyncManager.getTowerSyncByName(scheduler.getTowersync());
                towerSync.ifPresent(sync -> Bukkit.getScheduler().runTask(plugin, () -> sync.start(false)));
            }
        }
    }
}
