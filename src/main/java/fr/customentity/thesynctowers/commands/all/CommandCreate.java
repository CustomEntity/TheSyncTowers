package fr.customentity.thesynctowers.commands.all;

import com.google.inject.Inject;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.commands.SubCommand;
import fr.customentity.thesynctowers.data.TowerSync;
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
public class CommandCreate extends AbstractSubCommand {

    @Inject private TowerSyncManager towerSyncManager;

    @SubCommand(subCommand = "create", permission = Perm.COMMAND_CREATE)
    public void execute(CommandSender sender, String[] args) {
        if (!this.isPlayer(sender)) return;
        if (args.length == 0) {
            Tl.sendConfigMessage(sender, Tl.COMMAND_CREATE_SYNTAX);
        } else {
            String name = args[0];
            if (this.towerSyncManager.isTowerSyncExists(name)) {
                Tl.sendConfigMessage(sender, Tl.GENERAL_TOWERSYNC$ALREADY$EXISTS, "%arg%", name);
                return;
            }
            this.towerSyncManager.createTowerSync(TowerSync.Type.TIME, name, 0,
                    300, 5000);
            Tl.sendConfigMessage(sender, Tl.COMMAND_CREATE_SUCCESS, "%arg%", name);
        }
    }
}
