package me.psikuvit.bettertrails;

import me.psikuvit.bettertrails.trails.Trail;
import me.psikuvit.bettertrails.utils.TrailsGUI;
import me.psikuvit.bettertrails.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VoucherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                if (args[0].equals("gui")) {
                    new TrailsGUI().open(player);
                }
            }
            if (!Utils.hasPermission(player, "trailsflight.equip")) {
                player.sendMessage(Utils.color("&cYou don't have the permission to run this"));
                return true;
            }
        }

        if (args.length == 3) {
            if (args[0].equals("give")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage(Utils.color("&cCouldn't find player"));
                    return true;
                }
                Trail trail = Utils.getTrailByID(args[2]);
                if (trail == null) {
                    sender.sendMessage(Utils.color("&cCouldn't find trail"));
                    return true;
                }

                ItemStack itemStack = new ItemStack(Material.PAPER);
                ItemMeta itemMeta = itemStack.getItemMeta();

                if (itemMeta == null) {
                    return true;
                }

                itemMeta.setDisplayName(trail.getName() + Utils.color(" &7Voucher"));
                String string;

                if (Utils.hasPermission(target, trail.getPermission())) string = Utils.color("&2&lOwned");
                else string = Utils.color("&c&lNot Owned");

                itemMeta.setLore(List.of(Utils.color("&eRight click to get access"), " ", string));
                itemMeta.getPersistentDataContainer().set(BetterTrails.getPlugin().getKey(), PersistentDataType.STRING, trail.getID());
                itemStack.setItemMeta(itemMeta);

                target.getInventory().addItem(itemStack);
            }
        }
        return false;
    }
}
