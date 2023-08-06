package com.cartnicklabs.rpgmobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Enemy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityTakesDamageEvent implements Listener {

    @EventHandler
    public void onTakeDamage(EntityDamageEvent e) {
        // look for cases where player hits mob - update health bar
        if (e.getEntity() instanceof Enemy) {
            Enemy enemy = (Enemy) e.getEntity();


            // find how much damage player did to enemy
            int mobHealth = (int) (enemy.getHealth() - e.getDamage());
            if (mobHealth < 0) {
                mobHealth = 0;
            }
            int maxMobHealth = (int) enemy.getMaxHealth();

            // update health bar
            String enemy_tag_start = enemy.getName().substring(0, enemy.getName().indexOf("("));
            String enemy_tag = enemy_tag_start + "(" + ChatColor.GOLD + mobHealth + ChatColor.RED + "/ " + maxMobHealth + " HP)";
            enemy.setCustomName(enemy_tag);

            enemy.setCustomNameVisible(true);
        }
    }
}