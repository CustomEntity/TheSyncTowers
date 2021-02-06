package fr.customentity.thesynctowers.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.locale.Tl;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Singleton
public class MessagesConfig {

    private FileConfiguration messagesConfig;
    private File messagesFile;

    @Inject private TheSyncTowers plugin;

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            try {
                messagesFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        for(Tl tl : Tl.values()) {
            if(!messagesConfig.contains(tl.toString())) {
                messagesConfig.set(tl.toString(), tl.isList() ? tl.getMessage() : tl.getMessage().get(0));
            }
        }
        save();
    }

    public FileConfiguration get() {
        return messagesConfig;
    }

    public void save() {
        try {
            messagesConfig.save(messagesFile);
        } catch (IOException e) {
        }
    }

    public void reload() {
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
}
