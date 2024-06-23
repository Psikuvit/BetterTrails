package me.psikuvit.bettertrails.trails;

import me.psikuvit.bettertrails.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class Enchanter implements Trail {
    @Override
    public String getID() {
        return "Enchanter";
    }

    @Override
    public String getName() {
        return Utils.color("&fEnchanter");
    }

    @Override
    public String getPermission() {
        return "trailflight.trails.enchanter";
    }

    @Override
    public void onEquip(Player player) {
        Location center = player.getLocation();
        for (double x = -0.5; x <= 0.5; x+=0.5) {
            for (double z = -0.5; z <= 0.5; z+=0.5) {
                center.add(x, 0, z);
                center.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, center, 1, 0,0,0,0);

            }
        }
    }
}
