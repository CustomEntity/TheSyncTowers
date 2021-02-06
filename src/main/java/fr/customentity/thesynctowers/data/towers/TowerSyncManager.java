package fr.customentity.thesynctowers.data.towers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import org.bukkit.Location;

import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class TowerSyncManager {

    private TheSyncTowers plugin;
    private Set<TowerSync> towerSyncs;

    private TowerSync.Factory towerSyncFactory;

    @Inject
    public TowerSyncManager(TheSyncTowers plugin, TowerSync.Factory towerSyncFactory) {
        this.plugin = plugin;
        this.towerSyncs = new HashSet<>();

        this.towerSyncFactory = towerSyncFactory;
    }

    public boolean isTowerBlockAtLocation(Location location) {
        return this.getRunningTowerSyncs().stream()
                .anyMatch(towerSync -> towerSync.getTowers().stream().anyMatch(tower -> tower.getLocation().equals(location)));
    }
    public List<TowerSync> getRunningTowerSyncs() {
        return towerSyncs.stream().filter(TowerSync::isRunning).collect(Collectors.toList());
    }

    public boolean isTowerSyncExists(String name) {
        return towerSyncs.stream().anyMatch(towerSync -> towerSync.getName().equalsIgnoreCase(name));
    }

    public Optional<TowerSync> getTowerSyncByName(String name) {
        return towerSyncs.stream().filter(towerSync -> towerSync.getName().equalsIgnoreCase(name)).findFirst();
    }

    public TowerSync createTowerSync(String name) {
        TowerSync towerSync = towerSyncFactory.create(name);
        this.towerSyncs.add(towerSync);
        return towerSync;
    }

    public void deleteTowerSync(String name) {
        Optional<TowerSync> towerSyncOptional = this.getTowerSyncByName(name);
        towerSyncOptional.ifPresent(towerSync -> this.towerSyncs.remove(towerSync));
    }

    public Set<TowerSync> getTowerSyncs() {
        return towerSyncs;
    }
}
