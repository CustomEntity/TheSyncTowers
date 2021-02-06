package fr.customentity.thesynctowers.listeners;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import org.bukkit.Bukkit;

@Singleton
public class ListenerManager {

    @Inject private TheSyncTowers plugin;
    @Inject private BlockListener blockListener;


    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(blockListener, plugin);
    }
}
