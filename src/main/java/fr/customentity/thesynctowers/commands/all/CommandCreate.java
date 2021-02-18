package fr.customentity.thesynctowers.commands.all;

import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.data.TowerSync;
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
public class CommandCreate extends AbstractSubCommand {

    public CommandCreate(TheSyncTowers plugin, String commandName, String permission, String... aliases) {
        super(plugin, commandName, permission, aliases);
    }

    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
        if (!this.isPlayer(sender)) return;
        if (args.length == 0) {
            Tl.sendConfigMessage(sender, Tl.COMMAND_CREATE_SYNTAX);
        } else {
            String name = args[0];
            if (this.getPlugin().getTowerSyncManager().isTowerSyncExists(name)) {
                Tl.sendConfigMessage(sender, Tl.GENERAL_TOWERSYNC$ALREADY$EXISTS, "%arg%", name);
                return;
            }
            this.getPlugin().getTowerSyncManager().createTowerSync(TowerSync.Type.TIME, name, 0, 300, 5000);
            Tl.sendConfigMessage(sender, Tl.COMMAND_CREATE_SUCCESS, "%arg%", name);
        }
    }
}
