package fr.customentity.thesynctowers.data.participant;

import com.massivecraft.factions.Faction;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

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
    public Set<Player> getPlayerParticipants() {
        return new HashSet<>(faction.getOnlinePlayers());
    }

    @Override
    public String getParticipantType() {
        return "Faction";
    }
}