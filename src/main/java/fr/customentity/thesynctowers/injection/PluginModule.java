package fr.customentity.thesynctowers.injection;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.tower.Tower;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.tasks.RunningTowerSyncTask;

public class PluginModule extends AbstractModule {

    private TheSyncTowers plugin;

    public PluginModule(TheSyncTowers plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        this.bind(TheSyncTowers.class).toInstance(this.plugin);
        this.install(new FactoryModuleBuilder()
                .build(Tower.Factory.class));
        this.install(new FactoryModuleBuilder()
                .build(TowerSync.Factory.class));
        this.install(new FactoryModuleBuilder()
                .build(RunningTowerSync.Factory.class));
    }
}
