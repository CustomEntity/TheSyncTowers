package fr.customentity.thesynctowers.listeners;

import com.google.inject.Inject;
import fr.customentity.thesynctowers.scoreboard.ScoreboardManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

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

public class ScoreboardListener implements Listener {

    @Inject
    private ScoreboardManager scoreboardManager;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.scoreboardManager.removeScoreboard(event.getPlayer());
    }

}
