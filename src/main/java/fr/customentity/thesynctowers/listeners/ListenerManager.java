package fr.customentity.thesynctowers.listeners;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import org.bukkit.Bukkit;

@Singleton
public class ListenerManager {

    @Inject private TheSyncTowers plugin;
    @Inject private PlayerListener playerListener;


    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(playerListener, plugin);
    }
}
