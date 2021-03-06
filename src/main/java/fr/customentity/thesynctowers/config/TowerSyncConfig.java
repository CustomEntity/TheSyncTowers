package fr.customentity.thesynctowers.config;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.data.TowerSyncManager;
import fr.customentity.thesynctowers.data.tower.Tower;
import fr.customentity.thesynctowers.gson.GsonManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

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
public class TowerSyncConfig {

    private File towerSyncsFile;

    @Inject
    private TheSyncTowers plugin;
    @Inject
    private GsonManager gsonManager;
    @Inject
    private TowerSyncManager towerSyncManager;
    @Inject
    private TowerSync.Factory towerSyncFactory;



    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        try {
            towerSyncsFile = this.gsonManager.getOrCreateFile("towersyncs.json");
        } catch (IOException e) {
            plugin.getLogger().log(Level.WARNING, "Error while creating towersyncs file !");
            e.printStackTrace();
        }
    }

    public void loadTowerSyncs() {
        Type type = new TypeToken<HashSet<TowerSync>>() {
        }.getType();
        try {
            Set<TowerSync> towerSyncsSet = (Set<TowerSync>) this.gsonManager.fromJson(towerSyncsFile, type);
            if (towerSyncsSet != null) {
                towerSyncsSet.forEach(towerSync -> {
                    TowerSync newTowerSync = towerSyncFactory.create(towerSync.getType(),
                            towerSync.getName(),
                            towerSync.getGoal(),
                            towerSync.getTimeBeforeEnd(),
                            towerSync.getTimeInterval(),
                            towerSync.getTowers(),
                            towerSync.getRewards()
                    );

                    this.towerSyncManager.getTowerSyncs().add(newTowerSync);
                });
            }
        } catch (IOException e) {
            plugin.getLogger().log(Level.WARNING, "Error while loading towersyncs from towersyncs file !");
            e.printStackTrace();
        }
    }

    public void saveTowerSyncs() {
        Type type = new TypeToken<HashSet<TowerSync>>() {
        }.getType();
        try {
            if (this.gsonManager.saveJSONToFile(towerSyncsFile, this.towerSyncManager.getTowerSyncs(), type)) {
                plugin.getLogger().log(Level.WARNING, "All TowerSync saved !");
            }
        } catch (IOException e) {
            plugin.getLogger().log(Level.WARNING, "Error while saving towersyncs in towersyncs file !");
            e.printStackTrace();
        }
    }
}
