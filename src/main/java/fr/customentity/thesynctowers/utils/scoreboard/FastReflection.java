package fr.customentity.thesynctowers.utils.scoreboard;

import org.bukkit.Bukkit;

import java.util.Locale;
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

/**
 * Small reflection class to use CraftBukkit and NMS
 *
 * @author MrMicky
 */
public final class FastReflection {

    public static final String OBC_PACKAGE = "org.bukkit.craftbukkit";
    public static final String NMS_PACKAGE = "net.minecraft.server";

    public static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().substring(OBC_PACKAGE.length() + 1);

    private FastReflection() {
        throw new UnsupportedOperationException();
    }

    public static String nmsClassName(String className) {
        return NMS_PACKAGE + '.' + VERSION + '.' + className;
    }

    public static Class<?> nmsClass(String className) throws ClassNotFoundException {
        return Class.forName(nmsClassName(className));
    }

    public static Optional<Class<?>> nmsOptionalClass(String className) {
        return optionalClass(nmsClassName(className));
    }

    public static String obcClassName(String className) {
        return OBC_PACKAGE + '.' + VERSION + '.' + className;
    }

    public static Class<?> obcClass(String className) throws ClassNotFoundException {
        return Class.forName(obcClassName(className));
    }

    public static Optional<Class<?>> obcOptionalClass(String className) {
        return optionalClass(obcClassName(className));
    }

    public static Optional<Class<?>> optionalClass(String className) {
        try {
            return Optional.of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> E enumValueOf(Class<?> enumClass, String enumName) {
        return Enum.valueOf((Class<E>) enumClass, enumName.toUpperCase(Locale.ROOT));
    }
}