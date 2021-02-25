package fr.customentity.thesynctowers.locale;

import com.google.inject.Inject;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.config.MessagesConfig;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.data.participant.IParticipant;
import fr.customentity.thesynctowers.data.tower.Tower;
import fr.customentity.thesynctowers.utils.ActionBarUtils;
import fr.customentity.thesynctowers.utils.TitleUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

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
public enum Tl {

    GENERAL_PREFIX("&3&lTST &7&l»"),

    COMMAND_NOT$NUMBER("%prefix% &c%arg% is not a number !"),
    COMMAND_NO$PERMISSION("%prefix% &cYou don't have permission to execute that command !"),

    COMMAND_CREATE_SYNTAX("%prefix% &cError syntax, please use: /tst create <name>"),
    COMMAND_CREATE_SUCCESS("%prefix% &fThe &b%arg% &ftowersync has been successfully created !"),

    COMMAND_DELETE_SYNTAX("%prefix% &cError syntax, please use: /tst delete <name>"),
    COMMAND_DELETE_SUCCESS("%prefix% &fThe &b%arg% &ftowersync has been successfully deleted !"),

    GENERAL_TOWERSYNC$ALREADY$EXISTS("%prefix% &cThat towersync already exists !"),
    GENERAL_TOWERSYNC$NOT$EXISTS("%prefix% &cThat towersync doesn't exist !"),
    GENERAL_TOWERSYNC$NOT$RUNNING("%prefix% &cThat towersync is not running !"),

    COMMAND_RELOAD_SUCCESS("%prefix% &aConfig reloaded!"),

    GENERAL_HELP$MESSAGE(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m &b&lTheSyncTowers Help &b&m--&3&m--&b&m--&3&m--&b&m--&3&m--",
            " ",
            " &7&l» &f/&btst create &3<name> &8- &bCreate a new towersync&f.",
            " &7&l» &f/&btst delete &3<towersync> &8- &bDelete a towersync&f.",
            " &7&l» &f/&btst reload &8- &bReload the plugin&f.",
            " &7&l» &f/&btst stop &3<towersync> &8- &bStop a running towersync&f.",
            " &7&l» &f/&btst now &3<towersync> &8- &bStart towersync without cooldown&f.",
            " &7&l» &f/&btst start &3<towersync> &8- &bStart a towersync&f.",
            " &7&l» &f/&btst list &8- &bShow towersyncs list&f.",
            " &7&l» &f/&btst edit &3<towersync> &8- &bShow towersyncs edit commands&f.",
            " &7&l» &f/&btst scheduler &8- &bShow towersyncs scheduler commands&f.",
            " &7&l» &f/&btst reward &8- &bEdit towersyncs rewards &f.",
            " ",
            "&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lBy CustomEntity &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--"
    )),

    COMMAND_NOW_SYNTAX("%prefix% &cError syntax, please use: /tst now <towersync>"),
    COMMAND_NOW_ALREADY$RUNNING("%prefix% &cThe towersync %towersync% is already running !"),
    COMMAND_NOW_NOT$EXISTS("%prefix% &cThe towersync %towersync% doesn't exist !"),
    COMMAND_NOW_SUCCESS("%prefix% &fThe towersync &b%towersync% &fstarted !"),

    COMMAND_START_SYNTAX("%prefix% &cError syntax, please use: /tst start <towersync>"),
    COMMAND_START_ALREADY$RUNNING("%prefix% &cThe towersync %towersync% is already running !"),
    COMMAND_START_NOT$EXISTS("%prefix% &cThe towersync %towersync% doesn't exist !"),
    COMMAND_START_SUCCESS("%prefix% &fThe towersync &b%towersync% &fstarted !"),

    COMMAND_LIST_HEADER(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lTOWERSYNCS LIST &3&m--&b&m--&3&m--&b&m--&3&m--",
            " "
    )),
    COMMAND_LIST_TOWERSYNC("&7&l» &b&l%towersync_name% &8&m-&b &3Example"),
    COMMAND_LIST_FOOTER(" "),
    COMMAND_LIST_EMPTY("%prefix% &cThere is no towersync created!"),


    COMMAND_EDIT_HELP$MESSAGE(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lTOWERSYNC EDIT &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--",
            " ",
            " &7&l» &f/&btst edit &3<towersync> &bsettimebeforeend &3<seconds>",
            " &7&l» &f/&btst edit &3<towersync> &btower",
            " ",
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lTOWERSYNC EDIT &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--"
    )),

    COMMAND_EDIT_SET$TIME$BEFORE$END_SYNTAX(
            "%prefix% &cError syntax, please use: /tst edit <towersync> settimebeforeend <seconds>"),
    COMMAND_EDIT_SET$TIME$BEFORE$END_SUCCESS("%prefix% &fThe time before the end of the towersync &b%towersync_name% &fhas been set."),

    COMMAND_EDIT_TOWER_HELP$MESSAGE(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lTOWER EDIT &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--",
            " ",
            " &7&l» &f/&btst edit &3<towersync> &btower add",
            " &7&l» &f/&btst edit &3<towersync> &btower remove <id>",
            " &7&l» &f/&btst edit &3<towersync> &btower list",
            " ",
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lTOWER EDIT &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--"
    )),
    COMMAND_EDIT_TOWER_ADD_NO$BLOCK$TARGETED(
            "%prefix% &cYou have to select a block to target !"),

    COMMAND_EDIT_TOWER_ADD_SUCCESS(
            "%prefix% &fYou have created a new tower succesfully !"),

    COMMAND_EDIT_TOWER_REMOVE_SYNTAX(
            "%prefix% &cError syntax, please use: /tst edit <towersync> tower remove <id>"),

    COMMAND_EDIT_TOWER_REMOVE_SUCCESS(
            "%prefix% &fYou have delete the tower succesfully !"),

    COMMAND_EDIT_TOWER_REMOVE_INCORRECT$ID(
            "%prefix% &cIncorrect ID !"),

    COMMAND_EDIT_TOWER_LIST_HEADER(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lTOWERS LIST &3&m--&b&m--&3&m--&b&m--&3&m--",
            " "
    )),
    COMMAND_EDIT_TOWER_LIST_TOWER("&7&l» &b&l%id% &8&m-&b &3X: &b%X%&7, &3Y: &b%Y%&7, &3Z: &b%Z%&7, &3Material: &b%material%"),
    COMMAND_EDIT_TOWER_LIST_FOOTER(" "),
    COMMAND_EDIT_TOWER_LIST_EMPTY("%prefix% &cThere is no tower created!"),

    COMMAND_STOP_SYNTAX("%prefix% &cError syntax, please use: /tst stop <towersync>"),
    COMMAND_STOP_SUCCESS("%prefix% &fThe &b%towersync_name% &ftowersync has been successfully stopped !"),


    GAME_ON$TOWER$ALREADY$BROKEN("%prefix% &cYour team has already broken this tower !"),
    GAME_SYNCHRONIZATION_SUCCESS("%actionbar%&aSuccessful synchronization! Your team has won &e%point% &apoints!"),
    GAME_SYNCHRONIZATION_PROGRESSION("%actionbar%&eSynchronization in progress... &f(&e%current%&7/&e%goal%&f) &b%time%s remaining"),
    GAME_SYNCHRONIZATION_FAILED("%actionbar%&cSynchronization failed !"),
    GAME_NOT$IN$A$TEAM("%prefix% &cYou cannot participate without a team!"),


    SCOREBOARD_TOP$EMPTY("&c✘"),

    GENERAL_REWARD$NOT$EXISTS("%prefix% &cThat reward doesn't exist !"),

    GAME_NEXUS_ON$RELOAD("%prefix% &fThe nexus &b%nexus_name% &fwas stopped by force!"),
    GAME_NEXUS_HEALTH_MESSAGE("%prefix% &fThe nexus is at &b%percent%% &fof its life! Health: &b%runningnexus_health% &4❤"),
    GAME_COOLDOWN_MESSAGE("%prefix% &fThe nexus &b%nexus_name% &fwill start in &b%minutes% minute(s) and %seconds% second(s) &fat &7(&bX: &f%nexus_location_X%, &bY: &f%nexus_location_Y%, &bZ: &f%nexus_location_Z%&7)"),
    GAME_ON$NEXUS$STOPPED$BROADCAST("%prefix% &fThe nexus &b%nexus_name% &fhas been stopped!"),
    GAME_ON$NEXUS$TIMEUP$BROADCAST("%prefix% &fThe nexus %nexus_name% didn't have time to be killed."),
    GAME_ON$NEXUS$WIN$BROADCAST("%prefix% &b%winner% &fdealt the final blow to the &b%nexus_name% &fnexus."),
    GAME_ON$NEXUS$START$BROADCAST("%prefix% &fThe &b%nexus_name% &fnexus appeared in &7(&bX: &f%nexus_location_X%, &bY: &f%nexus_location_Y%, &bZ: &f%nexus_location_Z%&7)"),
    GAME_ON$DAMAGE$NEXUS_FACTION_DOESNT$HAVE$FACTION("%prefix% &cYou have to join a faction to participate !"),
    GAME_ON$DAMAGE$NEXUS_SKYBLOCK_DOESNT$HAVE$ISLAND("%prefix% &cYou have to join an island to participate !"),
    GAME_ON$DAMAGE$NEXUS_GANG_DOESNT$HAVE$GANG("%prefix% &cYou have to join a gang to participate !"),
    GAME_ON$DAMAGE$NEXUS_CLANS_DOESNT$HAVE$GANG("%prefix% &cYou have to join a clan to participate !"),


    COMMAND_SCHEDULER_HELP$MESSAGE(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS SCHEDULER &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--",
            " ",
            " &7&l» &f/&bnexus scheduler list",
            " &7&l» &f/&bnexus scheduler delete <schedulerID>",
            " &7&l» &f/&bnexus scheduler repeat &3<minute> <nexus> [...]",
            " &7&l» &f/&bnexus scheduler daily &3<time_of_day> <minute> <nexus> [...]",
            " &7&l» &f/&bnexus scheduler weekly &3<day_of_week> <time_of_day> <minute> <nexus> [...]",
            " &7&l» &f/&bnexus scheduler specific &3<month> <day_of_month> <time_of_day> <minute> <nexus> [...]",
            " ",
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS SCHEDULER &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--"
    )),

    COMMAND_SCHEDULER_LIST_HEADER(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lSCHEDULERS LIST &3&m--&b&m--&3&m--&b&m--&3&m--",
            " "
    )),
    COMMAND_SCHEDULER_LIST_SCHEDULER(Arrays.asList("&7&l%id%. &3%scheduler_type% &f- &b%nexuses%",
            "   &7▪ &bMonth: &f%scheduler_month%",
            "   &7▪ &bDay of month: &f%scheduler_dayOfMonth%",
            "   &7▪ &bDay of week: &f%scheduler_dayOfWeek%",
            "   &7▪ &bTime of day: &f%scheduler_timeOfDay%",
            "   &7▪ &bMinute: &f%scheduler_minutes%",
            " "
    )),
    COMMAND_SCHEDULER_LIST_FOOTER(""),
    COMMAND_SCHEDULER_LIST_EMPTY("%prefix% &cThere is no scheduler created!"),

    COMMAND_SCHEDULER_DELETE_SYNTAX("%prefix% &cError syntax, please use: /nexus scheduler delete <schedulerID>"),
    COMMAND_SCHEDULER_DELETE_SUCCESS("%prefix% &fThe scheduler has been successfully deleted!"),

    COMMAND_SCHEDULER_REPEAT_SYNTAX("%prefix% &cError syntax, please use: /nexus scheduler repeat <time_in_minute> <nexus> [...]"),
    COMMAND_SCHEDULER_REPEAT_SUCCESS("%prefix% &fThe repeated launch has been successfully scheduled!"),

    COMMAND_SCHEDULER_DAILY_SYNTAX("%prefix% &cError syntax, please use: /nexus scheduler daily <time_of_day> <minute> [...]"),
    COMMAND_SCHEDULER_DAILY_SUCCESS("%prefix% The daily launch has been successfully scheduled!"),

    COMMAND_SCHEDULER_WEEKLY_SYNTAX("%prefix% &cError syntax, please use: /nexus scheduler weekly <day_of_week> <time_of_day> <minute>  <nexus> [...]"),
    COMMAND_SCHEDULER_WEEKLY_SUCCESS("%prefix% The weekly launch has been successfully scheduled!"),

    COMMAND_SCHEDULER_SPECIFIC_SYNTAX("%prefix% &cError syntax, please use: /nexus scheduler specific <month> <day_of_month> <time_of_day> <minute> <nexus> [...]"),
    COMMAND_SCHEDULER_SPECIFIC_SUCCESS("%prefix% The specific launch has been successfully scheduled!"),


    COMMAND_REWARD_HELP$MESSAGE(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS REWARDS &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--",
            " ",
            " &7&l» &f/&bnexus reward &3<nexus> &blist&f.",
            " &7&l» &f/&bnexus reward &3<nexus> &badd &3<[Begin]-[End]> <command>&f.",
            " &7&l» &f/&bnexus reward &3<nexus> &bdelete &3<id>&f.",
            " ",
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS PHASE &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--"
    )),

    COMMAND_REWARD_LIST_HEADER(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS REWARDS &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--",
            "&b%nexus_name% &3nexus rewards",
            " "
    )),
    COMMAND_REWARD_LIST_REWARD(Arrays.asList(
            "%nexus_reward_id%.",
            "    &ePosition interval: &b%nexus_reward_interval_begin% &7to &b%nexus_reward_interval_end%",
            "    &eCommands: "
    )),

    COMMAND_REWARD_LIST_REWARD$COMMAND("     &f- %nexus_reward_command%"),

    COMMAND_REWARD_LIST_FOOTER(
            " "
    ),

    COMMAND_REWARD_ADD_SYNTAX("%prefix% &cError syntax, please use: /nexus reward <nexus> add <[Begin]-[End]> <command>"),
    COMMAND_REWARD_ADD_SUCCESS("%prefix% &fYou have successfully added a new reward to the nexus &b%nexus_name%&f."),

    COMMAND_REWARD_DELETE_SYNTAX("%prefix% &cError syntax, please use: /nexus reward <nexus> delete <id>"),
    COMMAND_REWARD_DELETE_SUCCESS("%prefix% &fYou have successfully deleted the reward to the nexus &b%nexus_name%&f."),
    ;

    private List<String> message;
    private final String path;

    private final TheSyncTowers plugin;

    Tl(String message) {
        this.message = Collections.singletonList(message);
        this.path = this.name().replace("_", ".").replace("$", "-");

        this.plugin = JavaPlugin.getPlugin(TheSyncTowers.class);
    }

    Tl(List<String> listDefaultMessages) {
        this.message = listDefaultMessages;
        this.path = this.name().replace("_", ".").replace("$", "-");
        this.plugin = JavaPlugin.getPlugin(TheSyncTowers.class);
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public static void init(FileConfiguration fileConfiguration) {

    }

    public List<String> getMessage() {
        return message;
    }

    public boolean isList() {
        return message.size() != 1;
    }


    public static String addTowerSyncPlaceholder(TowerSync towerSync, String toReplace) {
        return toReplace.replace("%towersync_name%", towerSync.getName())
                .replace("%towersync_timeinterval%", towerSync.getTimeInterval() + "")
                .replace("%towersync_type%", towerSync.getType().name() + "")
                .replace("%towersync_goal%", towerSync.getValueToWin() + "")
                .replace("%towersync_timebeforeend%", towerSync.getTimeBeforeEnd() + "")
                .replace("%towersync_towerscount%", towerSync.getTowers().size() + "")
                ;
    }

    public static String addRunningTowerSyncPlaceholder(RunningTowerSync runningTowerSync, String toReplace) {
        String replaced = addTowerSyncPlaceholder(runningTowerSync.getTowerSync(), toReplace);
        return replaced
                .replace("%runningtowersync_health%", "")
                .replace("%runningtowersync_timeremaining%", runningTowerSync.getTimeBeforeEnd() + "")
                ;
    }

    public static List<String> addRunningTowerSyncPlaceholder(RunningTowerSync runningTowerSync, List<String> toReplace) {
        return toReplace.stream().map(s -> addRunningTowerSyncPlaceholder(runningTowerSync, s)).collect(Collectors.toList());
    }

    public static String addTowerPlaceholder(Tower tower, String toReplace) {
        return toReplace
                .replace("%tower_material%", tower.getMaterial().name())
                ;
    }

    public static List<String> addTopPlaceholder(RunningTowerSync runningTowerSync, Player player, List<String> toReplace) {
        return toReplace.stream().map(s -> addTopPlaceholder(runningTowerSync, player, s)).collect(Collectors.toList());
    }

    public static String addTopPlaceholder(RunningTowerSync runningTowerSync, Player player, String toReplace) {
        String replaced = toReplace;
        LinkedList<IParticipant> participants = new LinkedList<>(runningTowerSync.getTopParticipants().keySet());

        Optional<IParticipant> participant = runningTowerSync.getParticipantByName(player.getName());
        if (participant.isPresent() && participants.contains(participant.get()))
            replaced = replaced.replace("%towersync_player_top%", participants.indexOf(participant.get()) + 1 + "");
        else
            replaced = replaced.replace("%towersync_player_top%", Tl.SCOREBOARD_TOP$EMPTY.getMessage().get(0));

        for (int i = 1; i < 10; i++) {
            if (participants.size() < i) {
                replaced = replaced.replace("%towersync_top_" + i + "%", Tl.SCOREBOARD_TOP$EMPTY.getMessage().get(0))
                        .replace("%towersync_top_" + i + "_points%", "");
            } else {
                replaced = replaced.replace("%towersync_top_" + i + "%", participants.get(i - 1).getParticipantName())
                        .replace("%towersync_top_" + i + "_points%", "" +
                                runningTowerSync.getParticipantPoints().get(participants.get(i - 1))
                        );
            }
        }
        return replaced;
    }

/*    public static void sendConfigMessage(CommandSender sender, Tl tl, INexus nexus, IReward reward, String... replace) {
        HashMap<String, String> replaced = new HashMap<>();
        List<String> replaceList = Arrays.asList(replace);
        int index = 0;
        for (String str : replaceList) {
            index++;
            if (index % 2 == 0) continue;
            replaced.put(str, replaceList.get(index));
        }
        tl.getConfigMessages().forEach(s -> sendConfigMessage(sender, addNexusPlaceholder(nexus, addNexusRewardPlaceholder(reward, s)), replaced));
    }*/

    public static void sendConfigMessage(CommandSender sender, Tl tl, TowerSync towerSync, String... replace) {
        HashMap<String, String> replaced = new HashMap<>();
        List<String> replaceList = Arrays.asList(replace);
        int index = 0;
        for (String str : replaceList) {
            index++;
            if (index % 2 == 0) continue;
            replaced.put(str, replaceList.get(index));
        }
        tl.getMessage().forEach(s -> sendConfigMessage(sender, addTowerSyncPlaceholder(towerSync, s), replaced));
    }

    public static void sendConfigMessage(CommandSender sender, Tl tl, Tower tower, String... replace) {
        HashMap<String, String> replaced = new HashMap<>();
        List<String> replaceList = Arrays.asList(replace);
        int index = 0;
        for (String str : replaceList) {
            index++;
            if (index % 2 == 0) continue;
            replaced.put(str, replaceList.get(index));
        }
        tl.getMessage().forEach(s -> sendConfigMessage(sender, addTowerPlaceholder(tower, s), replaced));
    }

    public static void sendConfigMessage(CommandSender sender, Tl tl, RunningTowerSync runningTowerSync, String... replace) {
        HashMap<String, String> replaced = new HashMap<>();
        List<String> replaceList = Arrays.asList(replace);
        int index = 0;
        for (String str : replaceList) {
            index++;
            if (index % 2 == 0) continue;
            replaced.put(str, replaceList.get(index));
        }
        tl.getMessage().forEach(s -> sendConfigMessage(sender, addRunningTowerSyncPlaceholder(runningTowerSync, s), replaced));
    }

    public static void sendConfigMessage(CommandSender sender, Tl tl, String... replace) {
        HashMap<String, String> replaced = new HashMap<>();
        List<String> replaceList = Arrays.asList(replace);
        int index = 0;
        for (String str : replaceList) {
            index++;
            if (index % 2 == 0) continue;
            replaced.put(str, replaceList.get(index));
        }
        tl.getMessage().forEach(s -> sendConfigMessage(sender, s, replaced));
    }

    public static void sendConfigMessageToPlayers(List<Player> players, Tl tl, String... replace) {
        HashMap<String, String> replaced = new HashMap<>();
        List<String> replaceList = Arrays.asList(replace);
        int index = 0;
        for (String str : replaceList) {
            index++;
            if (index % 2 == 0) continue;
            replaced.put(str, replaceList.get(index));
        }
        players.forEach(player -> tl.getMessage().forEach(s -> sendConfigMessage(player, s, replaced)));
    }

    private static void sendConfigMessage(CommandSender commandSender, String configMessage, HashMap<String, String> replaced) {
        if (commandSender instanceof Player) {
            sendConfigMessage(((Player) commandSender), configMessage, replaced);
        } else {
            String message = ChatColor.translateAlternateColorCodes('&', configMessage.replace("%sender%", commandSender.getName()).replace("%prefix%", Tl.GENERAL_PREFIX.getMessage().get(0)));
            if (message.isEmpty()) return;
            for (Map.Entry<String, String> stringEntry : replaced.entrySet()) {
                message = message.replace(stringEntry.getKey(), stringEntry.getValue());
            }
            commandSender.sendMessage(message);
        }
    }

    private static void sendConfigMessage(Player player, String configMessage, HashMap<String, String> replaced) {
        String message = ChatColor.translateAlternateColorCodes('&', configMessage.replace("%sender%", player.getName()).replace("%prefix%", Tl.GENERAL_PREFIX.getMessage().get(0)));
        if (message.isEmpty()) return;
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
            message = PlaceholderAPI.setPlaceholders(player, message);


        for (Map.Entry<String, String> stringEntry : replaced.entrySet()) {
            message = message.replace(stringEntry.getKey(), stringEntry.getValue());
        }
        if (message.toLowerCase().startsWith("%title%")) {
            if (message.toLowerCase().contains("%subtitle%")) {
                String[] splitted = message.split("%subtitle%");
                TitleUtils.sendTitle(player, 0, 40, 10, splitted[0].replaceAll("(?i)%subtitle%", "").replaceAll("(?i)%title%", ""), splitted[1].replaceAll("(?i)%subtitle%", "").replaceAll("(?i)%title%", ""));
            } else {
                TitleUtils.sendTitle(player, 0, 40, 10, message.replaceAll("(?i)%title%", ""), null);
            }
        } else if (message.toLowerCase().startsWith("%subtitle%")) {
            if (message.toLowerCase().contains("%title%")) {
                String[] splitted = message.split("%title%");
                TitleUtils.sendTitle(player, 0, 40, 10, splitted[0].replaceAll("(?i)%subtitle%", "").replaceAll("(?i)%title%", ""), splitted[1].replaceAll("(?i)%subtitle%", "").replaceAll("(?i)%title%", ""));
            } else {
                TitleUtils.sendTitle(player, 0, 40, 10, message.replaceAll("(?i)%subtitle%", ""), null);
            }
        } else if (message.toLowerCase().startsWith("%actionbar%")) {
            ActionBarUtils.sendActionBar(player, message.replaceAll("(?i)%actionbar%", ""), -1);
        } else {
            player.sendMessage(message);
        }
    }

    public static void sendHelpMessage(CommandSender commandSender) {
        Tl.sendConfigMessage(commandSender, Tl.GENERAL_HELP$MESSAGE);
    }

    @Override
    public String toString() {
        return this.path;
    }
}
