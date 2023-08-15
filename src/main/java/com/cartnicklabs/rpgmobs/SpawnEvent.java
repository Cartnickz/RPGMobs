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
    RPGMobs plugin;
    public SpawnEvent(RPGMobs plugin) {
        this.plugin = plugin;
    }

    public static int getSquare(int number) {
        return number * number;
    }

    @EventHandler
    // run code when entity spawns
    public void onEntitySpawn(EntitySpawnEvent e){
        Boolean applyLevels = true;
        Entity entity = e.getEntity();

        // check if entity is an enemy
        if (entity instanceof Enemy){
            Enemy enemy = (Enemy) entity;
            // find the location of enemy
            final Location mobLocation = enemy.getLocation();
            final Location worldSpawn = enemy.getWorld().getSpawnLocation();
            final Location diff = enemy.getLocation().subtract(worldSpawn);
            int distance = (int) Math.sqrt(getSquare((int) diff.getX()) + getSquare((int) diff.getZ()));

            // find the depth of an enemy
            int mob_y = (int) mobLocation.getY();
            int surface_y = enemy.getWorld().getHighestBlockYAt(mobLocation);
            int depth = surface_y - mob_y;
            if (depth < 0 || enemy.getWorld().getEnvironment().toString() == "NETHER"){
                depth = 0;
            }

            if (applyLevels) {
                int radius;
                switch(enemy.getWorld().getEnvironment().toString()){
                    case "NETHER": radius = 65;
                        break;
                    default: radius = 500;
                        break;
                }
                int level = distance / radius + depth / 15 + 1;

                enemy.setMetadata("Level", new FixedMetadataValue(plugin, level));

                enemy.setMaxHealth(enemy.getHealth() * (1 + 0.10 * (level - 1)));
                enemy.setHealth(enemy.getMaxHealth());
            }

            // calculate mob level
            String mobName = WordUtils.capitalizeFully(entity.getType().name().strip(), '_').replaceAll("_", " ");
            enemy.setMetadata("Mob Type", new FixedMetadataValue(plugin, mobName));

            // get mob's health and display name tag
            int health = (int) enemy.getHealth();
            String test = (int) enemy.getHealth() + " " + (int) enemy.getMaxHealth() + " " + enemy.getMetadata("Level").get(0).asInt();

            String enemy_tag = ChatColor.GOLD + "Lvl. " + enemy.getMetadata("Level").get(0).asInt() + " " + ChatColor.GOLD + mobName + " " + ChatColor.RED + "(" + health + " HP)";
            enemy.setCustomName(enemy_tag);
            enemy.setCustomNameVisible(true);
        }
    }
}
