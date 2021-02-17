package fr.customentity.thesynctowers.locale;

import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.utils.ActionBarUtils;
import fr.customentity.thesynctowers.utils.TitleUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.*;

public enum Tl {

    GENERAL_PREFIX("&3&lTST &7&l» &f"),

    COMMAND_CREATE_SYNTAX("%prefix% &cError syntax, please use: /tst create <name>"),
    COMMAND_CREATE_SUCCESS("%prefix% &fThe &b%arg% &ftowersync has been successfully created !"),

    COMMAND_DELETE_SYNTAX("%prefix% &cError syntax, please use: /tst delete <name>"),
    COMMAND_DELETE_SUCCESS("%prefix% &fThe &b%arg% &ftowersync has been successfully deleted !"),

    GENERAL_SYNCTOWER$ALREADY$EXISTS("%prefix% &cThat towersync already exists !"),
    GENERAL_SYNCTOWER$NOT$EXISTS("%prefix% &cThat towersync doesn't exist !"),
    GENERAL_SYNCTOWER$NOT$RUNNING("%prefix% &cThat towersync is not running !"),

    COMMAND_RELOAD_SUCCESS("%prefix% &aConfig reloaded!"),

    GENERAL_HELP$MESSAGE(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m &b&lTheSyncTowers Help &b&m--&3&m--&b&m--&3&m--&b&m--&3&m--",
            " ",
            " &7&l» &f/&btst create &3<name> &8- &bCreate a new towersync&f.",
            " &7&l» &f/&btst delete &3<towersync> &8- &bDelete a towersync&f.",
            " &7&l» &f/&btst reload &8- &bReload the plugin&f.",
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










    COMMAND_STOP_SYNTAX("%prefix% &cError syntax, please use: /nexus stop <nexus>"),
    COMMAND_STOP_SUCCESS("%prefix% &fThe &b%nexus_name% &fnexus has been successfully stopped !"),


    GENERAL_PHASE$NOT$EXISTS("%prefix% &cThat phase doesn't exist !"),
    GENERAL_REWARD$NOT$EXISTS("%prefix% &cThat reward doesn't exist !"),
    GENERAL_ACTION$NOT$EXISTS("%prefix% &cThat action doesn't exist !"),

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


    SCOREBOARD_TOP$EMPTY("&c✘"),

    COMMAND_NOT$NUMBER("%prefix% &c%arg% is not a number !"),
    COMMAND_NO$PERMISSION("%prefix% &cYou don't have permission to execute that command !"),







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



    COMMAND_PHASE_HELP$MESSAGE(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS PHASE &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--",
            " ",
            " &7&l» &f/&bnexus phase &3<nexus> &blist&f.",
            " &7&l» &f/&bnexus phase &3<nexus> &badd &3<healthstage>&f.",
            " &7&l» &f/&bnexus phase &3<nexus> &bdelete &3<phase>&f.",
            " &7&l» &f/&bnexus phase &3<nexus> &bsethealthstage &3<phase> <healstage>&f.",
            " &7&l» &f/&bnexus phase &3<nexus> &baction &3<add | remove | list>&f.",
            " ",
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS PHASE &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--"
    )),

    COMMAND_PHASE_LIST_HEADER(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS PHASE &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--",
            "&b%nexus_name% &3nexus phase information",
            " "
    )),
    COMMAND_PHASE_LIST_PHASE(Arrays.asList(
            "%nexus_phase_id%.",
            "    &eHealh Stage: %nexus_phase_healthstage% &4&l❤"
    )),

    COMMAND_PHASE_LIST_FOOTER(
            " "
    ),

    COMMAND_PHASE_ADD_SUCCESS("%prefix% &fYou have successfully added a new phase to the nexus !"),
    COMMAND_PHASE_ADD_SYNTAX("%prefix% &cError syntax, please use: /nexus phase <nexus> action add <phase>"),

    COMMAND_PHASE_DELETE_SUCCESS("%prefix% &fYou have successfully deleted phase %phase% of nexus %nexus_name%."),
    COMMAND_PHASE_DELETE_SYNTAX("%prefix% &cError syntax, please use: /nexus phase <nexus> delete <phase>"),

    COMMAND_PHASE_SET$HEALTH$STAGE_SUCCESS("%prefix% &fYou have successfully set the phase %phase% health stage of the %nexus_name% nexus to %healthstage%."),
    COMMAND_PHASE_SET$HEALTH$STAGE_SYNTAX("%prefix% &cError syntax, please use: /nexus phase <nexus> sethealthstage <phase> <healstage>"),

    COMMAND_PHASE_ACTION_HELP(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS PHASE &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--",
            " ",
            " &7&l» &f/&bnexus phase &3<nexus> &baction add &3<phase>",
            " &7&l» &f/&bnexus phase &3<nexus> &baction delete &3<phase> <id>",
            " &7&l» &f/&bnexus phase &3<nexus> &baction list &3<phase>",
            " ",
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS PHASE &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--"
    )),

    COMMAND_PHASE_ACTION_ADD_SYNTAX("%prefix% &cError syntax, please use: /nexus phase <nexus> action add <phase>"),
    COMMAND_PHASE_ACTION_ADD_ENTER$CREATING$MODE("%prefix% The next message you enter the chat will define the action."),
    COMMAND_PHASE_ACTION_ADD_LEAVE$CREATING$MODE("%prefix% You just leave the action creating mode."),
    COMMAND_PHASE_ACTION_ADD_SUCCESS("%prefix% &aYou have successfully added a new action to the nexus !"),


    COMMAND_PHASE_ACTION_DELETE_SYNTAX("%prefix% &cError syntax, please use: /nexus phase <nexus> action delete <phase> <id>"),
    COMMAND_PHASE_ACTION_DELETE_SUCCESS("%prefix% &fYou have successfully deleted the action of the phase &b%nexus_phase_id% &fof nexus &b%nexus_name%&f."),

    COMMAND_PHASE_ACTION_LIST_SYNTAX("%prefix% &cError syntax, please use: /nexus phase <nexus> action list <phase>"),
    COMMAND_PHASE_ACTION_LIST_HEADER(Arrays.asList(
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS PHASE &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--",
            "&bActions of phase &3%nexus_phase_id% &bof the nexus &3%nexus_name%&b:",
            " "
    )),

    COMMAND_PHASE_ACTION_LIST_ACTION("&b%nexus_action_id%. &f%nexus_action%"),

    COMMAND_PHASE_ACTION_LIST_FOOTER(Arrays.asList(
            " ",
            "&3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--&f &b&lNEXUS PHASE &3&m--&b&m--&3&m--&b&m--&3&m--&b&m--&3&m--")),


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


    private final List<String> defaultMessage;
    private final String path;

    private final TheSyncTowers plugin;

    Tl(String defaultMessage) {
        this.defaultMessage = Collections.singletonList(defaultMessage);
        this.path = this.name().replace("_", ".").replace("$", "-");

        this.plugin = JavaPlugin.getPlugin(TheSyncTowers.class);
    }

    Tl(List<String> listDefaultMessages) {
        this.defaultMessage = listDefaultMessages;
        this.path = this.name().replace("_", ".").replace("$", "-");
        this.plugin = JavaPlugin.getPlugin(TheSyncTowers.class);
    }

    public List<String> getMessage() {
        return defaultMessage;
    }

    public List<String> getConfigMessages() {
        String serialized = this.toString();
        return plugin.getMessagesConfig().get().isList(serialized) ? plugin.getMessagesConfig().get().getStringList(serialized) : Collections.singletonList(plugin.getMessagesConfig().get().getString(serialized));
    }

    public boolean isList() {
        return defaultMessage.size() != 1;
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
        tl.getConfigMessages().forEach(s -> sendConfigMessage(sender, s, replaced));
    }
    public static void sendConfigMessage(CommandSender sender, Tl tl, RunningTowerSync runningTowerSync, String... replace) {
        //TODO: REPLACE
        sendConfigMessage(sender, tl, "");
    }

    public static void sendConfigMessage(CommandSender sender, Tl tl, TowerSync towerSync, String... replace) {
        //TODO: REPLACE
        sendConfigMessage(sender, tl, "");
    }

    private static void sendConfigMessage(CommandSender commandSender, String configMessage, HashMap<String, String> replaced) {
        if (commandSender instanceof Player) {
            sendConfigMessage(((Player) commandSender), configMessage, replaced);
        } else {
            String message = ChatColor.translateAlternateColorCodes('&', configMessage.replace("%sender%", commandSender.getName()).replace("%prefix%", Tl.GENERAL_PREFIX.getConfigMessages().get(0)));
            if (message.isEmpty()) return;
            for (Map.Entry<String, String> stringEntry : replaced.entrySet()) {
                message = message.replace(stringEntry.getKey(), stringEntry.getValue());
            }
            commandSender.sendMessage(message);
        }
    }

    private static void sendConfigMessage(Player player, String configMessage, HashMap<String, String> replaced) {
        String message = ChatColor.translateAlternateColorCodes('&', configMessage.replace("%sender%", player.getName()).replace("%prefix%", Tl.GENERAL_PREFIX.getConfigMessages().get(0)));
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
