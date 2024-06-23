package me.psikuvit.bettertrails.trails;

import me.psikuvit.bettertrails.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class Wizard implements Trail {
    @Override
    public String getID() {
        return "Wizard";
    }

    @Override
    public String getName() {
        return Utils.color("&5Wizard");
    }

    @Override
    public String getPermission() {
        return "trailflight.trails.wizard";
    }

    @Override
    public void onEquip(Player player) {
        Location center = player.getLocation();
        for (double x = -0.5; x <= 0.5; x+=0.5) {
            for (double z = -0.5; z <= 0.5; z+=0.5) {
                center.add(x, 0, z);
                center.getWorld().spawnParticle(Particle.SPELL_WITCH, center, 1, 0,0,0,0);
            }
        }
    }
}
