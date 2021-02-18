package fr.customentity.thesynctowers.utils;

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

public class Perms {

    public static boolean has(CommandSender commandSender, Perm perm) {
        return perm.hasPermission(commandSender);
    }

    public static boolean has(CommandSender commandSender, String perm) {
        return commandSender.hasPermission(perm);
    }
}
