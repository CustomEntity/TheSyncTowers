package fr.customentity.thesynctowers.data.tower;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.data.participant.IParticipant;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Optional;

public class Tower {

    private TowerSync towerSync;
    private Location location;
    private Material material;

    @Inject
    public Tower(@Assisted TowerSync towerSync, @Assisted Location location, @Assisted Material material) {
        this.towerSync = towerSync;
        this.location = location;
        this.material = material;
    }

    public void handleBreak(Player breaker) {
        Optional<RunningTowerSync> runningTowerSyncOptional = towerSync.getRunningTowerSync();
        if(!runningTowerSyncOptional.isPresent())return;
        RunningTowerSync runningTowerSync = runningTowerSyncOptional.get();
        IParticipant participant = IParticipant.getParticipantFromPlayer(breaker);
        if(runningTowerSync.getParticipantsTowerTable().contains(participant, this)) {
            //TODO: SEND ALREADY TOWER BROKEN MESSAGE
            return;
        }
        runningTowerSync.getParticipantsTowerTable().put(participant, this, System.currentTimeMillis());
    }

    public interface Factory {
        Tower create(TowerSync towerSync, Location location, Material material);
    }

    public Location getLocation() {
        return location;
    }

    public Material getMaterial() {
        return material;
    }

    public TowerSync getTowerSync() {
        return towerSync;
    }
}
