package fr.customentity.thesynctowers;

import com.google.inject.Guice;
import com.google.inject.Inject;
import fr.customentity.thesynctowers.commands.SubCommandManager;
import fr.customentity.thesynctowers.config.MessagesConfig;
import fr.customentity.thesynctowers.data.towers.TowerSyncManager;
import fr.customentity.thesynctowers.gson.GsonManager;
import fr.customentity.thesynctowers.injection.PluginModule;
import fr.customentity.thesynctowers.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class TheSyncTowers extends JavaPlugin {

    @Inject private GsonManager gsonManager;
    @Inject private ListenerManager listenerManager;
    @Inject private SubCommandManager subCommandManager;
    @Inject private TowerSyncManager towerSyncManager;

    @Inject private MessagesConfig messagesConfig;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        Guice.createInjector(new PluginModule(this));

        this.listenerManager.registerListeners();

        this.subCommandManager.registerCommands();

        this.messagesConfig.setup();

        System.out.println("\n  _______ _           _____               _______                         \n" +
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
    }

    public GsonManager getGsonManager() {
        return gsonManager;
    }

    public MessagesConfig getMessagesConfig() {
        return messagesConfig;
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    public SubCommandManager getSubCommandManager() {
        return subCommandManager;
    }

    public TowerSyncManager getTowerSyncManager() {
        return towerSyncManager;
    }
}
