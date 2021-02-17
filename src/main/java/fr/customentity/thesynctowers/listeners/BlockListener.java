package fr.customentity.thesynctowers.listeners;


import com.google.inject.Inject;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.tower.Tower;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Optional;

public class BlockListener implements Listener {

    @Inject private TheSyncTowers plugin;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        Optional<Tower> towerOptional = plugin.getTowerSyncManager().getRunningTowerSyncs().stream()
                .flatMap(towerSync -> towerSync.getTowers().stream())
                .filter(tower -> tower.getLocation().equals(block.getLocation()))
                .findAny();

        if(!towerOptional.isPresent())return;
        towerOptional.get().handleBreak(player);
    }
}
