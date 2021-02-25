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
import fr.customentity.thesynctowers.tasks.SynchronizationTask;
import org.bukkit.Material;

import java.util.*;
import java.util.stream.Collectors;

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
public class RunningTowerSync {

    private final TowerSync towerSync;
    private final List<IParticipant> participants;
    private final Table<IParticipant, Tower, Long> participantTowerTable;
    private final Map<IParticipant, Long> participantPoints;
    private int timeBeforeEnd;

    private final TheSyncTowers plugin;


    private StartingTask startingTask;
    private RunningTowerSyncTask runningTowerSyncTask;
    private Map<IParticipant, SynchronizationTask> synchronizationTaskMap;

    private RunningTowerSyncTask.Factory runningTowerSyncTaskFactory;

    @Inject
    public RunningTowerSync(TheSyncTowers plugin,
                            RunningTowerSyncTask.Factory runningTowerSyncTaskFactory,
                            @Assisted TowerSync towerSync, @Assisted boolean now) {
        this.plugin = plugin;
        this.towerSync = towerSync;
        this.participants = new ArrayList<>();
        this.participantTowerTable = HashBasedTable.create();
        this.participantPoints = new HashMap<>();
        this.timeBeforeEnd = towerSync.getTimeBeforeEnd();
        this.synchronizationTaskMap = new HashMap<>();

        this.runningTowerSyncTaskFactory = runningTowerSyncTaskFactory;

        if (now) {
            this.start();
        } else {
            this.startingTask = new StartingTask(this);
            this.startingTask.runTaskTimer(plugin, 20, 20);
        }
    }

    public interface Factory {
        RunningTowerSync create(TowerSync towerSync, boolean now);
    }

    public void start() {
        this.runningTowerSyncTask = this.runningTowerSyncTaskFactory.create(this);
        this.runningTowerSyncTask.runTaskTimer(plugin, 20, 20);

        this.towerSync.getTowers().forEach(tower -> tower.getLocation().getWorld()
                .getBlockAt(tower.getLocation()).setType(tower.getMaterial()));
    }

    public List<IParticipant> getParticipants() {
        return participants;
    }

    public Optional<IParticipant> getParticipantByName(String name) {
        return this.participants.stream().filter(participant ->
                participant.getParticipantName().equalsIgnoreCase(name)).findAny();
    }

    public void stop(EndReason endReason) {
        this.towerSync.setRunningTowerSync(null);

        if (startingTask != null) startingTask.cancel();
        if (runningTowerSyncTask != null) runningTowerSyncTask.cancel();

        this.towerSync.getTowers().forEach(tower -> tower.getLocation().getWorld()
                .getBlockAt(tower.getLocation()).setType(Material.AIR));

        if(endReason == EndReason.TIMEUP) {
            if(this.towerSync.getType() == TowerSync.Type.TIME) {
                //TODO: HANDLE THE MOST POINT
            }
        }
    }

    public LinkedHashMap<IParticipant, Long> getTopParticipants() {
        return this.participantPoints.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public int getTimeBeforeEnd() {
        return timeBeforeEnd;
    }

    public void setTimeBeforeEnd(int timeBeforeEnd) {
        this.timeBeforeEnd = timeBeforeEnd;
    }

    public Table<IParticipant, Tower, Long> getParticipantsTowerTable() {
        return participantTowerTable;
    }

    public Map<IParticipant, Long> getParticipantPoints() {
        return participantPoints;
    }

    public Map<IParticipant, SynchronizationTask> getSynchronizationTaskMap() {
        return synchronizationTaskMap;
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
}
