package fr.customentity.thesynctowers.commands.all;

import com.google.inject.Inject;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.commands.AbstractSubCommand;
import fr.customentity.thesynctowers.commands.SubCommand;
import fr.customentity.thesynctowers.data.TowerSyncManager;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.permissible.Perm;
import fr.customentity.thesynctowers.scheduler.Scheduler;
import fr.customentity.thesynctowers.scheduler.SchedulerManager;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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

public class CommandScheduler extends AbstractSubCommand {

    @Inject private SchedulerManager schedulerManager;
    @Inject private TowerSyncManager towerSyncManager;

    @SubCommand(subCommand = "scheduler", permission = Perm.COMMAND_SCHEDULER)
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            this.sendMessage(sender, Tl.COMMAND_SCHEDULER_HELP$MESSAGE);
        } else {
            if (args[0].equalsIgnoreCase("list")) {
                if(this.schedulerManager.getSchedulers().size() == 0) {
                    this.sendMessage(sender, Tl.COMMAND_SCHEDULER_LIST_EMPTY);
                    return;
                }

                this.sendMessage(sender, Tl.COMMAND_SCHEDULER_LIST_HEADER);

                int i = 0;
                for (Scheduler scheduler : this.schedulerManager.getSchedulers()) {
                    this.sendMessage(sender, Tl.COMMAND_SCHEDULER_LIST_SCHEDULER, "%scheduler_type%", scheduler.getSchedulerType().name(),
                            "%id%", i + "",
                            "%scheduler_month%", scheduler.getMonth() == -1 ? "§c✘" :scheduler.getMonth()  +"",
                            "%scheduler_dayOfMonth%", scheduler.getDayOfMonth() == -1 ? "§c✘" :scheduler.getDayOfMonth()  + "",
                            "%scheduler_dayOfWeek%", scheduler.getDayOfWeek() == -1 ? "§c✘" :scheduler.getDayOfWeek()  +"",
                            "%scheduler_timeOfDay%", scheduler.getTimeOfDay() == -1 ? "§c✘" :scheduler.getTimeOfDay()  +"",
                            "%scheduler_minutes%", scheduler.getMinutes() == -1 ? "§c✘" :scheduler.getMinutes()  +"",
                            "%towersync_name%", scheduler.getTowersync());
                    i++;
                }

                this.sendMessage(sender, Tl.COMMAND_SCHEDULER_LIST_FOOTER);
            } else if (args[0].equalsIgnoreCase("delete")) {
                if (args.length != 2) {
                    sendMessage(sender, Tl.COMMAND_SCHEDULER_DELETE_SYNTAX);
                    return;
                }
                int schedulerId;
                try {
                    schedulerId = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[1]);
                    return;
                }

                this.schedulerManager.getSchedulers().remove(schedulerId);
                this.sendMessage(sender, Tl.COMMAND_SCHEDULER_DELETE_SUCCESS);
            } else if (args[0].equalsIgnoreCase("repeat")) {
                if (args.length <= 2) {
                    this.sendMessage(sender, Tl.COMMAND_SCHEDULER_REPEAT_SYNTAX);
                    return;
                }
                int minute;
                try {
                    minute = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[1]);
                    return;
                }
                String towersync = args[2];

                if (!this.towerSyncManager.isTowerSyncExists(towersync)) {
                    this.sendMessage(sender, Tl.GENERAL_TOWERSYNC$NOT$EXISTS);
                    return;
                }

                this.schedulerManager.getSchedulers().add(new Scheduler(towersync, Scheduler.SchedulerType.REPEAT, minute));
                this.sendMessage(sender, Tl.COMMAND_SCHEDULER_REPEAT_SUCCESS);
            } else if (args[0].equalsIgnoreCase("daily")) {
                if (args.length <= 3) {
                    this.sendMessage(sender, Tl.COMMAND_SCHEDULER_DAILY_SYNTAX);
                    return;
                }
                int minute;
                try {
                    minute = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[2]);
                    return;
                }

                int timeOfDay;
                try {
                    timeOfDay = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[1]);
                    return;
                }

                String towersync = args[3];

                if (!this.towerSyncManager.isTowerSyncExists(towersync)) {
                    this.sendMessage(sender, Tl.GENERAL_TOWERSYNC$NOT$EXISTS);
                    return;
                }
                this.schedulerManager.getSchedulers().add(new Scheduler(towersync, Scheduler.SchedulerType.DAILY, timeOfDay, minute));
                this.sendMessage(sender, Tl.COMMAND_SCHEDULER_DAILY_SUCCESS);
            } else if (args[0].equalsIgnoreCase("weekly")) {
                if (args.length <= 4) {
                    this.sendMessage(sender, Tl.COMMAND_SCHEDULER_WEEKLY_SYNTAX);
                    return;
                }
                int minute;
                try {
                    minute = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[3]);
                    return;
                }

                int timeOfDay;
                try {
                    timeOfDay = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[2]);
                    return;
                }

                int dayOfWeek;
                try {
                    dayOfWeek = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[1]);
                    return;
                }
                String towersync = args[4];

                if (!this.towerSyncManager.isTowerSyncExists(towersync)) {
                    this.sendMessage(sender, Tl.GENERAL_TOWERSYNC$NOT$EXISTS);
                    return;
                }
                this.schedulerManager.getSchedulers().add(new Scheduler(towersync, Scheduler.SchedulerType.WEEKLY, dayOfWeek, timeOfDay, minute));
                this.sendMessage(sender, Tl.COMMAND_SCHEDULER_WEEKLY_SUCCESS);
            } else if (args[0].equalsIgnoreCase("specific")) {
                if (args.length <= 5) {
                    this.sendMessage(sender, Tl.COMMAND_SCHEDULER_SPECIFIC_SYNTAX);
                    return;
                }
                int minute;
                try {
                    minute = Integer.parseInt(args[4]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[4]);
                    return;
                }

                int timeOfDay;
                try {
                    timeOfDay = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[3]);
                    return;
                }

                int dayOfMonth;
                try {
                    dayOfMonth = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[2]);
                    return;
                }

                int month;
                try {
                    month = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    this.sendMessage(sender, Tl.COMMAND_NOT$NUMBER, "%arg%", args[1]);
                    return;
                }

                String towersync = args[5];

                if (!this.towerSyncManager.isTowerSyncExists(towersync)) {
                    this.sendMessage(sender, Tl.GENERAL_TOWERSYNC$NOT$EXISTS);
                    return;
                }
                this.schedulerManager.getSchedulers().add(new Scheduler(towersync, Scheduler.SchedulerType.SPECIFIC, month, dayOfMonth, 0, timeOfDay, minute));
                this.sendMessage(sender, Tl.COMMAND_SCHEDULER_SPECIFIC_SUCCESS);
            }
        }
    }
}
