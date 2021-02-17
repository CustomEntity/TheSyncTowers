package fr.customentity.thesynctowers.commands.all;

import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.locale.Tl;
import org.bukkit.command.CommandSender;

public class CommandReload extends AbstractSubCommand {

    public CommandReload(TheSyncTowers plugin, String commandName, String permission, String... aliases) {
        super(plugin, commandName, permission, aliases);
    }

    @Override
    protected void execute(CommandSender sender, String command, String[] args) {
        //this.getPlugin().reloadPlugin();
        this.getPlugin().getSettings().loadSettings();
        this.getPlugin().getMessagesConfig().setup();

       this.sendMessage(sender, Tl.COMMAND_RELOAD_SUCCESS);
    }
}
