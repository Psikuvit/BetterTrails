package me.psikuvit.bettertrails.trails;

import me.psikuvit.bettertrails.utils.Utils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Random;

public class Love implements Trail {
    @Override
    public String getID() {
        return "Love";
    }

    @Override
    public String getName() {
        return Utils.color("&cLove");
    }

    @Override
    public String getPermission() {
        return "trailflight.trails.love";
    }

    @Override
    public void onEquip(Player player) {
        Location center = player.getLocation();
        Particle.DustOptions redDust = new Particle.DustOptions(Color.fromRGB(255, 192, 203), 1);

        int chance = new Random().nextInt(2);
        for (double x = -0.5; x <= 0.5; x+=0.5) {
            for (double z = -0.5; z <= 0.5; z+=0.5) {
                center.add(x, 0, z);

                if (chance == 0) center.getWorld().spawnParticle(Particle.REDSTONE, center, 1, redDust);
                else center.getWorld().spawnParticle(Particle.HEART, center, 1, 0,0,0,0);
            }
        }
    }
}
