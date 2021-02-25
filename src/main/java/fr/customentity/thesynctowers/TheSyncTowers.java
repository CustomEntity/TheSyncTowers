package fr.customentity.thesynctowers;

import com.google.inject.Guice;
import com.google.inject.Inject;
import fr.customentity.thesynctowers.commands.SubCommandManager;
import fr.customentity.thesynctowers.config.MessagesConfig;
import fr.customentity.thesynctowers.config.TowerSyncConfig;
import fr.customentity.thesynctowers.hook.HookManager;
import fr.customentity.thesynctowers.injection.PluginModule;
import fr.customentity.thesynctowers.listeners.ListenerManager;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.settings.Settings;
import org.bukkit.plugin.java.JavaPlugin;

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
public final class TheSyncTowers extends JavaPlugin {

    @Inject
    private ListenerManager listenerManager;
    @Inject
    private SubCommandManager subCommandManager;
    @Inject
    private HookManager hookManager;
    @Inject
    private MessagesConfig messagesConfig;
    @Inject
    private TowerSyncConfig towerSyncConfig;
    @Inject
    private Settings settings;


    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        Guice.createInjector(new PluginModule(this));

        this.listenerManager.registerListeners();

        this.subCommandManager.registerCommands(this.getClass().getPackage().getName());

        this.messagesConfig.setup();
        Tl.init(this.messagesConfig.get());

        this.towerSyncConfig.setup();
        this.towerSyncConfig.loadTowerSyncs();

        this.settings.loadSettings();

        this.hookManager.onEnable();


        System.out.println(
                "\n  _______ _           _____               _______                         \n" +
                        " |__   __| |         / ____|             |__   __|                        \n" +
                        "    | |  | |__   ___| (___  _   _ _ __   ___| | _____      _____ _ __ ___ \n" +
                        "    | |  | '_ \\ / _ \\\\___ \\| | | | '_ \\ / __| |/ _ \\ \\ /\\ / / _ \\ '__/ __|\n" +
                        "    | |  | | | |  __/____) | |_| | | | | (__| | (_) \\ V  V /  __/ |  \\__ \\\n" +
                        "    |_|  |_| |_|\\___|_____/ \\__, |_| |_|\\___|_|\\___/ \\_/\\_/ \\___|_|  |___/\n" +
                        "                             __/ |                                        \n" +
                        "                            |___/                                         \n\n                  TheSyncTowers - " + this.getDescription().getVersion() + " ENABLED ! \n\n");

        Metrics metrics = new Metrics(this);
    }

    @Override
    public void onDisable() {
        this.towerSyncConfig.saveTowerSyncs();
        this.hookManager.onDisable();
    }


/*    public void reloadPlugin() {
        this.nexusConfig.loadNexuses();
        this.schedulersConfig.loadSchedulers();

        this.getNexusManager().getRunningNexuses()
                .forEach(runningNexus -> runningNexus.stop(INexus.EndReason.RELOAD));
    }*/
}
