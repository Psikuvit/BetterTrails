package me.psikuvit.bettertrails.trails;

import me.psikuvit.bettertrails.utils.Utils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class INK implements Trail {
    @Override
    public String getID() {
        return "Ink";
    }

    @Override
    public String getName() {
        return Utils.color("&0Ink");
    }

    @Override
    public String getPermission() {
        return "trailflight.trails.ink";
    }

    @Override
    public void onEquip(Player player) {
        Location center = player.getLocation();
        for (double x = -0.5; x <= 0.5; x+=0.5) {
            for (double z = -0.5; z <= 0.5; z+=0.5) {
                center.add(x, 0, z);
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLACK, 1);
                center.getWorld().spawnParticle(Particle.REDSTONE, center, 1,0,0,0,0, dustOptions);

            }
        }
    }
}
