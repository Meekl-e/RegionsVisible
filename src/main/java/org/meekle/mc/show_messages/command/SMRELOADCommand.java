package org.meekle.mc.show_messages.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.meekle.mc.show_messages.EventListener;
import org.meekle.mc.show_messages.Message;
import org.meekle.mc.show_messages.Show_messages;

import java.lang.reflect.Member;

public class SMRELOADCommand extends AbstractCommand {


    public SMRELOADCommand(){ super("rv-reload");}

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission("rv.reload")) {
            Message.NoPermission.send(sender);
            return;
        }
        Show_messages.getRegions().ReloadConfig();
        Show_messages.getSettings().ReloadConfig();
        Show_messages.getInstance().reloadConfig();
        Message.config = Show_messages.getInstance().getConfig();
        Message.prefix = ChatColor.translateAlternateColorCodes('&', Message.config.getString("prefix"));
        EventListener.distance_config = Show_messages.getInstance().getConfig().getInt("region_settings.time_update");
        EventListener.config = Show_messages.getSettings().getConfig();
        EventListener.rgConfig = Show_messages.getRegions().getConfig();
        AddRegionCommand.regions = Show_messages.getRegions();
        RemoveRegionCommand.regions = Show_messages.getRegions();
        Message.Successful_reload.send(sender);
    }
}
