package com.cartnicklabs.rpgmobs;


import org.bukkit.entity.Enemy;
import org.bukkit.plugin.java.JavaPlugin;


public final class RPGMobs extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new SpawnEvent(this), this);
        getServer().getPluginManager().registerEvents(new EntityTakesDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerAttackedEvent(), this);
    }
}
