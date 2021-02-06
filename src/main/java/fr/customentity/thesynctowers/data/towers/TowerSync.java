package fr.customentity.thesynctowers.data.towers;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import fr.customentity.thesynctowers.TheSyncTowers;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TowerSync {

    private final Tower.Factory towerFactory;
    private final TheSyncTowers plugin;

    private String name;
    private List<Tower> towers;

    private transient RunningTowerSync runningTowerSync;

    @Inject
    public TowerSync(TheSyncTowers plugin, Tower.Factory towerFactory, @Assisted String name) {
        this.plugin = plugin;
        this.towerFactory = towerFactory;

        this.name = name;
        this.towers = new ArrayList<>();

        this.runningTowerSync = null;
    }

    public interface Factory {
        TowerSync create(String name);
    }

    public Optional<RunningTowerSync> getRunningTowerSync() {
        return Optional.ofNullable(runningTowerSync);
    }

    public boolean isRunning() {
        return this.getRunningTowerSync().isPresent();
    }

    public void addTower(Location location, Material material) {
        towers.add(towerFactory.create(location, material));
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
}
