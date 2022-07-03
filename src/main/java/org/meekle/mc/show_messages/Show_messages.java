package org.meekle.mc.show_messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;
import org.meekle.mc.show_messages.command.SMRELOADCommand;

import java.io.Console;


public final class Show_messages extends JavaPlugin {
    private static Show_messages instance;
    private static Storage regions;

    @Override
    public void onEnable() {

        instance = this;
        saveDefaultConfig();
        regions = new Storage("regions.yml");
        new SMRELOADCommand();
        new Message();
        Plugin pl = Bukkit.getPluginManager().getPlugin("WorldGuard");
        pl = Bukkit.getPluginManager().getPlugin("ActionBarAPI");

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);


        
    }

    @Override
    public void onDisable() {

    }
    public static Show_messages getInstance() {
        return instance;
    }
    public static Storage getRegions() {
        return regions;
    }



}
