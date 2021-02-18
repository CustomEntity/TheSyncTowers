package fr.customentity.nexus.commands.all;

import fr.customentity.nexus.api.nexus.INexus;
import fr.customentity.nexus.commands.AbstractSubCommand;
import fr.customentity.nexus.data.Nexus;
import fr.customentity.nexus.tl.Tl;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandStop extends AbstractSubCommand {

    public CommandStop(String commandName, String permission, String... aliases) {
        super(commandName, permission, aliases);
    }

    @Override
    protected void execute(CommandSender sender, String command, String[] args) {
        if (args.length == 0) {
            Tl.sendConfigMessage(sender, Tl.COMMAND_STOP_SYNTAX);
        } else {
            String nexusArg = args[0];
            if (!getPlugin().getNexusManager().existNexus(nexusArg)) {
                Tl.sendConfigMessage(sender, Tl.GENERAL_NEXUS$NOT$EXISTS);
                return;
            }

            INexus nexus = plugin.getNexusManager().getNexusByName(nexusArg);
            if(!nexus.isRunning()) {
                this.sendMessage(sender, Tl.GENERAL_NEXUS$NOT$RUNNING, nexus);
                return;
            }
            this.sendMessage(sender, Tl.COMMAND_STOP_SUCCESS, nexus);
            this.getPlugin().getNexusManager().stop(nexus);
        }
    }
}
