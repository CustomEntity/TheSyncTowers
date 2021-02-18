package fr.customentity.thesynctowers.commands.all;

import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.locale.Tl;
import org.bukkit.command.CommandSender;

import java.util.Optional;

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
public class CommandNow extends AbstractSubCommand {
    public CommandNow(TheSyncTowers plugin, String commandName, String permission, String... aliases) {
        super(plugin, commandName, permission, aliases);
    }


    @Override
    protected void execute(CommandSender sender, String command, String[] args) {
        if (args.length == 0) {
            Tl.sendConfigMessage(sender, Tl.COMMAND_NOW_SYNTAX);
        } else {
            Optional<TowerSync> towerSyncOptional = this.getPlugin().getTowerSyncManager().getTowerSyncByName(args[0]);
            if (!towerSyncOptional.isPresent()) {
                Tl.sendConfigMessage(sender, Tl.COMMAND_NOW_NOT$EXISTS, "%towersync%", args[0]);
                return;
            }

            TowerSync towerSync = towerSyncOptional.get();
            if (towerSync.isRunning()) {
                Tl.sendConfigMessage(sender, Tl.COMMAND_NOW_ALREADY$RUNNING, "%towersync%", towerSync.getName());
                return;
            }
            Tl.sendConfigMessage(sender, Tl.COMMAND_NOW_SUCCESS, "%towersync%", towerSync.getName());
            towerSync.start(true);
        }
    }
}
