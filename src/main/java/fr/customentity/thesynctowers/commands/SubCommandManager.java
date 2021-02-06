package fr.customentity.thesynctowers.commands;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.permissions.Perm;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.*;

@Singleton
public class SubCommandManager implements CommandExecutor {

    private final TheSyncTowers plugin;
    private final Set<AbstractSubCommand> subCommandSet;

    @Inject
    public SubCommandManager(TheSyncTowers plugin) {
        this.plugin = plugin;
        this.subCommandSet = new HashSet<>();

        Objects.requireNonNull(plugin.getCommand("thesynctowers")).setExecutor(this);
        Objects.requireNonNull(plugin.getCommand("thesynctowers")).setAliases(Collections.singletonList("tst"))
    }

    public void registerCommands() {
        subCommandSet.add(new CommandCreate("create", Perm.COMMAND_CREATE.getPermission()));
        subCommandSet.add(new CommandDelete("delete", Perm.COMMAND_DELETE.getPermission()));
        subCommandSet.add(new CommandEdit("edit", Perm.COMMAND_EDIT.getPermission()));
        subCommandSet.add(new CommandList("list", Perm.COMMAND_LIST.getPermission()));
        subCommandSet.add(new CommandStart("start", Perm.COMMAND_START.getPermission()));
        subCommandSet.add(new CommandScheduler("scheduler", Perm.COMMAND_SCHEDULER.getPermission()));
        subCommandSet.add(new CommandReload("reload", Perm.COMMAND_RELOAD.getPermission()));
        subCommandSet.add(new CommandNow("now", Perm.COMMAND_NOW.getPermission()));
        subCommandSet.add(new CommandStop("stop", Perm.COMMAND_STOP.getPermission()));
        subCommandSet.add(new CommandReward("reward", Perm.COMMAND_REWARD.getPermission()));
        subCommandSet.add(new CommandVersion("version", null));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            Tl.sendHelpMessage(sender);
        } else {
            Optional<fr.customentity.nexus.commands.AbstractSubCommand> optionalSubCommand = subCommandSet.stream().filter(subCommand -> subCommand.getCommandName().equalsIgnoreCase(args[0])).findFirst();
            if (optionalSubCommand.isPresent()) {
                fr.customentity.nexus.commands.AbstractSubCommand subCommand = optionalSubCommand.get();
                if (subCommand.getPermission() != null && !Perms.has(sender, subCommand.getPermission())) {
                    Tl.sendConfigMessage(sender, Tl.COMMAND_NO$PERMISSION);
                    return true;
                }

                subCommand.setPlugin(plugin);
                subCommand.execute(sender, label, Arrays.copyOfRange(args, 1, args.length));
                return true;
            } else {
                Tl.sendConfigMessage(sender, Tl.GENERAL_HELP$MESSAGE);
            }
        }
        return false;
    }
}
