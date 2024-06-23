package me.psikuvit.bettertrails.trails;

import me.psikuvit.bettertrails.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Random;

public class Flame implements Trail {
    @Override
    public String getID() {
        return "Flame";
    }

    @Override
    public String getName() {
        return Utils.color("&6Flame");
    }

    @Override
    public String getPermission() {
        return "trailflight.trails.flame";
    }

    @Override
    public void onEquip(Player player) {
        Location center = player.getLocation();
        Particle[] effects = {Particle.FLAME, Particle.SMOKE_NORMAL};
        for (double x = -0.5; x <= 0.5; x+=0.5) {
            for (double z = -0.5; z <= 0.5; z+=0.5) {
                center.add(x, 0, z);

                Particle toShow = effects[new Random().nextInt(effects.length)];


                center.getWorld().spawnParticle(toShow, center, 1, 0,0,0,0);
            }
        }
    }
}
