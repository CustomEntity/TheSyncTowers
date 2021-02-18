package fr.customentity.thesynctowers.gson;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.gson.adapters.LocationTypeAdapter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.*;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

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
@Singleton
public class GsonManager {

    private final TheSyncTowers plugin;
    private final Gson gson;

    @Inject
    public GsonManager(TheSyncTowers plugin) {
        this.plugin = plugin;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .serializeNulls()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .registerTypeAdapter(Location.class, new LocationTypeAdapter())
                .create();
    }

    public Gson getGson() {
        return gson;
    }

    public File getOrCreateFile(String fileName) throws IOException {
        File f = new File(plugin.getDataFolder(), fileName);
        if (!f.exists()) {
            Bukkit.getLogger().info("Creating new file " + fileName + " !");
            f.createNewFile();
        }
        return f;
    }

    public Object fromJson(File f, Type token) throws FileNotFoundException {
        InputStreamReader inputStreamReader = new InputStreamReader(
                new FileInputStream(f),
                StandardCharsets.UTF_8
        );
        return gson.fromJson(inputStreamReader, token);
    }

    public String toJSON(Object object, Object token) {
        return gson.toJson(object, getTypeFromObject(token));
    }

    public boolean saveJSONToFile(File f, Object toSave, Object token) throws IOException {
        String str = toJSON(toSave, token);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                new FileOutputStream(f),
                StandardCharsets.UTF_8
        );

        outputStreamWriter.write(str);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        return true;
    }

    private Type getTypeFromObject(Object object) {
        return object instanceof Type ? (Type) object : getTypeFromClass(object.getClass());
    }

    private Type getTypeFromClass(Class<?> clazz) {
        return TypeToken.of(clazz).getType();
    }
}
