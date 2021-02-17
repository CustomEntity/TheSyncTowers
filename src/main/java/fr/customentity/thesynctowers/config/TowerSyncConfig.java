package fr.customentity.thesynctowers.config;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.data.TowerSync;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public class TowerSyncConfig {

    private File towerSyncsFile;

    @Inject private TheSyncTowers plugin;


    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        try {
            towerSyncsFile = plugin.getGsonManager().getOrCreateFile("towersyncs.json");
        } catch (IOException e) {
            plugin.getLogger().log(Level.WARNING, "Error while creating towersyncs file !");
            e.printStackTrace();
        }
    }

    public void loadTowerSyncs() {
        Type type = new TypeToken<HashSet<TowerSync>>() {
        }.getType();
        try {
            Set<TowerSync> nexusSet = (Set<TowerSync>) plugin.getGsonManager().fromJson(towerSyncsFile, type);
            if (nexusSet != null) {
                plugin.getTowerSyncManager().setTowerSyncs(new HashSet<>(nexusSet));
            }
        } catch (IOException e) {
            plugin.getLogger().log(Level.WARNING, "Error while loading towersyncs from towersyncs file !");
            e.printStackTrace();
        }
    }

    public void saveTowerSyncs() {
        Type type = new TypeToken<HashSet<TowerSync>>() {
        }.getType();
        try {
            if (plugin.getGsonManager().saveJSONToFile(towerSyncsFile, plugin.getTowerSyncManager().getTowerSyncs(), type)) {
                plugin.getLogger().log(Level.WARNING, "All TowerSync saved !");
            }
        } catch (IOException e) {
            plugin.getLogger().log(Level.WARNING, "Error while saving towersyncs in towersyncs file !");
            e.printStackTrace();
        }
    }
}
