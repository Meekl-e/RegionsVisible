package org.meekle.mc.show_messages;


import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Message {
    public static FileConfiguration config = Show_messages.getInstance().getConfig();

    public String getString(String str){
        str = config.getString("messages." + str);
        str = replace_color(str);
        return str;
    }

    public String getMessage(String str, CommandSender sender) {
        str = config.getString("messages." + str);
        str = replace_color(str);
        str = replace_placeholders(str, sender.getName());
        return str;
    }
    public String getSetting(String str) {
        return config.getString("region_settings." + str);
    }


    private String replace_color(String str) {
        str = ChatColor.translateAlternateColorCodes('&', str);
        return str;

    }


    private String replace_placeholders(String str, String player) {
        str = str.replace("{PLAYER}", player);
        return str;

    }

}