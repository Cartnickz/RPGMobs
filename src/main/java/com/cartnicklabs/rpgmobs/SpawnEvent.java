package com.cartnicklabs.rpgmobs;

import org.bukkit.entity.Enemy;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class SpawnEvent implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e){
        Entity entity = e.getEntity();

        if (entity instanceof Enemy){
            entity.setGlowing(true);
            entity.setCustomName("BAD DUDE");
            entity.setCustomNameVisible(true);
        }
    }
}
