package fr.customentity.thesynctowers.commands;

import com.google.inject.Inject;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.permissible.Perm;
import fr.customentity.thesynctowers.utils.Perms;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractSubCommand {

    private final TheSyncTowers plugin;
    private final String commandName;
    private final String permission;
    private final String[] aliases;

    @Inject
    public AbstractSubCommand(TheSyncTowers plugin, String commandName, String permission, String... aliases) {
        this.plugin = plugin;
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

    public TheSyncTowers getPlugin() {
        return plugin;
    }

    protected boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    protected Player getPlayer(CommandSender sender) {
        return (Player) sender;
    }


    protected void sendMessage(CommandSender sender, Tl tl, String... replace) {
        Tl.sendConfigMessage(sender, tl, replace);
    }

    protected void sendMessage(CommandSender sender, Tl tl, TowerSync towerSync,  String... replace) {
        Tl.sendConfigMessage(sender, tl, towerSync, replace);
    }
    protected void sendMessage(CommandSender sender, Tl tl, RunningTowerSync runningTowerSync, String... replace) {
        Tl.sendConfigMessage(sender, tl, runningTowerSync, replace);
    }

}