package org.meekle.mc.show_messages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

public class Storage {
    private FileConfiguration config;
    private File file;

    public Storage(String name){
        file = new File(Show_messages.getInstance().getDataFolder(), name);
        if (!file.exists()){
            Show_messages.getInstance().saveResource(name, false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
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
