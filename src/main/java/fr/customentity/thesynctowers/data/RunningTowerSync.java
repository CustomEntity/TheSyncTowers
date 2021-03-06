package fr.customentity.thesynctowers.data;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.participant.IParticipant;
import fr.customentity.thesynctowers.data.reward.Reward;
import fr.customentity.thesynctowers.data.tower.Tower;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.scoreboard.ScoreboardManager;
import fr.customentity.thesynctowers.settings.Settings;
import fr.customentity.thesynctowers.tasks.RunningTowerSyncTask;
import fr.customentity.thesynctowers.tasks.StartingTask;
import fr.customentity.thesynctowers.tasks.SynchronizationTask;
import fr.customentity.thesynctowers.utils.TimeUtils;
import fr.customentity.thesynctowers.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

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
public class RunningTowerSync {

    private final TowerSync towerSync;
    private final List<IParticipant> participants;
    private final Table<IParticipant, Tower, Long> participantTowerTable;
    private final Map<IParticipant, Long> participantPoints;
    private int timeBeforeEnd;

    private StartingTask startingTask;
    private RunningTowerSyncTask runningTowerSyncTask;
    private Map<IParticipant, SynchronizationTask> synchronizationTaskMap;

    private final TheSyncTowers plugin;
    private final ScoreboardManager scoreboardManager;
    private RunningTowerSyncTask.Factory runningTowerSyncTaskFactory;


    @Inject
    public RunningTowerSync(TheSyncTowers plugin,
                            ScoreboardManager scoreboardManager,
                            RunningTowerSyncTask.Factory runningTowerSyncTaskFactory,
                            @Assisted TowerSync towerSync, @Assisted boolean now) {
        this.plugin = plugin;
        this.scoreboardManager = scoreboardManager;
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

        for (Player player : Bukkit.getOnlinePlayers()) {
            Tl.sendConfigMessage(player, Tl.GAME_ON$TOWERSYNC$START$BROADCAST_HEADER, this);
            this.towerSync.getTowers()
                    .forEach(tower -> Tl.sendConfigMessage(player, Tl.GAME_ON$TOWERSYNC$START$BROADCAST_TOWER, tower));
        }
    }

    public List<IParticipant> getParticipants() {
        return participants;
    }

    public Optional<IParticipant> getParticipantByName(String name) {
        return this.participants.stream().filter(participant ->
                participant.getParticipantName().equalsIgnoreCase(name)).findAny();
    }

    public int getParticipantPosition(IParticipant participant) {
        Set<IParticipant> topParticipants = this.getTopParticipants().keySet();
        return new LinkedList<>(topParticipants).indexOf(participant) + 1;
    }

    public void stop(EndReason endReason) {
        this.towerSync.setRunningTowerSync(null);

        if (startingTask != null) startingTask.cancel();
        if (runningTowerSyncTask != null) runningTowerSyncTask.cancel();

        this.towerSync.getTowers().forEach(tower -> tower.getLocation().getWorld()
                .getBlockAt(tower.getLocation()).setType(Material.AIR));

        this.scoreboardManager.removeScoreboard(this.towerSync);

        if (endReason != EndReason.FORCED && endReason != EndReason.RELOAD) {
            if(endReason == EndReason.TIMEUP) {
                Bukkit.getOnlinePlayers().forEach(player ->
                        Tl.sendConfigMessage(player, Tl.GAME_ON$TOWERSYNC$TIMEUP$BROADCAST, this));
                return;
            }
            Bukkit.getOnlinePlayers().forEach(player ->
                    Tl.sendConfigMessage(player, Tl.GAME_ON$TOWERSYNC$WIN$BROADCAST, this));
            if (this.towerSync.getRewards() != null) {
                for (IParticipant participant : this.getParticipants()) {
                    int position = this.getParticipantPosition(participant);

                    for (Reward reward : this.towerSync.getRewards()) {
                        if (reward.getBegin() >= position && position <= reward.getEnd()) {
                            reward.getCommands().forEach(s -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s
                                    .replace("%participant%", participant.getParticipantName())));
                            break;
                        }
                    }
                }
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

    public void handleTowerBreak(Tower tower, Player breaker) {
        Optional<RunningTowerSync> runningTowerSyncOptional = towerSync.getRunningTowerSync();
        if (!runningTowerSyncOptional.isPresent()) return;
        RunningTowerSync runningTowerSync = runningTowerSyncOptional.get();

        IParticipant participant = IParticipant.getParticipantFromPlayer(breaker);
        if (participant == null) {
            Tl.sendConfigMessage(breaker, Tl.GAME_NOT$IN$A$TEAM, this);
            return;
        }

        if(Settings.ITEMS_RESTRICTION_ENABLED.getValue()) {
            String type = Settings.ITEMS_RESTRICTION_TYPE.getValue();
            boolean hasRightItem = Settings.ITEMS_RESTRICTION_ITEMS.getValue().stream()
                    .map(XMaterial::matchXMaterial)
                    .filter(Optional::isPresent)
                    .map(xMaterial -> xMaterial.get().parseMaterial())
                    .anyMatch(material ->
                            type.equalsIgnoreCase("BLACKLIST") == (material != breaker.getItemInHand().getType())
                    );
            if(!hasRightItem) {
                Tl.sendConfigMessage(breaker, Tl.GAME_INCORRECT$ITEM, this);
                return;
            }
        }

        Optional<IParticipant> participantOptional = runningTowerSync
                .getParticipantByName(participant.getParticipantName());
        if (participantOptional.isPresent()) {
            participant = participantOptional.get();
        } else {
            runningTowerSync.getParticipants().add(participant);
        }


        Table<IParticipant, Tower, Long> participantTowerTable = runningTowerSync.getParticipantsTowerTable();
        Map<IParticipant, Map<Tower, Long>> rowMap = participantTowerTable.rowMap();

        if (participantTowerTable.contains(participant, this)) {
            Tl.sendConfigMessage(breaker, Tl.GAME_ON$TOWER$ALREADY$BROKEN, towerSync);
            return;
        }

        long lastBreakTime = System.currentTimeMillis();
        participantTowerTable.put(participant, tower, lastBreakTime);
        long firstBreakTime = rowMap.get(participant).values().stream()
                .sorted()
                .findFirst()
                .get();

        if (rowMap.get(participant).size() == runningTowerSync.getTowerSync().getTowers().size()) {
            long point = towerSync.getTimeInterval() - (lastBreakTime - firstBreakTime);
            if (point < 0) return;
            long currentPoint = runningTowerSync.getParticipantPoints().getOrDefault(participant, 0L);
            runningTowerSync.getParticipantPoints().put(participant, currentPoint + point);

            Tl.sendConfigMessageToPlayers(participant.getPlayerParticipants(), Tl.GAME_SYNCHRONIZATION_SUCCESS, "%point%", point + "");

            runningTowerSync.getSynchronizationTaskMap().get(participant).cancel();
            runningTowerSync.getSynchronizationTaskMap().remove(participant);
            participantTowerTable.row(participant).clear();

            if (towerSync.getType() == TowerSync.Type.POINT && point + currentPoint >= towerSync.getGoal())
                runningTowerSync.stop(EndReason.WON);

        } else if (rowMap.get(participant).size() == 1) {
            if (runningTowerSync.getSynchronizationTaskMap().containsKey(participant)) return;
            SynchronizationTask synchronizationTask = new SynchronizationTask(participant, runningTowerSync, firstBreakTime);
            synchronizationTask.runTaskTimer(towerSync.getPlugin(), 0, 1);

            runningTowerSync.getSynchronizationTaskMap().put(participant, synchronizationTask);
        }
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
