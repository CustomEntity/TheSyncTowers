package fr.customentity.thesynctowers.commands.all;

import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.locale.Tl;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCreate extends AbstractSubCommand {

    public CommandCreate(TheSyncTowers plugin, String commandName, String permission, String... aliases) {
        super(plugin, commandName, permission, aliases);
    }

    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
        if(!this.isPlayer(sender))return;
        Player player = this.getPlayer(sender);
        if(args.length == 0) {
            Tl.sendConfigMessage(sender, Tl.COMMAND_CREATE_SYNTAX);
        } else {
            String name = args[0];
            if(this.getPlugin().getTowerSyncManager().isTowerSyncExists(name)) {
                Tl.sendConfigMessage(sender, Tl.GENERAL_SYNCTOWER$ALREADY$EXISTS, "%arg%", name);
                return;
            }
            this.getPlugin().getTowerSyncManager().createTowerSync(name);
            Tl.sendConfigMessage(sender, Tl.COMMAND_CREATE_SUCCESS, "%arg%", name);
        }
    }
}
