package fr.customentity.thesynctowers.listeners;


import com.google.inject.Inject;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.data.TowerSyncManager;
import fr.customentity.thesynctowers.data.tower.Tower;
import fr.customentity.thesynctowers.utils.pair.Pair;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Optional;

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
public class BlockListener implements Listener {

    @Inject
    private TheSyncTowers plugin;
    @Inject
    private TowerSyncManager towerSyncManager;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        Pair<TowerSync, Tower> towerSyncTowerPair = null;
        for(TowerSync runningTowerSync : this.towerSyncManager.getRunningTowerSyncs()) {
            for(Tower tower : runningTowerSync.getTowers()) {
                if(block.getLocation().equals(tower.getLocation())) {
                    towerSyncTowerPair = Pair.of(runningTowerSync, tower);
                }
            }
        }

        if(towerSyncTowerPair == null)return;
        event.setCancelled(true);
        towerSyncTowerPair.getLeft().getRunningTowerSync().get().handleTowerBreak(towerSyncTowerPair.getRight(), player);
    }
}
