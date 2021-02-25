package fr.customentity.thesynctowers.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.locale.Tl;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
@Singleton
public class MessagesConfig {

    private FileConfiguration messagesConfig;
    private File messagesFile;

    @Inject
    private TheSyncTowers plugin;

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        this.messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!this.messagesFile.exists()) {
            try {
                this.messagesFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.messagesConfig = YamlConfiguration.loadConfiguration(this.messagesFile);
        for (Tl tl : Tl.values()) {
            if (!this.messagesConfig.contains(tl.toString())) {
                this.messagesConfig.set(tl.toString(), tl.isList() ? tl.getMessage() : tl.getMessage().get(0));
            } else {
                tl.setMessage(this.messagesConfig.isList(tl.toString()) ?
                        this.messagesConfig.getStringList(tl.toString()) :
                        Collections.singletonList(this.messagesConfig.getString(tl.toString()))
                );
            }
        }
        save();
    }

    public FileConfiguration get() {
        return messagesConfig;
    }

    public void save() {
        try {
            messagesConfig.save(messagesFile);
        } catch (IOException e) {
        }
    }

    public void reload() {
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
}
