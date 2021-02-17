package fr.customentity.thesynctowers.commands.all;

import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.locale.Tl;
import org.bukkit.command.CommandSender;

public class CommandList extends AbstractSubCommand {

    public CommandList(TheSyncTowers plugin, String commandName, String permission, String... aliases) {
        super(plugin, commandName, permission, aliases);
    }

    @Override
    protected void execute(CommandSender sender, String command, String[] args) {
        if (this.getPlugin().getTowerSyncManager().getTowerSyncs().size() == 0) {
            this.sendMessage(sender, Tl.COMMAND_LIST_EMPTY);
            return;
        }
        this.sendMessage(sender, Tl.COMMAND_LIST_HEADER);
        this.getPlugin().getTowerSyncManager().getTowerSyncs()
                .forEach(towerSync -> Tl.sendConfigMessage(sender, Tl.COMMAND_LIST_TOWERSYNC, towerSync));
        this.sendMessage(sender, Tl.COMMAND_LIST_FOOTER);
    }
}
