package fr.customentity.thesynctowers.locale;

import fr.customentity.thesynctowers.TheSyncTowers;
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
import java.util.stream.Collectors;

public enum Tl {

    GENERAL_PREFIX("&d&lNexus &7&l» &f"),

    GENERAL_HELP$MESSAGE(Arrays.asList(
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS HELP &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--",
            " ",
            " &7&l» &f/&dnexus create &5<name> &8- &dCreate a new nexus&f.",
            " &7&l» &f/&dnexus delete &5<nexus> &8- &dDelete a nexus&f.",
            " &7&l» &f/&dnexus reload &8- &dReload the plugin&f.",
            " &7&l» &f/&dnexus stop &5<nexus> [...] &8- &dStop a running nexus&f.",
            " &7&l» &f/&dnexus now &5<nexus> [...] &8- &dStart nexuses without cooldown&f.",
            " &7&l» &f/&dnexus start &5<nexus> [...] &8- &dStart multiple nexuses&f.",
            " &7&l» &f/&dnexus list &8- &dShow nexuses list&f.",
            " &7&l» &f/&dnexus edit &5<nexus> &8- &dShow nexuses edit commands&f.",
            " &7&l» &f/&dnexus phase &5<nexus> &8- &dShow nexuses phases commands&f.",
            " &7&l» &f/&dnexus scheduler &8- &dShow nexus scheduler commands&f.",
            " &7&l» &f/&dnexus reward &8- &dEdit nexus rewards &f.",
            " ",
            "&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lBy CustomEntity &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--"
    )),

    GENERAL_NEXUS$ALREADY$EXISTS("%prefix% &cThat nexus already exists !"),
    GENERAL_NEXUS$NOT$EXISTS("%prefix% &cThat nexus doesn't exist !"),
    GENERAL_NEXUS$NOT$RUNNING("%prefix% &cThat nexus is not running !"),

    GENERAL_PHASE$NOT$EXISTS("%prefix% &cThat phase doesn't exist !"),
    GENERAL_REWARD$NOT$EXISTS("%prefix% &cThat reward doesn't exist !"),
    GENERAL_ACTION$NOT$EXISTS("%prefix% &cThat action doesn't exist !"),

    GAME_NEXUS_ON$RELOAD("%prefix% &fThe nexus &d%nexus_name% &fwas stopped by force!"),
    GAME_NEXUS_HEALTH_MESSAGE("%prefix% &fThe nexus is at &d%percent%% &fof its life! Health: &d%runningnexus_health% &4❤"),
    GAME_COOLDOWN_MESSAGE("%prefix% &fThe nexus &d%nexus_name% &fwill start in &d%minutes% minute(s) and %seconds% second(s) &fat &7(&dX: &f%nexus_location_X%, &dY: &f%nexus_location_Y%, &dZ: &f%nexus_location_Z%&7)"),
    GAME_ON$NEXUS$STOPPED$BROADCAST("%prefix% &fThe nexus &d%nexus_name% &fhas been stopped!"),
    GAME_ON$NEXUS$TIMEUP$BROADCAST("%prefix% &fThe nexus %nexus_name% didn't have time to be killed."),
    GAME_ON$NEXUS$WIN$BROADCAST("%prefix% &d%winner% &fdealt the final blow to the &d%nexus_name% &fnexus."),
    GAME_ON$NEXUS$START$BROADCAST("%prefix% &fThe &d%nexus_name% &fnexus appeared in &7(&dX: &f%nexus_location_X%, &dY: &f%nexus_location_Y%, &dZ: &f%nexus_location_Z%&7)"),
    GAME_ON$DAMAGE$NEXUS_FACTION_DOESNT$HAVE$FACTION("%prefix% &cYou have to join a faction to participate !"),
    GAME_ON$DAMAGE$NEXUS_SKYBLOCK_DOESNT$HAVE$ISLAND("%prefix% &cYou have to join an island to participate !"),
    GAME_ON$DAMAGE$NEXUS_GANG_DOESNT$HAVE$GANG("%prefix% &cYou have to join a gang to participate !"),
    GAME_ON$DAMAGE$NEXUS_CLANS_DOESNT$HAVE$GANG("%prefix% &cYou have to join a clan to participate !"),


    SCOREBOARD_TOP$EMPTY("&c✘"),

    COMMAND_NOT$NUMBER("%prefix% &c%arg% is not a number !"),
    COMMAND_NO$PERMISSION("%prefix% &cYou don't have permission to execute that command !"),

    COMMAND_CREATE_SYNTAX("%prefix% &cError syntax, please use: /nexus create <name>"),
    COMMAND_CREATE_SUCCESS("%prefix% &fThe &d%arg% &fnexus has been successfully created !"),

    COMMAND_DELETE_SYNTAX("%prefix% &cError syntax, please use: /nexus delete <name>"),
    COMMAND_DELETE_SUCCESS("%prefix% &fThe &d%arg% &fnexus has been successfully deleted !"),

    COMMAND_LIST_HEADER(Arrays.asList(
            "&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS LIST &5&m--&d&m--&5&m--&d&m--&5&m--",
            " "
    )),
    COMMAND_LIST_NEXUS("&7&l» &d&l%nexus_name% &8&m-&d &5X&7: &5%nexus_location_X% &5Y&7: &5%nexus_location_Y% &5Z&7: &5%nexus_location_Z% &8&m-&d &d&l%nexus_health% &4&l❤"),
    COMMAND_LIST_FOOTER(" "),
    COMMAND_LIST_EMPTY("%prefix% &cThere is no nexus created!"),

    COMMAND_SCHEDULER_HELP$MESSAGE(Arrays.asList(
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS SCHEDULER &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--",
            " ",
            " &7&l» &f/&dnexus scheduler list",
            " &7&l» &f/&dnexus scheduler delete <schedulerID>",
            " &7&l» &f/&dnexus scheduler repeat &5<minute> <nexus> [...]",
            " &7&l» &f/&dnexus scheduler daily &5<time_of_day> <minute> <nexus> [...]",
            " &7&l» &f/&dnexus scheduler weekly &5<day_of_week> <time_of_day> <minute> <nexus> [...]",
            " &7&l» &f/&dnexus scheduler specific &5<month> <day_of_month> <time_of_day> <minute> <nexus> [...]",
            " ",
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS SCHEDULER &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--"
    )),

    COMMAND_SCHEDULER_LIST_HEADER(Arrays.asList(
            "&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lSCHEDULERS LIST &5&m--&d&m--&5&m--&d&m--&5&m--",
            " "
    )),
    COMMAND_SCHEDULER_LIST_SCHEDULER(Arrays.asList("&7&l%id%. &5%scheduler_type% &f- &d%nexuses%",
            "   &7▪ &dMonth: &f%scheduler_month%",
            "   &7▪ &dDay of month: &f%scheduler_dayOfMonth%",
            "   &7▪ &dDay of week: &f%scheduler_dayOfWeek%",
            "   &7▪ &dTime of day: &f%scheduler_timeOfDay%",
            "   &7▪ &dMinute: &f%scheduler_minutes%",
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

    COMMAND_EDIT_HELP$MESSAGE(Arrays.asList(
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS EDIT &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--",
            " ",
            " &7&l» &f/&dnexus edit &5<nexus> &dsettimeup &5<timeup>",
            " &7&l» &f/&dnexus edit &5<nexus> &dsetdisplayname &5<displayname>",
            " &7&l» &f/&dnexus edit &5<nexus> &dsethealth &5<health>",
            " &7&l» &f/&dnexus edit &5<nexus> &dsetlocation",
            " ",
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS EDIT &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--"
    )),

    COMMAND_RELOAD_SUCCESS("%prefix% &aConfig reloaded!"),

    COMMAND_STOP_SYNTAX("%prefix% &cError syntax, please use: /nexus stop <nexus>"),
    COMMAND_STOP_SUCCESS("%prefix% &fThe &d%nexus_name% &fnexus has been successfully stopped !"),


    COMMAND_START_SYNTAX("%prefix% &cError syntax, please use: /nexus start <nexus1> [nexus2] [...]"),
    COMMAND_START_ALREADY$RUNNING("%prefix% &cNexuses %nexuses% is/are already running !"),
    COMMAND_START_NOT$EXISTS("%prefix% &cNexuses %nexuses% doesn't exist !"),
    COMMAND_START_SUCCESS("%prefix% &fNexuses &d%nexuses% &fstarted !"),

    COMMAND_NOW_SYNTAX("%prefix% &cError syntax, please use: /nexus now <nexus1> [nexus2] [...]"),
    COMMAND_NOW_ALREADY$RUNNING("%prefix% &cNexuses %nexuses% is/are already running !"),
    COMMAND_NOW_NOT$EXISTS("%prefix% &cNexuses %nexuses% doesn't exist !"),
    COMMAND_NOW_SUCCESS("%prefix% &fNexuses &d%nexuses% &fstarted !"),

    COMMAND_EDIT_SET$HEALTH_SYNTAX("%prefix% &cError syntax, please use: /nexus edit <nexus> sethealth <health>"),
    COMMAND_EDIT_SET$HEALTH_SUCCESS("%prefix% &fThe health of the nexus &d%nexus_name% &fhas been set !"),

    COMMAND_EDIT_SET$TIME$UP_SYNTAX("%prefix% &cError syntax, please use: /nexus edit <nexus> settimeup <timeup-in-seconds>"),
    COMMAND_EDIT_SET$TIME$UP_SUCCESS("%prefix% &fThe time up of the nexus &d%nexus_name% &fhas been set."),

    COMMAND_EDIT_SET$LOCATION_SUCCESS("%prefix% &fThe location of the nexus &d%nexus_name% &fhas been set."),
    COMMAND_EDIT_SET$LOCATION_SYNTAX("%prefix% &cError syntax, please use: /nexus edit <nexus> setlocation"),

    COMMAND_EDIT_SET$DISPLAYNAME_SUCCESS("%prefix% &fThe display name of the nexus &d%nexus_name% &fhas been changed !"),
    COMMAND_EDIT_SET$DISPLAYNAME_SYNTAX("%prefix% &cError syntax, please use: /nexus edit <nexus> setdisplayname <displayname>"),

    COMMAND_PHASE_HELP$MESSAGE(Arrays.asList(
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS PHASE &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--",
            " ",
            " &7&l» &f/&dnexus phase &5<nexus> &dlist&f.",
            " &7&l» &f/&dnexus phase &5<nexus> &dadd &5<healthstage>&f.",
            " &7&l» &f/&dnexus phase &5<nexus> &ddelete &5<phase>&f.",
            " &7&l» &f/&dnexus phase &5<nexus> &dsethealthstage &5<phase> <healstage>&f.",
            " &7&l» &f/&dnexus phase &5<nexus> &daction &5<add | remove | list>&f.",
            " ",
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS PHASE &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--"
    )),

    COMMAND_PHASE_LIST_HEADER(Arrays.asList(
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS PHASE &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--",
            "&d%nexus_name% &5nexus phase information",
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
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS PHASE &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--",
            " ",
            " &7&l» &f/&dnexus phase &5<nexus> &daction add &5<phase>",
            " &7&l» &f/&dnexus phase &5<nexus> &daction delete &5<phase> <id>",
            " &7&l» &f/&dnexus phase &5<nexus> &daction list &5<phase>",
            " ",
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS PHASE &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--"
    )),

    COMMAND_PHASE_ACTION_ADD_SYNTAX("%prefix% &cError syntax, please use: /nexus phase <nexus> action add <phase>"),
    COMMAND_PHASE_ACTION_ADD_ENTER$CREATING$MODE("%prefix% The next message you enter the chat will define the action."),
    COMMAND_PHASE_ACTION_ADD_LEAVE$CREATING$MODE("%prefix% You just leave the action creating mode."),
    COMMAND_PHASE_ACTION_ADD_SUCCESS("%prefix% &aYou have successfully added a new action to the nexus !"),


    COMMAND_PHASE_ACTION_DELETE_SYNTAX("%prefix% &cError syntax, please use: /nexus phase <nexus> action delete <phase> <id>"),
    COMMAND_PHASE_ACTION_DELETE_SUCCESS("%prefix% &fYou have successfully deleted the action of the phase &d%nexus_phase_id% &fof nexus &d%nexus_name%&f."),

    COMMAND_PHASE_ACTION_LIST_SYNTAX("%prefix% &cError syntax, please use: /nexus phase <nexus> action list <phase>"),
    COMMAND_PHASE_ACTION_LIST_HEADER(Arrays.asList(
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS PHASE &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--",
            "&dActions of phase &5%nexus_phase_id% &dof the nexus &5%nexus_name%&d:",
            " "
    )),

    COMMAND_PHASE_ACTION_LIST_ACTION("&d%nexus_action_id%. &f%nexus_action%"),

    COMMAND_PHASE_ACTION_LIST_FOOTER(Arrays.asList(
            " ",
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS PHASE &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--")),


    COMMAND_REWARD_HELP$MESSAGE(Arrays.asList(
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS REWARDS &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--",
            " ",
            " &7&l» &f/&dnexus reward &5<nexus> &dlist&f.",
            " &7&l» &f/&dnexus reward &5<nexus> &dadd &5<[Begin]-[End]> <command>&f.",
            " &7&l» &f/&dnexus reward &5<nexus> &ddelete &5<id>&f.",
            " ",
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS PHASE &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--"
    )),

    COMMAND_REWARD_LIST_HEADER(Arrays.asList(
            "&5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--&f &d&lNEXUS REWARDS &5&m--&d&m--&5&m--&d&m--&5&m--&d&m--&5&m--",
            "&d%nexus_name% &5nexus rewards",
            " "
    )),
    COMMAND_REWARD_LIST_REWARD(Arrays.asList(
            "%nexus_reward_id%.",
            "    &ePosition interval: &d%nexus_reward_interval_begin% &7to &d%nexus_reward_interval_end%",
            "    &eCommands: "
    )),

    COMMAND_REWARD_LIST_REWARD$COMMAND("     &f- %nexus_reward_command%"),

    COMMAND_REWARD_LIST_FOOTER(
            " "
    ),

    COMMAND_REWARD_ADD_SYNTAX("%prefix% &cError syntax, please use: /nexus reward <nexus> add <[Begin]-[End]> <command>"),
    COMMAND_REWARD_ADD_SUCCESS("%prefix% &fYou have successfully added a new reward to the nexus &d%nexus_name%&f."),

    COMMAND_REWARD_DELETE_SYNTAX("%prefix% &cError syntax, please use: /nexus reward <nexus> delete <id>"),
    COMMAND_REWARD_DELETE_SUCCESS("%prefix% &fYou have successfully deleted the reward to the nexus &d%nexus_name%&f."),
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
