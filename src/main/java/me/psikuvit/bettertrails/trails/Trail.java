package me.psikuvit.bettertrails.trails;

import org.bukkit.entity.Player;

public interface Trail {

    String getID();
    String getName();
    String getPermission();
    void onEquip(Player player);
}
