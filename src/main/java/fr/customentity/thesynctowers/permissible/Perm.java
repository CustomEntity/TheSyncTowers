package fr.customentity.thesynctowers.permissible;

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
public enum Perm {

    COMMAND_CREATE("command.create"),
    COMMAND_DELETE("command.delete"),
    COMMAND_EDIT("command.edit"),
    COMMAND_START("command.start"),
    COMMAND_RELOAD("command.reload"),
    COMMAND_LIST("command.list"),
    COMMAND_SCHEDULER("command.scheduler"),
    COMMAND_STOP("command.stop"),
    COMMAND_NOW("command.now"),
    COMMAND_REWARD("command.reward"),
    ;


    private final String permission;

    Perm(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public boolean hasPermission(CommandSender commandSender) {
        return commandSender.hasPermission("tst." + permission) ||
                commandSender.hasPermission("thesynctowers." + permission);
    }
}
