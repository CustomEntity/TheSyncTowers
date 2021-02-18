package fr.customentity.thesynctowers.commands.all;

import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.data.tower.Tower;
import fr.customentity.thesynctowers.locale.Tl;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
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
public class CommandEdit extends AbstractSubCommand {

    /*
    TODO: ADD EDIT TYPE AND VALUE TO WIN

    * */

    public CommandEdit(TheSyncTowers plugin, String commandName, String permission, String... aliases) {
        super(plugin, commandName, permission, aliases);
    }

    @Override
    protected void execute(CommandSender sender, String command, String[] args) {
        Player player = (Player) sender;
        if (args.length <= 1) {
            this.sendMessage(sender, Tl.COMMAND_EDIT_HELP$MESSAGE);
        } else {
            Optional<TowerSync> towerSyncOptional = this.getPlugin().getTowerSyncManager().getTowerSyncByName(args[0]);
            if (!towerSyncOptional.isPresent()) {
                this.sendMessage(sender, Tl.GENERAL_TOWERSYNC$NOT$EXISTS);
                return;
            }
            TowerSync towerSync = towerSyncOptional.get();

            String arg = args[1];
            if (arg.equalsIgnoreCase("settimebeforeend")) {
                if (args.length != 3) {
                    this.sendMessage(sender, Tl.COMMAND_EDIT_SET$TIME$BEFORE$END_SYNTAX, towerSync);
                    return;
                }
                int i;
                try {
                    i = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, towerSync, "%arg%", arg);
                    return;
                }
                towerSync.setTimeBeforeEnd(i);
                this.sendMessage(sender, Tl.COMMAND_EDIT_SET$TIME$BEFORE$END_SUCCESS, towerSync);
            } else if (arg.equalsIgnoreCase("tower")) {
                if (args.length == 2) {
                    this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_HELP$MESSAGE);
                    return;
                }
                if (args[2].equalsIgnoreCase("add")) {
                    Block block = player.getTargetBlock(new HashSet<Material>() {{
                        add(Material.AIR);
                    }}, 5);
                    if (block.getType() == Material.AIR) {
                        this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_ADD_NO$BLOCK$TARGETED, towerSync);
                        return;
                    }

                    block.getWorld().getBlockAt(block.getLocation()).setType(Material.AIR);

                    towerSync.addTower(block.getLocation(), block.getType());
                    this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_ADD_SUCCESS, towerSync);
                } else if (args[2].equalsIgnoreCase("remove")) {
                    if (args.length != 4) {
                        this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_REMOVE_SYNTAX);
                        return;
                    }
                    int id;
                    try {
                        id = Integer.parseInt(args[3]);
                    } catch (NumberFormatException ignored) {
                        this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[3]);
                        return;
                    }

                    if (id + 1 > towerSync.getTowers().size()) {
                        this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_REMOVE_INCORRECT$ID, "%arg%", id + "");
                        return;
                    }

                    towerSync.removeTower(id);
                    this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_REMOVE_SUCCESS, towerSync, "%arg%", id + "");
                } else if (args[2].equalsIgnoreCase("list")) {
                    List<Tower> towerList = towerSync.getTowers();
                    this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_LIST_HEADER);
                    for (Tower tower : towerList) {
                        this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_LIST_TOWER, towerSync,
                                "%id%", towerList.indexOf(tower) + "",
                                "%X%", tower.getLocation().getBlockX() + "",
                                "%Y%", tower.getLocation().getBlockY() + "",
                                "%Z%", tower.getLocation().getBlockZ() + "",
                                "%material%", tower.getMaterial().name());
                    }
                    this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_LIST_FOOTER);
                }
            } else {
                this.sendMessage(sender, Tl.COMMAND_EDIT_HELP$MESSAGE);
            }
        }
    }
}
