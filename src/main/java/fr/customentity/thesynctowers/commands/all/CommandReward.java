package fr.customentity.thesynctowers.commands.all;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.commands.SubCommand;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.data.TowerSyncManager;
import fr.customentity.thesynctowers.data.reward.Reward;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.permissible.Perm;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.CommandSender;

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
 * @Date: 26/02/2021
 */

public class CommandReward extends AbstractSubCommand {

    @Inject
    private TowerSyncManager towerSyncManager;

    @SubCommand(subCommand = "reward", permission = Perm.COMMAND_REWARD)
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            this.sendMessage(sender, Tl.COMMAND_REWARD_HELP$MESSAGE);
        } else {
            if(args.length == 1) {
                this.sendMessage(sender, Tl.COMMAND_REWARD_HELP$MESSAGE);
                return;
            }
            String towersyncArg = args[0];
            Optional<TowerSync> towerSyncOptional = this.towerSyncManager.getTowerSyncByName(towersyncArg);
            if(!towerSyncOptional.isPresent()) {
                this.sendMessage(sender, Tl.GENERAL_TOWERSYNC$NOT$EXISTS);
                return;
            }
            TowerSync towerSync = towerSyncOptional.get();
            String arg = args[1];
            if(arg.equalsIgnoreCase("add")) {
                if (args.length < 4) {
                    this.sendMessage(sender, Tl.COMMAND_REWARD_ADD_SYNTAX, towerSync);
                    return;
                }
                String intervalStr = args[2];

                int begin;
                int end;

                if(intervalStr.contains("-")) {
                    String[] splitted = intervalStr.split("-");

                    if(!NumberUtils.isNumber(splitted[0]) || !NumberUtils.isNumber(splitted[1])) {
                        this.sendMessage(sender, Tl.COMMAND_REWARD_ADD_SYNTAX, towerSync);
                        return;
                    }
                    begin = Integer.parseInt(splitted[0]);
                    end = Integer.parseInt(splitted[1]);
                } else {
                    begin = Integer.parseInt(intervalStr);
                    end = Integer.parseInt(intervalStr);
                }

                StringBuilder message = new StringBuilder();
                for (int i = 3; i < args.length; i++) {
                    if (!message.toString().equals("")) message.append(" ");
                    message.append(args[i]);
                }

                boolean found = false;
                for(Reward reward : towerSync.getRewards()) {
                    if(reward.getBegin() == begin && reward.getEnd() == end) {
                        reward.getCommands().add(message.toString());
                        found = true;
                        break;
                    }
                }

                if(!found)
                    towerSync.getRewards().add(new Reward(begin, end, Sets.newHashSet(message.toString())));
                this.sendMessage(sender, Tl.COMMAND_REWARD_ADD_SUCCESS, towerSync);
            } else if(arg.equalsIgnoreCase("delete")) {
                if (args.length != 3) {
                    this.sendMessage(sender, Tl.COMMAND_REWARD_DELETE_SYNTAX, towerSync);
                    return;
                }
                int rewardId;
                try {
                    rewardId = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, towerSync, "%arg%", args[2]);
                    return;
                }

                try {
                    towerSync.getRewards().remove(rewardId - 1);
                } catch (IndexOutOfBoundsException e) {
                    this.sendMessage(sender, Tl.GENERAL_REWARD$NOT$EXISTS, towerSync);
                    return;
                }
                this.sendMessage(sender, Tl.COMMAND_REWARD_DELETE_SUCCESS, towerSync);
            } else if(arg.equalsIgnoreCase("list")) {
                this.sendMessage(sender, Tl.COMMAND_REWARD_LIST_HEADER, towerSync);

                int i = 1;
                for(Reward reward : towerSync.getRewards()) {
                    this.sendMessage(sender, Tl.COMMAND_REWARD_LIST_REWARD, towerSync, reward, "%towersync_reward_id%", i + "");
                    for(String cmd : reward.getCommands()) {
                        this.sendMessage(sender, Tl.COMMAND_REWARD_LIST_REWARD$COMMAND, towerSync, reward, "%towersync_reward_command%", cmd);
                    }
                    i++;
                }
                this.sendMessage(sender, Tl.COMMAND_REWARD_LIST_FOOTER, towerSync);
            }
        }
    }
}
