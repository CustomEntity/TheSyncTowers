package fr.customentity.thesynctowers.commands;

import com.google.common.reflect.ClassPath;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.locale.Tl;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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
@Singleton
public class SubCommandManager implements CommandExecutor {

    private final TheSyncTowers plugin;
    private final Set<SubCommandExecutor> subCommandSet;

    private final SubCommandExecutor.Factory subCommandExecutorFactory;

    @Inject
    public SubCommandManager(TheSyncTowers plugin, SubCommandExecutor.Factory subCommandExecutorFactory) {
        this.plugin = plugin;
        this.subCommandSet = new HashSet<>();
        this.subCommandExecutorFactory = subCommandExecutorFactory;

        Objects.requireNonNull(plugin.getCommand("thesynctowers")).setExecutor(this);
        Objects.requireNonNull(plugin.getCommand("thesynctowers")).setAliases(Collections.singletonList("tst"));
    }

    public void registerCommands(String packageId) {
        try {
            ClassPath.from(this.getClass().getClassLoader()).getTopLevelClassesRecursive(packageId).forEach(classInfo -> {
                try {
                    Arrays.stream(Class.forName(classInfo.getName()).getMethods())
                            .filter(method -> method.isAnnotationPresent(SubCommand.class))
                            .forEach(this::registerCommand);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerCommand(Method method) {
        SubCommandExecutor subCommandExecutor = this.subCommandExecutorFactory.create(method);
        this.registerCommand(subCommandExecutor);
    }

    private void registerCommand(SubCommandExecutor subCommandExecutor) {
        this.subCommandSet.add(subCommandExecutor);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            Tl.sendHelpMessage(sender);
        } else {
            Optional<SubCommandExecutor> optionalSubCommandExecutor = subCommandSet.stream()
                    .filter(subCommand -> subCommand.getSubCommand().subCommand().equalsIgnoreCase(args[0])).findFirst();
            if (optionalSubCommandExecutor.isPresent()) {
                SubCommandExecutor subCommandExecutor = optionalSubCommandExecutor.get();
                if (subCommandExecutor.getSubCommand().permission() != null &&
                        subCommandExecutor.getSubCommand().permission().getPermission() != null
                        && !subCommandExecutor.getSubCommand().permission().hasPermission(sender)) {
                    Tl.sendConfigMessage(sender, Tl.COMMAND_NO$PERMISSION);
                    return true;
                }

                try {
                    subCommandExecutor.getMethod()
                            .invoke(subCommandExecutor.getCommandInstance(), sender, Arrays.copyOfRange(args, 1, args.length));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                Tl.sendConfigMessage(sender, Tl.GENERAL_HELP$MESSAGE);
            }
        }
        return false;
    }
}
