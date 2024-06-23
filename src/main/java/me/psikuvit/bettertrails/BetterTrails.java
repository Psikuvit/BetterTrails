package me.psikuvit.bettertrails;

import me.psikuvit.bettertrails.listeners.InteractListener;
import me.psikuvit.bettertrails.listeners.InvListeners;
import me.psikuvit.bettertrails.listeners.MoveListener;
import me.psikuvit.bettertrails.trails.*;
import me.psikuvit.bettertrails.trails.singlecolored.*;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class BetterTrails extends JavaPlugin {

    private static BetterTrails plugin;
    private final NamespacedKey key = new NamespacedKey(this, "trail");
    private Map<UUID, Trail> playerTrails;
    private List<Trail> trails;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        playerTrails = new HashMap<>();
        trails = List.of(new Rainbow(), new Flame(), new Enchanter(), new HydroHomie(), new INK(), new Love(), new Musician(), new Oceanic(),
                new Skulker(), new Snowy(), new Wizard(), new Blue(), new Green(), new Orange(), new Purple(), new Red(), new Yellow());

        getServer().getPluginManager().registerEvents(new MoveListener(), this);
        getServer().getPluginManager().registerEvents(new InvListeners(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);

        getCommand("flighttrails").setExecutor(new VoucherCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Map<UUID, Trail> getPlayerTrails() {
        return playerTrails;
    }

    public List<Trail> getTrails() {
        return trails;
    }

    public NamespacedKey getKey() {
        return key;
    }


    public LuckPerms getLuckPerms() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider == null) {
            return null;
        }
        return provider.getProvider();
    }

    public static BetterTrails getPlugin() {
        return plugin;
    }
}
