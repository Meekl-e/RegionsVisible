package org.meekle.mc.show_messages.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.meekle.mc.show_messages.EventListener;
import org.meekle.mc.show_messages.Message;
import org.meekle.mc.show_messages.Show_messages;

import java.lang.reflect.Member;

public class SMRELOADCommand extends AbstractCommand {


    public SMRELOADCommand(){ super("sm-reload");}

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission("show_messages.reload")) {
            sender.sendMessage(new Message().getMessage("NoPermission", sender));
            return;
        }

        Show_messages.getInstance().reloadConfig();
        Message.config = Show_messages.getInstance().getConfig();
        EventListener.distance_config = Show_messages.getInstance().getConfig().getInt("region_settings.time_update");
        if (sender instanceof Player) {
            sender.sendMessage(new Message().getMessage("Successful_reload_player", sender));
            return;
        }
        sender.sendMessage(Show_messages.getInstance().getConfig().getString("messages.Successful_reload_console"));
    }
}
