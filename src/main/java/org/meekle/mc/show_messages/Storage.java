package org.meekle.mc.show_messages;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

public class Storage {
    private FileConfiguration config;
    private File file;

    public Storage(String fileName){
        file = new File(Show_messages.getInstance().getDataFolder(), fileName);
        config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()){
            try {
                file.createNewFile();
                if (fileName.equalsIgnoreCase("regions.yml")){
                    for (World world : Show_messages.getInstance().getServer().getWorlds()){
                            if (world.getName().equalsIgnoreCase("world_nether")){
                                config.set("__global__|" + world.getName()+".name", "&3&lNether territory" );
                                Show_messages.getInstance().getLogger().info("Initialized world_nether");
                            } else if (world.getName().equalsIgnoreCase("world_the_end")) {
                                config.set("__global__|" + world.getName()+".name", "&1&lEnd territory" );
                                Show_messages.getInstance().getLogger().info("Initialized world_the_end");
                            } else {
                                config.set("__global__|" + world.getName()+".name", "&3&lFree territory" );
                                Show_messages.getInstance().getLogger().info("Initialized " + world.getName());
                            }
                            save();}

                }


            } catch (IOException e) {
                throw new RuntimeException("Can't create file "+ fileName + " "+ e);
            }
        }
        if (fileName.equalsIgnoreCase("WorldSettings.yml")){
            for (World world : Show_messages.getInstance().getServer().getWorlds()){
                String name = world.getName();
                if (config.get(name)==null){
                    Show_messages.getInstance().getLogger().info("Create default config for " + name);
                config.set(name+".enabled", true);
                config.set(name+".CheckHomes", true);
                if (name.equalsIgnoreCase("world_nether")){
                    config.set(name+".OwnerTerritory", "&a&lOwner this nether territory");
                    config.set(name+".MemberTerritory", "&a&lMember this nether territory");
                    config.set(name+".AnotherTerritory", "&c&lOccupied nether territory");
                } else if (name.equalsIgnoreCase("world_the_end")) {
                    config.set(name+".OwnerTerritory", "&a&lOwner this end territory");
                    config.set(name+".MemberTerritory", "&a&lMember this end territory");
                    config.set(name+".AnotherTerritory", "&c&lOccupied end territory");
                } else {
                    config.set(name+".OwnerTerritory", "&a&lOwner this territory");
                    config.set(name+".MemberTerritory", "&a&lMember territory");
                    config.set(name+".AnotherTerritory", "&c&lOccupied territory");
                }}
                save();}

    }}
    public void save(){
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException("Can't save configuration! "+e);
        }
    }
    public FileConfiguration getConfig(){
        return config;
    }
    public void ReloadConfig(){
        config = YamlConfiguration.loadConfiguration(file);
    }

}
