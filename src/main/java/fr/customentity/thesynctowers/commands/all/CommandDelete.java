package fr.customentity.thesynctowers.commands.all;

import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.locale.Tl;
import org.bukkit.command.CommandSender;

public class CommandDelete extends AbstractSubCommand {

    public CommandDelete(TheSyncTowers plugin, String commandName, String permission, String... aliases) {
        super(plugin, commandName, permission, aliases);
    }

    @Override
    protected void execute(CommandSender sender, String command, String[] args) {
        if(args.length == 0) {
            this.sendMessage(sender, Tl.COMMAND_CREATE_SYNTAX);
        } else {

            String name = args[0];
            if(!this.getPlugin().getTowerSyncManager().isTowerSyncExists(name)) {
                this.sendMessage(sender, Tl.GENERAL_SYNCTOWER$NOT$EXISTS, "%arg%", name);
                return;
            }
            this.getPlugin().getTowerSyncManager().deleteTowerSync(name);
            this.sendMessage(sender, Tl.COMMAND_DELETE_SUCCESS, "%arg%", name);
        }
    }
}
