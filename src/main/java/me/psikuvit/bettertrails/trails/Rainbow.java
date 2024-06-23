package me.psikuvit.bettertrails.trails;

import me.psikuvit.bettertrails.utils.Utils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Random;

public class Rainbow implements Trail {

    @Override
    public String getID() {
        return "Rainbow";
    }

    @Override
    public String getName() {
        return Utils.color("&cR&6a&ei&an&9b&1o&5w");
    }

    @Override
    public String getPermission() {
        return "trailflight.trails.rainbow";
    }

    @Override
    public void onEquip(Player player) {
        Location center = player.getLocation();
        Color[] colors = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.ORANGE, Color.PURPLE};
        for (double x = -0.5; x <= 0.5; x+=0.5) {
            for (double z = -0.5; z <= 0.5; z+=0.5) {
                center.add(x, 0, z);
                Color toShow = colors[new Random().nextInt(colors.length)];
                Particle.DustOptions dustOptions = new Particle.DustOptions(toShow, 1);
                center.getWorld().spawnParticle(Particle.REDSTONE, center, 1,0,0,0,0, dustOptions);

            }
        }
    }
}
