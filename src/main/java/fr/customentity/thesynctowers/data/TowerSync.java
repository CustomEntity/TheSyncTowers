package fr.customentity.thesynctowers.data;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.reward.Reward;
import fr.customentity.thesynctowers.data.tower.Tower;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
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
public class TowerSync {

    private TowerSync.Type type;
    private String name;
    private long goal;
    private int timeBeforeEnd;
    private int timeInterval;
    private List<Tower> towers;
    private List<Reward> rewards;

    private transient final TheSyncTowers plugin;
    private transient final Tower.Factory towerFactory;
    private transient final RunningTowerSync.Factory runningTowerSyncFactory;
    private transient RunningTowerSync runningTowerSync;

    @Inject
    public TowerSync(TheSyncTowers plugin,
                     Tower.Factory towerFactory,
                     RunningTowerSync.Factory runningTowerSyncFactory,
                     @Assisted TowerSync.Type type,
                     @Assisted String name,
                     @Assisted long goal,
                     @Assisted("timeBeforeEnd") int timeBeforeEnd,
                     @Assisted("timeInterval") int timeInterval,
                     @Assisted List<Tower> towers,
                     @Assisted List<Reward> rewards
    ) {
        this.plugin = plugin;
        this.towerFactory = towerFactory;
        this.runningTowerSyncFactory = runningTowerSyncFactory;

        this.type = type;
        this.name = name;
        this.goal = goal;
        this.timeBeforeEnd = timeBeforeEnd;
        this.timeInterval = timeInterval;
        this.towers = towers;
        this.rewards = rewards;

        this.runningTowerSync = null;
    }

    public interface Factory {
        TowerSync create(TowerSync.Type type, String name, long goal,
                         @Assisted("timeBeforeEnd") int timeBeforeEnd, @Assisted("timeInterval") int timeInterval,
                         List<Tower> towers, List<Reward> rewards);
    }

    public void stop(EndReason endReason) {
        this.getRunningTowerSync().ifPresent(runningTowerSync1 -> runningTowerSync1.stop(endReason));
    }


    public enum Type {
        TIME,
        POINT
    }

    public Type getType() {
        return type;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public TheSyncTowers getPlugin() {
        return plugin;
    }

    public long getGoal() {
        return goal;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }

    public void setGoal(long goal) {
        this.goal = goal;
    }

    public void setRunningTowerSync(RunningTowerSync runningTowerSync) {
        this.runningTowerSync = runningTowerSync;
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

    public void addTower(String name, Location location, Material material) {
        towers.add(towerFactory.create(name, location, material));
    }

    public void removeTower(String name) {
        this.towers.removeIf(tower -> tower.getName().equalsIgnoreCase(name));
    }

    public boolean isTowerExisting(String name) {
        return this.towers.stream().anyMatch(tower -> tower.getName().equalsIgnoreCase(name));
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
