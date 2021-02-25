package fr.customentity.thesynctowers.commands.all;

import com.google.inject.Inject;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.commands.SubCommand;
import fr.customentity.thesynctowers.data.TowerSyncManager;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.permissible.Perm;
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
public class CommandDelete extends AbstractSubCommand {

    @Inject private TowerSyncManager towerSyncManager;

    @SubCommand(subCommand = "delete", permission = Perm.COMMAND_DELETE)
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            this.sendMessage(sender, Tl.COMMAND_CREATE_SYNTAX);
        } else {
            String name = args[0];
            if (!this.towerSyncManager.isTowerSyncExists(name)) {
                this.sendMessage(sender, Tl.GENERAL_TOWERSYNC$NOT$EXISTS, "%arg%", name);
                return;
            }
            this.towerSyncManager.deleteTowerSync(name);
            this.sendMessage(sender, Tl.COMMAND_DELETE_SUCCESS, "%arg%", name);
        }
    }
}
