package com.cartnicklabs.rpgmobs;

import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.World;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;


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
            final Location mobLocation = entity.getLocation();
            final Location worldSpawn = entity.getWorld().getSpawnLocation();
            final Location diff = entity.getLocation().subtract(worldSpawn);
            int distance = (int) Math.sqrt(getSquare((int) diff.getX()) + getSquare((int) diff.getZ()));

            // find the depth of an enemy
            int mob_y = (int) mobLocation.getY();
            int surface_y = entity.getWorld().getHighestBlockYAt(mobLocation);
            int depth = surface_y - mob_y;

            // calculate mob level
            int level = distance / 500 + depth / 15 + 1;
            ((Enemy) entity).setMaxHealth(((Enemy) entity).getMaxHealth() * (1 + 0.10 * (level - 1)));
            ((Enemy) entity).setHealth(((Enemy) entity).getMaxHealth());

            String mobName = WordUtils.capitalizeFully(entity.getType().name().strip(), '_').replaceAll("_", " ");

            // get mob's health and display name tag
            int health = (int) ((Enemy) entity).getHealth();
            String enemy_tag = ChatColor.GOLD + "Lvl. " + level + " " + ChatColor.GOLD + mobName + " " + ChatColor.RED + " (" + health + " HP)";
            entity.setCustomName(enemy_tag);
            entity.setCustomNameVisible(false);
        }
    }
}
