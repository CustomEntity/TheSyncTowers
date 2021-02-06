package fr.customentity.thesynctowers;

import com.google.inject.Guice;
import com.google.inject.Inject;
import fr.customentity.thesynctowers.commands.SubCommandManager;
import fr.customentity.thesynctowers.gson.GsonManager;
import fr.customentity.thesynctowers.injection.PluginModule;
import fr.customentity.thesynctowers.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TheSyncTowers extends JavaPlugin {

    @Inject private GsonManager gsonManager;
    @Inject private ListenerManager listenerManager;
    @Inject private SubCommandManager subCommandManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        Guice.createInjector(new PluginModule(this));

        this.listenerManager.registerListeners();

        Metrics metrics = new Metrics(this);
    }

    @Override
    public void onDisable() {
    }

    public GsonManager getGsonManager() {
        return gsonManager;
    }
}
