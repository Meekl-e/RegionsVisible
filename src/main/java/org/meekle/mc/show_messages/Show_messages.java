package org.meekle.mc.show_messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;
import org.meekle.mc.show_messages.command.AddRegionCommand;
import org.meekle.mc.show_messages.command.RemoveRegionCommand;
import org.meekle.mc.show_messages.command.SMRELOADCommand;

import java.io.Console;


public final class Show_messages extends JavaPlugin {
    private static Show_messages instance;
    private static Storage regions;
    private static Storage settings;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        regions = new Storage("regions.yml");
        settings = new Storage("WorldSettings.yml");
        new SMRELOADCommand();
        new AddRegionCommand();
        new RemoveRegionCommand();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);

        getLogger().info("Regions Visible v2.0 enabled ");

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
    public static Storage getSettings() {
        return settings;
    }



}
