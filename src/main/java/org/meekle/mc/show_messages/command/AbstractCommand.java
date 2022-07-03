package org.meekle.mc.show_messages.command;

import org.bukkit.command.*;
import org.meekle.mc.show_messages.Show_messages;


import java.util.ArrayList;
import java.util.List;

public  abstract class AbstractCommand implements CommandExecutor {

    public AbstractCommand(String command) {
        PluginCommand pluginCommand =  Show_messages.getInstance().getCommand(command);
        if(pluginCommand != null){
            pluginCommand.setExecutor(this);

        }

    }
    public abstract void execute(CommandSender sender, String label, String[] args );
    public List<String> complete(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        execute(sender, label , args);
        return true;
    }
}
