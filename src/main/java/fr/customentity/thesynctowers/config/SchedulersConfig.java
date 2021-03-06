package fr.customentity.thesynctowers.config;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.gson.GsonManager;
import fr.customentity.thesynctowers.scheduler.Scheduler;
import fr.customentity.thesynctowers.scheduler.SchedulerManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;

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
 * @Date: 26/02/2021
 */
public class SchedulersConfig {

    private File schedulersFile;

    @Inject private TheSyncTowers plugin;
    @Inject private SchedulerManager schedulerManager;
    @Inject private GsonManager gsonManager;


    public void setup() {
        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }
        try {
            schedulersFile = this.gsonManager.getOrCreateFile("schedulers.json");
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.WARNING, "Error while creating schedulers file !");
            e.printStackTrace();
        }
    }

    public void loadSchedulers() {
        Type type = new TypeToken<List<Scheduler>>() {
        }.getType();
        try {
            List<Scheduler> schedulerSet = (List<Scheduler>) this.gsonManager.fromJson(schedulersFile, type);
            if (schedulerSet != null) {
                this.schedulerManager.setSchedulers(schedulerSet);
            }
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.WARNING, "Error while loading schedulers from schedulers file !");
            e.printStackTrace();
        }
    }

    public void saveSchedulers() {
        Type type = new TypeToken<List<Scheduler>>() {
        }.getType();
        try {
            if (this.gsonManager.saveJSONToFile(schedulersFile, this.schedulerManager.getSchedulers(), type)) {
                this.plugin.getLogger().log(Level.WARNING, "All schedulers saved !");
            }
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.WARNING, "Error while saving schedulers in schedulers file !");
            e.printStackTrace();
        }
    }
}
