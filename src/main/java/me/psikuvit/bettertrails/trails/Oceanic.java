package me.psikuvit.bettertrails.trails;

import me.psikuvit.bettertrails.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Random;

public class Oceanic implements Trail {
    @Override
    public String getID() {
        return "Oceanic";
    }

    @Override
    public String getName() {
        return Utils.color("&1Oceanic");
    }

    @Override
    public String getPermission() {
        return "trailflight.trails.oceanic";
    }

    @Override
    public void onEquip(Player player) {
        Location center = player.getLocation();
        Particle[] particles = {Particle.WATER_BUBBLE, Particle.GLOW_SQUID_INK};
        for (double x = -0.5; x <= 0.5; x+=0.5) {
            for (double z = -0.5; z <= 0.5; z+=0.5) {
                center.add(x, 0, z);

                Particle toShow = particles[new Random().nextInt(particles.length)];

                center.getWorld().spawnParticle(toShow, center, 1, 0,0,0,0);
            }
        }
    }
}
