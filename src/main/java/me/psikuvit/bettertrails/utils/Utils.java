package me.psikuvit.bettertrails.utils;

import me.psikuvit.bettertrails.BetterTrails;
import me.psikuvit.bettertrails.trails.Trail;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {

    public static Trail getTrailByID(String s) {
        for (Trail trail : BetterTrails.getPlugin().getTrails()) {
            if (trail.getID().equalsIgnoreCase(s)) return trail;
        }
        return null;
    }
    public static String color(String message) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : ch) {
                builder.append("&").append(c);
            }

            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static List<String> color(List<String> msg) {
        return msg.stream().map(Utils::color).collect(Collectors.toList());
    }

    public static ItemStack makeItem(Material material, String displayName, String... lore) {

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(color(displayName));

        itemMeta.setLore(color(Arrays.asList(lore)));
        item.setItemMeta(itemMeta);

        return item;
    }

    public static String checkPerm(Player player, String perm) {
        if (Utils.hasPermission(player, perm)) {
            return Utils.color("&aOwned");
        } else {
            return Utils.color("&cNot Owned");
        }
    }

    public static void addPermission(User user, String permission) {
        user.data().add(PermissionNode.builder(permission).build());

        BetterTrails.getPlugin().getLuckPerms().getUserManager().saveUser(user);
    }
    public static boolean hasPermission(Player player, String permission) {
        User user = BetterTrails.getPlugin().getLuckPerms().getUserManager().getUser(player.getUniqueId());

        return user.getNodes().stream().filter(NodeType.PERMISSION::matches).anyMatch(node -> node.getKey().equals(permission));
    }
}
