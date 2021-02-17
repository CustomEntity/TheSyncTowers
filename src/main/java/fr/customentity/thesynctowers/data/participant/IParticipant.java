package fr.customentity.thesynctowers.data.participant;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import fr.customentity.thesynctowers.settings.Settings;
import org.bukkit.entity.Player;

import java.util.Set;

public interface IParticipant {

    String getParticipantName();

    Set<Player> getPlayerParticipants();

    String getParticipantType();

    static IParticipant getParticipantFromPlayer(Player player) {
        String participantType = Settings.PARTICIPANT_TYPE.getValue();
        switch (participantType) {
            case "PLAYER":
                return new PlayerParticipant(player);
            case "FACTION":
                FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
                if(!fPlayer.hasFaction()) return null;
                return new FactionParticipant(fPlayer.getFaction());
        }
    }
}