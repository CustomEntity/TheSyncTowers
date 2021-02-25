package fr.customentity.thesynctowers.injection;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.SubCommandExecutor;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.tower.Tower;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.tasks.RunningTowerSyncTask;

/**
 *  Copyright (c) 2021. By CustomEntity
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * @Author: CustomEntity
 * @Date: 18/02/2021
 *
 */
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
                .build(RunningTowerSync.Factory.class));
        this.install(new FactoryModuleBuilder()
                .build(TowerSync.Factory.class));
        this.install(new FactoryModuleBuilder()
                .build(SubCommandExecutor.Factory.class));
        this.install(new FactoryModuleBuilder()
                .build(RunningTowerSyncTask.Factory.class));
    }
}
