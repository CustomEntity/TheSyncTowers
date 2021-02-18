package fr.customentity.thesynctowers.data;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.Assisted;
import fr.customentity.thesynctowers.TheSyncTowers;
import org.bukkit.Location;

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
@Singleton
public class TowerSyncManager {

    private final TheSyncTowers plugin;
    private Set<TowerSync> towerSyncs;

    private final TowerSync.Factory towerSyncFactory;

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

    public TowerSync createTowerSync(TowerSync.Type type, String name, long valueToWin, int timeBeforeEnd, int timeInterval) {
        TowerSync towerSync = towerSyncFactory.create(type, name, valueToWin, timeBeforeEnd, timeInterval);
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

    public void setTowerSyncs(Set<TowerSync> towerSyncs) {
        this.towerSyncs = towerSyncs;
    }

}
