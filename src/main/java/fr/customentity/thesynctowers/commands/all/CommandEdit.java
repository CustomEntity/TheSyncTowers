package fr.customentity.thesynctowers.commands.all;

import com.google.inject.Inject;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.commands.SubCommand;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.data.TowerSyncManager;
import fr.customentity.thesynctowers.data.tower.Tower;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.permissible.Perm;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Copyright (c) 2021. By CustomEntity
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * @Author: CustomEntity
 * @Date: 18/02/2021
 */
public class CommandEdit extends AbstractSubCommand {

    @Inject
    private TowerSyncManager towerSyncManager;

    @SubCommand(subCommand = "edit", permission = Perm.COMMAND_EDIT)
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length <= 1) {
            this.sendMessage(sender, Tl.COMMAND_EDIT_HELP$MESSAGE);
        } else {
            Optional<TowerSync> towerSyncOptional = this.towerSyncManager.getTowerSyncByName(args[0]);
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
            } else if (arg.equalsIgnoreCase("setgoal")) {
                if (args.length != 3) {
                    this.sendMessage(sender, Tl.COMMAND_EDIT_SET$GOAL_SYNTAX, towerSync);
                    return;
                }
                long i;
                try {
                    i = Long.parseLong(args[2]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, towerSync, "%arg%", arg);
                    return;
                }
                towerSync.setGoal(i);
                this.sendMessage(sender, Tl.COMMAND_EDIT_SET$GOAL_SUCCESS, towerSync);
            } else if (arg.equalsIgnoreCase("settype")) {
                if (args.length != 3) {
                    this.sendMessage(sender, Tl.COMMAND_EDIT_SET$TYPE_SYNTAX, towerSync);
                    return;
                }
                String type = args[2];
                towerSync.setType(type.equalsIgnoreCase("point") ? TowerSync.Type.POINT : TowerSync.Type.TIME);
                this.sendMessage(sender, Tl.COMMAND_EDIT_SET$TYPE_SUCCESS, towerSync);
            } else if (arg.equalsIgnoreCase("settimeinterval")) {
                if (args.length != 3) {
                    this.sendMessage(sender, Tl.COMMAND_EDIT_SET$TIMEINTERVAL_SYNTAX, towerSync);
                    return;
                }
                int i;
                try {
                    i = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, towerSync, "%arg%", arg);
                    return;
                }
                towerSync.setTimeInterval(i);
                this.sendMessage(sender, Tl.COMMAND_EDIT_SET$TIMEINTERVAL_SUCCESS, towerSync);
            } else if (arg.equalsIgnoreCase("tower")) {
                if (args.length == 2) {
                    this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_HELP$MESSAGE);
                    return;
                }
                if (args[2].equalsIgnoreCase("add")) {
                    if (args.length != 4) {
                        this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_ADD_SYNTAX);
                        return;
                    }
                    String name = args[3];

                    if (towerSync.isTowerExisting(name)) {
                        this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_ADD_TOWER$ALREADY$EXISTS, "%arg%", name);
                        return;
                    }

                    Block block = player.getTargetBlock(new HashSet<Material>() {{
                        add(Material.AIR);
                    }}, 5);
                    if (block.getType() == Material.AIR) {
                        this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_ADD_NO$BLOCK$TARGETED, towerSync);
                        return;
                    }


                    towerSync.addTower(name, block.getLocation(), block.getType());
                    block.getWorld().getBlockAt(block.getLocation()).setType(Material.AIR);
                    this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_ADD_SUCCESS, towerSync);
                } else if (args[2].equalsIgnoreCase("remove")) {
                    if (args.length != 4) {
                        this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_REMOVE_SYNTAX);
                        return;
                    }
                    String name = args[3];

                    if (!towerSync.isTowerExisting(name)) {
                        this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_REMOVE_TOWER$NOT$EXISTS, "%arg%", name);
                        return;
                    }

                    towerSync.removeTower(name);
                    this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_REMOVE_SUCCESS, towerSync, "%arg%", name);
                } else if (args[2].equalsIgnoreCase("list")) {
                    List<Tower> towerList = towerSync.getTowers();
                    this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_LIST_HEADER);
                    for (Tower tower : towerList) {
                        this.sendMessage(sender, Tl.COMMAND_EDIT_TOWER_LIST_TOWER, towerSync,
                                "%name%", tower.getName(),
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
