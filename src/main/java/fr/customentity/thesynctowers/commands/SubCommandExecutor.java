package fr.customentity.thesynctowers.commands;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import fr.customentity.thesynctowers.data.TowerSync;

import java.lang.reflect.Method;

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
 * @Date: 25/02/2021
 */

public class SubCommandExecutor {

    private final Object commandInstance;

    private final Method method;
    private final SubCommand subCommand;

    @Inject
    public SubCommandExecutor(Provider<Injector> injector, @Assisted Method method) {
        this.commandInstance = injector.get().getInstance(method.getDeclaringClass());
        this.method = method;
        this.subCommand = method.getAnnotation(SubCommand.class);
    }

    public interface Factory {
        SubCommandExecutor create(Method method);
    }

    public SubCommand getSubCommand() {
        return subCommand;
    }

    public Method getMethod() {
        return method;
    }

    public Object getCommandInstance() {
        return commandInstance;
    }
}
