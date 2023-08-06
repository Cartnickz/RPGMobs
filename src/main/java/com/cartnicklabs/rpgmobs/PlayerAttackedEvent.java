package com.cartnicklabs.rpgmobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Enemy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerAttackedEvent implements Listener {
    @EventHandler
    public void onPlayerAttackedEvent(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Enemy) {
            // get the level of the enemy
            Enemy enemy = (Enemy) e.getDamager();
            String enemyName = enemy.getName();

            Integer level = Integer.parseInt(enemyName.substring(enemyName.indexOf(".") + 1,
                    enemyName.indexOf(" ", enemyName.indexOf(".") + 2)).trim());


            e.setDamage(e.getDamage() + (0.5 * (level / 10.0)));
            // e.getEntity().sendMessage((e.getDamage() + (0.5 * (level / 10.0))) + " " + level + " " + (0.5 * (level / 10)));
        }
    }
}
