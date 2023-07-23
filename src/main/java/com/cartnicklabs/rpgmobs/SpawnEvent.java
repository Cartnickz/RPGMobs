package com.cartnicklabs.rpgmobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.World;

public class SpawnEvent implements Listener {

    public static int getSquare(int number) {
        return number * number;
    }

    @EventHandler
    // run code when entity spawns
    public void onEntitySpawn(EntitySpawnEvent e){
        Entity entity = e.getEntity();

        // check if entity is an enemy
        if (entity instanceof Enemy){

            // find the location of enemy
            Location mobLocation = entity.getLocation();
            Location worldSpawn = entity.getWorld().getSpawnLocation();
            Location diff = mobLocation.subtract(worldSpawn);
            int distance = (int) Math.sqrt(getSquare((int) diff.getX()) + getSquare((int) diff.getZ()));

            // calculate mob level and set max health
            int level = distance / 500 + 1;
            ((Enemy) entity).setMaxHealth(((Enemy) entity).getMaxHealth() * (1 + 0.10 * (level - 1)));
            ((Enemy) entity).setHealth(((Enemy) entity).getMaxHealth());

            int health = (int) ((Enemy) entity).getHealth();

            String enemy_tag = ChatColor.BLUE + "Lvl: " + level + ChatColor.RED + " (" + health + " HP)";

            entity.setGlowing(true);
            entity.setCustomName(enemy_tag);
            entity.setCustomNameVisible(true);
        }
    }
}
