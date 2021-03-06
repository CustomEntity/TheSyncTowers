package fr.customentity.thesynctowers.data.tower;

import com.google.common.collect.Table;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import fr.customentity.thesynctowers.data.EndReason;
import fr.customentity.thesynctowers.data.RunningTowerSync;
import fr.customentity.thesynctowers.data.TowerSync;
import fr.customentity.thesynctowers.data.participant.IParticipant;
import fr.customentity.thesynctowers.locale.Tl;
import fr.customentity.thesynctowers.tasks.SynchronizationTask;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;

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
public class Tower {


    private String name;
    private Location location;
    private Material material;

    @Inject
    public Tower(@Assisted String name, @Assisted Location location, @Assisted Material material) {
        this.name = name;
        this.location = location;
        this.material = material;
    }


    public interface Factory {
        Tower create(String name, Location location, Material material);
    }


    public Location getLocation() {
        return location;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }
}
