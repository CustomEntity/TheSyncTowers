package fr.customentity.thesynctowers.data.participant;

import org.bukkit.entity.Player;

import java.util.Collections;
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
public class PlayerParticipant implements IParticipant {

    private final Player player;

    public PlayerParticipant(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String getParticipantName() {
        return player.getName();
    }

    @Override
    public List<Player> getPlayerParticipants() {
        return Collections.singletonList(player);
    }

    @Override
    public String getParticipantType() {
        return "Player";
    }
}