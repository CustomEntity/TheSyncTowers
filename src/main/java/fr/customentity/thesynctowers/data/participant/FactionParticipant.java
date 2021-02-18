package fr.customentity.thesynctowers.data.participant;

import com.massivecraft.factions.Faction;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class FactionParticipant implements IParticipant {

    private final Faction faction;

    public FactionParticipant(Faction faction) {
        this.faction = faction;
    }

    public Faction getFaction() {
        return faction;
    }

    @Override
    public String getParticipantName() {
        return faction.getTag();
    }

    @Override
    public List<Player> getPlayerParticipants() {
        return faction.getOnlinePlayers();
    }

    @Override
    public String getParticipantType() {
        return "Faction";
    }
}