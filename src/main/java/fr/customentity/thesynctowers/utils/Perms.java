package fr.customentity.thesynctowers.utils;

import fr.customentity.thesynctowers.permissible.Perm;
import org.bukkit.command.CommandSender;

public class Perms {

    public static boolean has(CommandSender commandSender, Perm perm) {
        return perm.hasPermission(commandSender);
    }

    public static boolean has(CommandSender commandSender, String perm) {
        return commandSender.hasPermission(perm);
    }
}
