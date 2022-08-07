package org.meekle.mc.show_messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public enum Message {
    Successful_reload, NoPermission,
    OnlyPlayer, Successful_add, Successful_remove, LowArgs, UnknownRegion;
    public static FileConfiguration config = Show_messages.getInstance().getConfig();
    public static String prefix =ChatColor.translateAlternateColorCodes('&', config.getString("prefix"));

    public void send(CommandSender sender){
        String str = config.getString("messages."+Message.this.name());
        sender.sendMessage(prefix + replace_color(str));
    }
    public void send(CommandSender sender, String region, String name){
        String str = config.getString("messages."+Message.this.name());
        str = str.replace("{region}", region);
        str = str.replace("{name}", name);
        sender.sendMessage(prefix + replace_color(str));
    }
    public void send(CommandSender sender, String region){
        String str = config.getString("messages."+Message.this.name());
        str = str.replace("{region}", region);
        sender.sendMessage(prefix + replace_color(str));
    }
    public void sendError(CommandSender sender, boolean tag){
        if (tag){
            if (Message.this.name().equalsIgnoreCase("LowArgs")){
                sender.sendMessage(ChatColor.RED + "Low arguments");
                sender.sendMessage(ChatColor.RED +"/sm-add <region_name> <name_in_actionBar>");
            } else if (Message.this.name().equalsIgnoreCase("UnknownRegion")) {
                sender.sendMessage(ChatColor.RED +"Undefined Region");
                sender.sendMessage(ChatColor.RED +"/sm-add <region_name> <name_in_actionBar>");
            }
        }else{
        if (Message.this.name().equalsIgnoreCase("LowArgs")){
            sender.sendMessage(ChatColor.RED + "Low arguments");
            sender.sendMessage(ChatColor.RED +"/sm-remove <region_name>");
        } else if (Message.this.name().equalsIgnoreCase("UnknownRegion")) {
            sender.sendMessage(ChatColor.RED +"Undefined Region");
            sender.sendMessage(ChatColor.RED +"/sm-remove <region_name>");
        }
        }
    }

    private String replace_color(String str) {
        str = ChatColor.translateAlternateColorCodes('&', str);
        return str;
    }



}