package de.freezy.mesystem.instance;

import de.freezy.teleport.*;
import de.freezy.teleport.warp.Warp;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public class Instance extends JavaPlugin {


    public static Instance instance;

    public static Instance getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        // Register Commands
        getCommand("home").setExecutor(new HomeCMD());
        getCommand("back").setExecutor(new BackCMD());

        getCommand("locmob").setExecutor(new LocMobCMD());
        getCommand("locmob").setTabCompleter((TabCompleter) new LocMobCMDInterface());
        getCommand("test").setExecutor(new TestCMD());

        // Register Listener
        getServer().getPluginManager().registerEvents(new OnDeath(), this);
        getServer().getPluginManager().registerEvents(new Warp(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

