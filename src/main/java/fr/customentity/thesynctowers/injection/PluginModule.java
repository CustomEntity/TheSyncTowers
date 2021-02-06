package fr.customentity.thesynctowers.injection;

import com.google.inject.AbstractModule;
import fr.customentity.thesynctowers.TheSyncTowers;

public class PluginModule extends AbstractModule {

    private TheSyncTowers plugin;

    public PluginModule(TheSyncTowers plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        this.bind(TheSyncTowers.class).toInstance(this.plugin);
    }
}
