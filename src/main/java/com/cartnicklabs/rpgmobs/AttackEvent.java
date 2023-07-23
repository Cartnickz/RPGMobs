package com.cartnicklabs.rpgmobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AttackEvent implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Enemy) {
            Player player = (Player) e.getDamager();
            Enemy enemy = (Enemy) e.getEntity();

            int mobHealth = (int) (enemy.getHealth() - e.getDamage());
            if (mobHealth < 0) {
                mobHealth = 0;
            }
            int maxMobHealth = (int) enemy.getMaxHealth();

            String enemy_tag_start = enemy.getName().substring(0, enemy.getName().indexOf("("));
            String enemy_tag = enemy_tag_start + "(" + ChatColor.GOLD + mobHealth + ChatColor.RED + "/ " + maxMobHealth + " HP)";
            enemy.setCustomName(enemy_tag);
        }



    }

}
