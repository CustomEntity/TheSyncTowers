package fr.customentity.thesynctowers.hook.all;

import fr.customentity.thesynctowers.hook.Hook;
import org.bukkit.Bukkit;

public class FeatherboardHook implements Hook {

    private boolean enabled = false;

    @Override
    public boolean onSetup() {
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("FeatherBoard")){
            this.enabled = true;
            return true;
        }
        return false;
    }

    @Override
    public String getHookName() {
        return "Featherboard";
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
