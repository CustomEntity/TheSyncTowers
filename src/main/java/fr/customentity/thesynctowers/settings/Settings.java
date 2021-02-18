package fr.customentity.thesynctowers.settings;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.settings.values.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

@Singleton
public class Settings {

    public static final LongSetting SAVE_DATAS_DELAY = new LongSetting("settings.save-datas-delay", 3600);
    public static final IntSetting START_COOLDOWN_IN_SECOND = new IntSetting("settings.start-cooldown-in-second", 300);
    public static final IntListSetting START_COOLDOWN_MESSAGES_IN_SECOND = new IntListSetting("settings.start-cooldown-messages-in-second",
            Arrays.asList(300, 240, 180, 120, 60, 30, 15, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
    public static final StringSetting PARTICIPANT_TYPE = new StringSetting("settings.participant-type", "PLAYER");


    private final Set<Setting<?>> settingList = new HashSet<>();
    private final TheSyncTowers plugin;

    @Inject
    public Settings(TheSyncTowers plugin) {
        this.plugin = plugin;

        registerSettings();
    }

    public void registerSettings() {
        registerSetting(
                SAVE_DATAS_DELAY,
                START_COOLDOWN_MESSAGES_IN_SECOND,
                START_COOLDOWN_IN_SECOND,
                PARTICIPANT_TYPE
        );
    }

    public void loadSettings() {
        this.settingList.forEach(setting -> setting.load(plugin.getConfig()));
    }

    public void registerSetting(Setting<?> setting) {
        this.settingList.add(setting);
    }

    public void registerSetting(Setting<?>... setting) {
        this.settingList.addAll(Arrays.asList(setting));
    }


}
