package fr.customentity.thesynctowers.hook;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;
import fr.customentity.thesynctowers.hook.all.FeatherboardHook;
import fr.customentity.thesynctowers.hook.all.TitleManagerHook;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@Singleton
public class HookManager  {

    @Inject private TheSyncTowers plugin;

    private final Map<Class<? extends Hook>, Hook> hooks = new HashMap<>();

    public Map<Class<? extends Hook>, Hook> getHooks() {
        return hooks;
    }

    public void onEnable() {
        this.registerHook(FeatherboardHook.class, new FeatherboardHook());
        this.registerHook(TitleManagerHook.class, new TitleManagerHook());

        hooks.forEach((aClass, hook) -> {
            boolean enabled = hook.onSetup();
            plugin.getLogger().log(Level.INFO, hook.getHookName() + " hook: " + (enabled ? "Enabled" : "Disabled"));
        });
    }

    public void onDisable() {
    }

    public void registerHook(Class<? extends Hook> hookClass, Hook hook) {
        hooks.put(hookClass, hook);
    }

    public boolean isHookEnabled(Class<? extends Hook> hookClass) {
        return hooks.get(hookClass)!= null && hooks.get(hookClass).isEnabled();
    }
}
