package me.psikuvit.bettertrails.listeners;

import me.psikuvit.bettertrails.BetterTrails;
import me.psikuvit.bettertrails.trails.Trail;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (BetterTrails.getPlugin().getPlayerTrails().containsKey(player.getUniqueId())) {
            if (player.isFlying()) {
                Trail trail = BetterTrails.getPlugin().getPlayerTrails().get(player.getUniqueId());
                trail.onEquip(player);
            }
        }
    }
}
