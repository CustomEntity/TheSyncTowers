package fr.customentity.thesynctowers.data.tower;

import com.google.common.collect.Table;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.data.participant.IParticipant;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.tasks.SynchronizationTask;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;

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
    public interface Factory {
        Tower create(TowerSync towerSync, Location location, Material material);
    }

    public void handleBreak(Player breaker) {
        Optional<RunningTowerSync> runningTowerSyncOptional = towerSync.getRunningTowerSync();
        if (!runningTowerSyncOptional.isPresent()) return;
        RunningTowerSync runningTowerSync = runningTowerSyncOptional.get();
        IParticipant participant = IParticipant.getParticipantFromPlayer(breaker);
        if(participant == null) {
            // TODO: HANDLE PARTICIPANT NULL
            return;
        }

        if (runningTowerSync.getParticipantsTowerTable().contains(participant, this)) {
            Tl.sendConfigMessage(breaker, Tl.GAME_ON$TOWER$ALREADY$BROKEN, towerSync);
            return;
        }

        Table<IParticipant, Tower, Long> participantTowerTable = runningTowerSync.getParticipantsTowerTable();
        Map<IParticipant, Map<Tower, Long>> rowMap = participantTowerTable.rowMap();

        long lastBreakTime = System.currentTimeMillis();
        participantTowerTable.put(participant, this, lastBreakTime);
        long firstBreakTime = rowMap.get(participant).entrySet().iterator().next().getValue();
        if (rowMap.get(participant).size() == runningTowerSync.getTowerSync().getTowers().size()) {
            long point = towerSync.getTimeInterval() - (lastBreakTime - firstBreakTime);
            if (point < 0) return;
            long currentPoint = runningTowerSync.getParticipantPoints().getOrDefault(participant, 0L);
            runningTowerSync.getParticipantPoints().put(participant, currentPoint + point);

            Tl.sendConfigMessageToPlayers(participant.getPlayerParticipants(), Tl.GAME_SYNCHRONIZATION_SUCCESS, "%point%", point + "");

            runningTowerSync.getSynchronizationTaskMap().get(participant).cancel();
            runningTowerSync.getSynchronizationTaskMap().remove(participant);
            participantTowerTable.row(participant).clear();
        } else if(rowMap.get(participant).size() == 1){
            if(runningTowerSync.getSynchronizationTaskMap().containsKey(participant))return;

            SynchronizationTask synchronizationTask = new SynchronizationTask(participant, runningTowerSync, firstBreakTime);
            synchronizationTask.runTaskTimer(towerSync.getPlugin(), 0, 1);

            runningTowerSync.getSynchronizationTaskMap().put(participant, synchronizationTask);
        }
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
