package fr.customentity.thesynctowers.hook.all;

import fr.customentity.thesynctowers.hook.Hook;
import org.bukkit.Bukkit;

public class TitleManagerHook implements Hook {

    private boolean enabled = false;

    @Override
    public boolean onSetup() {
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("TitleManager")){
            this.enabled = true;
            return true;
        }
        return false;
    }

    @Override
    public String getHookName() {
        return "TitleManager";
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

