package fr.customentity.thesynctowers.commands;

import fr.customentity.nexus.NexusPlugin;
import fr.customentity.nexus.api.nexus.INexus;
import fr.customentity.nexus.api.nexus.phase.IPhase;
import fr.customentity.nexus.api.nexus.reward.IReward;
import fr.customentity.nexus.permissions.Perm;
import fr.customentity.nexus.tl.Tl;
import fr.customentity.nexus.utils.Perms;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractSubCommand {

    protected NexusPlugin plugin;

    private final String commandName;
    private final String permission;
    private final String[] aliases;

    public AbstractSubCommand(String commandName, String permission, String... aliases) {
        this.commandName = commandName;
        this.permission = permission;
        this.aliases = aliases;
    }

    protected abstract void execute(CommandSender sender, String command, String[] args);

    protected boolean hasPermission(CommandSender sender, String permission) {
        if (sender instanceof ConsoleCommandSender || sender.isOp())
            return true;

        if (!Perms.has(sender, permission)) {
            Tl.sendConfigMessage(sender, Tl.COMMAND_NO$PERMISSION);
            return true;
        }

        return false;
    }

    protected boolean hasPermission(CommandSender sender, Perm permission) {
        return hasPermission(sender, permission.getPermission());
    }

    public String getCommandName() {
        return commandName;
    }

    public String getPermission() {
        return permission;
    }

    public String[] getAliases() {
        return aliases;
    }

    public NexusPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(NexusPlugin plugin) {
        this.plugin = plugin;
    }

    protected boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    protected Player getPlayer(CommandSender sender) {
        return (Player) sender;
    }

    protected void sendMessage(CommandSender sender, Tl tl, INexus nexus, IReward reward, String... replace) {
        Tl.sendConfigMessage(sender, tl, nexus, reward, replace);
    }

    protected void sendMessage(CommandSender sender, Tl tl, String... replace) {
        Tl.sendConfigMessage(sender, tl, replace);
    }

    protected void sendMessage(CommandSender sender, Tl tl, INexus nexus, String... replace) {
        Tl.sendConfigMessage(sender, tl, nexus, replace);
    }

    protected void sendMessage(CommandSender sender, Tl tl, INexus nexus, IPhase phase, String... replace) {
        Tl.sendConfigMessage(sender, tl, nexus, phase, replace);
    }
}