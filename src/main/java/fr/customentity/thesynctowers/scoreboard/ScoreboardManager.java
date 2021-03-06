package fr.customentity.thesynctowers.scoreboard;

import be.maximvdw.featherboard.api.FeatherBoardAPI;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.hook.HookManager;
import fr.customentity.thesynctowers.hook.all.FeatherboardHook;
import fr.customentity.thesynctowers.hook.all.TitleManagerHook;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.settings.Settings;
import fr.customentity.thesynctowers.utils.ColorUtils;
import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import io.puharesource.mc.titlemanager.TitleManagerPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
public class ScoreboardManager implements Listener {

    @Inject
    private TheSyncTowers plugin;

    @Inject
    private HookManager hookManager;

    private final ConcurrentHashMap<Player, Scoreboard> boardHashMap = new ConcurrentHashMap<>();

    public boolean hasScoreBoard(Player player) {
        return boardHashMap.containsKey(player);
    }

    public void applyScoreboard(RunningTowerSync runningTowerSync, Player player) {
        if (!this.hasScoreBoard(player)) {
            if (this.hookManager.isHookEnabled(FeatherboardHook.class)) {
                FeatherBoardAPI.toggle(player, false);
            } else if (this.hookManager.isHookEnabled(TitleManagerHook.class)) {
                TitleManagerPlugin titleManagerPlugin = (TitleManagerPlugin) Bukkit.getPluginManager().getPlugin("TitleManager");
                if (titleManagerPlugin != null)
                    titleManagerPlugin.removeScoreboard(player);
            }

            BPlayerBoard bPlayerBoard = Netherboard.instance().createBoard(player,
                    ChatColor.translateAlternateColorCodes('&',
                            this.plugin.getConfig().getString("scoreboard.name")));
            boardHashMap.put(player, new Scoreboard(bPlayerBoard, runningTowerSync.getTowerSync()));
            return;
        }
        List<String> lines = ColorUtils.translateAlternateColorCodes(
                Tl.addRunningTowerSyncPlaceholder(runningTowerSync,
                        Tl.addTopPlaceholder(runningTowerSync, player,
                                runningTowerSync.getTowerSync().getType() == TowerSync.Type.POINT ?
                                        plugin.getConfig().getStringList("scoreboard.lines-type-point") :
                                        plugin.getConfig().getStringList("scoreboard.lines-type-time")
                        )
                ));
        boardHashMap.get(player).getbPlayerBoard()
                .setAll(lines.stream().map(s -> s.substring(0, Math.min(s.length(), 31))).toArray(String[]::new));
    }


    public void removeScoreboard(TowerSync towerSync) {
        for (Iterator<Map.Entry<Player, Scoreboard>> it = this.boardHashMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Player, Scoreboard> entry = it.next();
            if (entry.getValue().getTowerSync().equals(towerSync)) {
                entry.getValue().getbPlayerBoard().delete();
                this.boardHashMap.remove(entry.getKey());
            }
        }
    }

    public void removeScoreboard(Player player) {
        if (this.hasScoreBoard(player)) {
            this.boardHashMap.get(player).getbPlayerBoard().delete();
            this.boardHashMap.remove(player);

            if (this.hookManager.isHookEnabled(FeatherboardHook.class))
                FeatherBoardAPI.toggle(player, true);
            else if (this.hookManager.isHookEnabled(TitleManagerHook.class)) {
                TitleManagerPlugin titleManagerPlugin = (TitleManagerPlugin) Bukkit.getPluginManager().getPlugin("TitleManager");
                if (titleManagerPlugin != null)
                    titleManagerPlugin.giveScoreboard(player);
            }
        }
    }

    public ConcurrentHashMap<Player, Scoreboard> getBoardHashMap() {
        return boardHashMap;
    }
}
