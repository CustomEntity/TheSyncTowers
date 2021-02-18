package fr.customentity.thesynctowers.settings.values;

import fr.customentity.thesynctowers.settings.Setting;
import org.bukkit.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

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

public class IntListSetting implements Setting<List<Integer>> {

    private final String path;
    private final List<Integer> defaultValue;
    private List<Integer> value = new ArrayList<>();

    public IntListSetting(String path, List<Integer> defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }


    @Override
    public String getPath() {
        return path;
    }

    @Override
    public List<Integer> getValue() {
        return value;
    }

    @Override
    public List<Integer> getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setValue(List<Integer> value) {
        this.value = value;
    }

    @Override
    public List<Integer> parse(Configuration config, String path) {
        return config.getIntegerList(path);
    }
}

