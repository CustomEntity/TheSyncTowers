package fr.customentity.thesynctowers.commands.all;

import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.locale.Tl;
import org.bukkit.command.CommandSender;

/**
 *  Copyright (c) 2021. By CustomEntity
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * @Author: CustomEntity
 * @Date: 18/02/2021
 *
 */
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
