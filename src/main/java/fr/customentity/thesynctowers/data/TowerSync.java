package fr.customentity.thesynctowers.data;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.tower.Tower;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TowerSync {


    private String name;
    private List<Tower> towers;
    private int timeBeforeEnd;

    private transient final TheSyncTowers plugin;
    private transient final Tower.Factory towerFactory;
    private transient final RunningTowerSync.Factory runningTowerSyncFactory;
    private transient RunningTowerSync runningTowerSync;

    @Inject
    public TowerSync(TheSyncTowers plugin,
                     Tower.Factory towerFactory,
                     RunningTowerSync.Factory runningTowerSyncFactory,
                     @Assisted String name) {
        this.plugin = plugin;
        this.towerFactory = towerFactory;
        this.runningTowerSyncFactory = runningTowerSyncFactory;

        this.name = name;
        this.towers = new ArrayList<>();

        this.runningTowerSync = null;
    }

    public interface Factory {
        TowerSync create(String name);
    }

    public int getTimeBeforeEnd() {
        return timeBeforeEnd;
    }

    public void setTimeBeforeEnd(int timeBeforeEnd) {
        this.timeBeforeEnd = timeBeforeEnd;
    }

    public Optional<RunningTowerSync> getRunningTowerSync() {
        return Optional.ofNullable(runningTowerSync);
    }

    public boolean isRunning() {
        return this.getRunningTowerSync().isPresent();
    }

    public void addTower(Location location, Material material) {
        towers.add(towerFactory.create(this, location, material));
    }

    public void removeTower(int id) {
        towers.remove(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public void start(boolean now) {
        if (this.isRunning()) return;
        this.runningTowerSync = runningTowerSyncFactory.create(this, now);

    }
}
