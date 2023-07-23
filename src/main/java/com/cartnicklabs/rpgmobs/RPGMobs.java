package com.cartnicklabs.rpgmobs;

import org.bukkit.plugin.java.JavaPlugin;

public final class RPGMobs extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new SpawnEvent(), this);
    }
}
