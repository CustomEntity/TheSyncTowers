package fr.customentity.thesynctowers.permissible;

import org.bukkit.command.CommandSender;

public enum Perm  {

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
