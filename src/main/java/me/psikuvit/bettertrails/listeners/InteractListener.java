package me.psikuvit.bettertrails.listeners;

import me.psikuvit.bettertrails.BetterTrails;
import me.psikuvit.bettertrails.trails.Trail;
import me.psikuvit.bettertrails.utils.Utils;
import net.luckperms.api.model.user.User;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class InteractListener implements Listener {

    private final BetterTrails plugin = BetterTrails.getPlugin();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = e.getItem();

        if (itemStack == null || itemStack.getType() != Material.PAPER || itemStack.getItemMeta() == null) return;

        PersistentDataContainer PDC = itemStack.getItemMeta().getPersistentDataContainer();

        if (PDC.has(plugin.getKey(), PersistentDataType.STRING)) {

            String id = PDC.get(plugin.getKey(), PersistentDataType.STRING);
            Trail trail = Utils.getTrailByID(id);
            if (trail == null) return;

            User user = plugin.getLuckPerms().getUserManager().getUser(player.getUniqueId());

            if (user == null) {
                return;
            }

            Utils.addPermission(user, trail.getPermission());
            player.getInventory().remove(itemStack);
            player.sendMessage(Utils.color("&bYou can access this trail now!"));
        }
    }
}
