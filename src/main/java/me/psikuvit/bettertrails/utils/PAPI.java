package me.psikuvit.bettertrails.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PAPI extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "trailflight";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Psikuvit";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        Player p = (Player) player;
        if (p == null) return null;
        return switch (params) {
            case "trail_rainbow" -> Utils.checkPerm(p, "trailflight.trails.rainbow");
            case "trail_flame" -> Utils.checkPerm(p, "trailflight.trails.flame");
            default -> null;
        };
    }

}
