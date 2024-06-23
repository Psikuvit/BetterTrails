package me.psikuvit.bettertrails.listeners;

import me.psikuvit.bettertrails.BetterTrails;
import me.psikuvit.bettertrails.trails.Trail;
import me.psikuvit.bettertrails.utils.TrailsGUI;
import me.psikuvit.bettertrails.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class InvListeners implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getInventory() == TrailsGUI.getInventory()) {
            ItemStack clicked = e.getCurrentItem();
            if (clicked == null || clicked.getItemMeta() == null) {
                return;
            }
            e.setCancelled(true);

            if (clicked.getType().equals(Material.BARRIER)) player.closeInventory();
            if (clicked.getType().equals(Material.NAME_TAG)) {

                PersistentDataContainer PDC = clicked.getItemMeta().getPersistentDataContainer();
                String id = PDC.get(BetterTrails.getPlugin().getKey(), PersistentDataType.STRING);
                Trail trail = Utils.getTrailByID(id);

                if (trail == null) return;
                if (Utils.hasPermission(player, trail.getPermission())) {

                    BetterTrails.getPlugin().getPlayerTrails().put(player.getUniqueId(), trail);

                    player.sendMessage(trail.getName() + Utils.color(" &7was selected!"));
                    player.closeInventory();
                } else {
                    player.sendMessage(Utils.color("&cYou don't have access for this trail!"));
                    player.closeInventory();
                }
            }

        }
    }
}
