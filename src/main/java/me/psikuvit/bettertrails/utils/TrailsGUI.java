package me.psikuvit.bettertrails.utils;

import me.psikuvit.bettertrails.BetterTrails;
import me.psikuvit.bettertrails.trails.Trail;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;
import java.util.List;

public class TrailsGUI {

    private static Inventory inventory;
    private final ItemStack FILLER_GLASS = Utils.makeItem(Material.GRAY_STAINED_GLASS_PANE, " ");

    private void setupInventory(Player player) {
        inventory = Bukkit.createInventory(null, 54, "Trails Gui");

        inventory.setItem(49, Utils.makeItem(Material.BARRIER, "&cClose"));


        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, FILLER_GLASS);
            }
        }

        inventory.setItem(2, FILLER_GLASS);
        inventory.setItem(3, FILLER_GLASS);
        inventory.setItem(5, FILLER_GLASS);
        inventory.setItem(6, FILLER_GLASS);
        inventory.setItem(17, FILLER_GLASS);
        inventory.setItem(18, FILLER_GLASS);
        inventory.setItem(26, FILLER_GLASS);
        inventory.setItem(27, FILLER_GLASS);
        inventory.setItem(35, FILLER_GLASS);
        inventory.setItem(36, FILLER_GLASS);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, FILLER_GLASS);
            }
        }

        List<Trail> trails = BetterTrails.getPlugin().getTrails();

        for (int i = 0; i < 28; i++) {
            if (i >= trails.size()) break;
            if (trails.get(i) != null) {
                ///////////////////////////
                Trail trail = trails.get(i);

                //Create an item from our collection and place it into the inventory
                ItemStack tagItem = new ItemStack(Material.NAME_TAG);
                ItemMeta tagMeta = tagItem.getItemMeta();
                PersistentDataContainer pdc = tagMeta.getPersistentDataContainer();
                pdc.set(BetterTrails.getPlugin().getKey(), PersistentDataType.STRING, trail.getID());

                tagMeta.setDisplayName(trail.getName());
                if (Utils.hasPermission(player, trail.getPermission())) {
                    tagMeta.setLore(Collections.singletonList(Utils.color("&aOwned")));
                } else {
                    tagMeta.setLore(Collections.singletonList(Utils.color("&cNot Owned")));
                }
                tagItem.setItemMeta(tagMeta);

                inventory.addItem(tagItem);
            }
        }
    }

    public static Inventory getInventory() {
        return inventory;
    }
    public void open(Player player) {
        setupInventory(player);
        player.openInventory(inventory);
    }
}
