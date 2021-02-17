package fr.customentity.thesynctowers.data.participant;

import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Set;

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
    public Set<Player> getPlayerParticipants() {
        return Collections.singleton(player);
    }

    @Override
    public String getParticipantType() {
        return "Player";
    }
}