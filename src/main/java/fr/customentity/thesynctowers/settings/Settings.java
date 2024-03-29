package fr.customentity.thesynctowers.settings;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.settings.values.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class Settings {

    public static final Setting<Long> SAVE_DATAS_DELAY = new LongSetting("settings.save-datas-delay", 3600);
    public static final Setting<Integer> START_COOLDOWN_IN_SECOND = new IntSetting("settings.start-cooldown-in-second", 300);
    public static final Setting<List<Integer>> START_COOLDOWN_MESSAGES_IN_SECOND = new IntListSetting("settings.start-cooldown-messages-in-second",
            Arrays.asList(300, 240, 180, 120, 60, 30, 15, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
    public static final Setting<String> PARTICIPANT_TYPE = new StringSetting("settings.participant-type", "PLAYER");

    public static final Setting<Boolean> SCOREBOARD_ENABLED = new BooleanSetting("scoreboard.enabled", true);
    public static final Setting<Boolean> SCOREBOARD_CHECK_DISTANCE_TO_APPLY = new BooleanSetting("scoreboard.check-distance-to-apply", true);
    public static final Setting<Integer> SCOREBOARD_DISTANCE_TO_APPLY_VALUE = new IntSetting("scoreboard.distance-to-apply-value", 20);

    public static final Setting<Boolean> ITEMS_RESTRICTION_ENABLED = new
            BooleanSetting("settings.items-restriction.restriction", true);
    public static final Setting<String> ITEMS_RESTRICTION_TYPE = new
            StringSetting("settings.items-restriction.restriction-type", "WHITELIST");
    public static final Setting<List<String>> ITEMS_RESTRICTION_ITEMS = new
            StringListSetting("settings.items-restriction.restriction", Arrays.asList("DIAMOND_SWORD",
            "IRON_SWORD", "GOLDEN_SWORD", "WOODEN_SWORD"
    ));

    @Inject private TheSyncTowers plugin;
    private final Set<Setting<?>> settingList = new HashSet<>();


    public void registerSettings() {
        this.registerSetting(
                SAVE_DATAS_DELAY,
                START_COOLDOWN_MESSAGES_IN_SECOND,
                START_COOLDOWN_IN_SECOND,
                PARTICIPANT_TYPE,
                SCOREBOARD_ENABLED,
                SCOREBOARD_CHECK_DISTANCE_TO_APPLY,
                SCOREBOARD_DISTANCE_TO_APPLY_VALUE,
                ITEMS_RESTRICTION_ENABLED,
                ITEMS_RESTRICTION_TYPE,
                ITEMS_RESTRICTION_ITEMS
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
