package fr.customentity.thesynctowers.data.participant;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import fr.customentity.thesynctowers.settings.Settings;
import org.bukkit.entity.Player;

import java.util.List;

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
public interface IParticipant {

    String getParticipantName();

    List<Player> getPlayerParticipants();

    String getParticipantType();

    static IParticipant getParticipantFromPlayer(Player player) {
        String participantType = Settings.PARTICIPANT_TYPE.getValue();
        switch (participantType) {
            case "PLAYER":
                return new PlayerParticipant(player);
            case "FACTION":
                FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
                if (!fPlayer.hasFaction()) return null;
                return new FactionParticipant(fPlayer.getFaction());
        }
        return new PlayerParticipant(player);
    }
}