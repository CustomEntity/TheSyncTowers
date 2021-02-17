package fr.customentity.thesynctowers.data;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.participant.IParticipant;
import fr.customentity.thesynctowers.data.tower.Tower;
import fr.customentity.thesynctowers.tasks.RunningTowerSyncTask;
import fr.customentity.thesynctowers.tasks.StartingTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RunningTowerSync {

    private final TowerSync towerSync;
    private final Table<IParticipant, Tower, Long> participantTowerTable;
    private final Map<IParticipant, Long> participantPoints;

    private final TheSyncTowers plugin;

    private StartingTask startingTask;
    private RunningTowerSyncTask runningTowerSyncTask;

    @Inject
    public RunningTowerSync(TheSyncTowers plugin,
                            @Assisted TowerSync towerSync, @Assisted boolean now) {
        this.plugin = plugin;
        this.towerSync = towerSync;
        this.participantTowerTable = HashBasedTable.create();
        this.participantPoints = new HashMap<>();

        if (now) {
            this.start();
        } else {
            this.startingTask = new StartingTask(this);
            this.startingTask.runTaskTimer(plugin, 20, 20);
        }
    }

    public void start() {
        this.runningTowerSyncTask = new RunningTowerSyncTask(this.plugin, this);
        this.runningTowerSyncTask.runTaskTimer(plugin, 20, 20);
    }

    public Table<IParticipant, Tower, Long> getParticipantsTowerTable() {
        return participantTowerTable;
    }

    public Map<IParticipant, Long> getParticipantPoints() {
        return participantPoints;
    }

    public TowerSync getTowerSync() {
        return towerSync;
    }

    public Optional<RunningTowerSyncTask> getRunningTowerSyncTask() {
        return Optional.ofNullable(runningTowerSyncTask);
    }

    public Optional<StartingTask> getStartingTask() {
        return Optional.ofNullable(startingTask);
    }

    public interface Factory {
        RunningTowerSync create(TowerSync towerSync, boolean now);
    }
}
