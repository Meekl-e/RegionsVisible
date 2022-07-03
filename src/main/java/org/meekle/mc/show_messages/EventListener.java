
package org.meekle.mc.show_messages;


import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;


public class EventListener implements Listener {
    public static int distance_config = Show_messages.getInstance().getConfig().getInt("region_settings.time_update");
    public static FileConfiguration config = Show_messages.getInstance().getConfig();
    public static FileConfiguration rgConfig = Show_messages.getRegions().getConfig();
    BukkitScheduler scheduler = Bukkit.getScheduler();
    boolean live = false;
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        live = true;
        repeatSend(e.getPlayer());

    }
    @EventHandler
    public void DeathPlayer(PlayerDeathEvent e){
        live = false;
    }
    @EventHandler
    public void RespawnEvent(PlayerRespawnEvent e){
        live = true;
        repeatSend(e.getPlayer());
    }
    @EventHandler
    public void PlayerKick(PlayerKickEvent e ){
        live = false;
    }
    @EventHandler
    public void PlayerLeave(PlayerQuitEvent e){
        live =false;
    }
    @EventHandler
    public void playerChangeWorld(PlayerChangedWorldEvent e){
        if (config.getBoolean("region_settings."+e.getPlayer().getWorld().getName()+".enabled")){
            if (!live){
                live = true;
                repeatSend(e.getPlayer());
            }
        }else {
            live = false;
        }
    }
    


   

    public Map<String, ProtectedRegion> getRegions(Player p) {
        return WorldGuardPlugin.inst().getRegionManager(p.getWorld()).getRegions();
    }

    public void sendActionBar(Player p, String str) {
         ActionBarAPI.sendActionBar(p, str );
             }

    public void repeatSend(Player p){

            scheduler.scheduleAsyncRepeatingTask(Show_messages.getInstance(), () -> {
                    if(live){
                    CheckRegion(p);}else {
                        scheduler.cancelTasks(Show_messages.getInstance());
                    }
              }, 0, distance_config);

    }

    public void CheckRegion(Player p) {
        Location loc_p = p.getLocation();
        for(ProtectedRegion i : getRegions(p).values()) {
            if(i.contains(loc_p.getBlockX(), loc_p.getBlockY(), loc_p.getBlockZ())){
            for (String str : rgConfig.getKeys(false)){
                if (i.getId().equalsIgnoreCase(str)){
                    if (rgConfig.getString(str+".world").equalsIgnoreCase(p.getWorld().getName())){
                        String act = ChatColor.translateAlternateColorCodes('&',rgConfig.getString(str+".name"));
                        sendActionBar(p, act);
                        return;
                    }
                }
                if (config.getBoolean("region_settings."+p.getWorld().getName()+".CheckHomes")){
                    if (i.getOwners().contains(p.getUniqueId())){
                        p.sendMessage("owner");
                        String act = config.getString("region_settings."+p.getWorld().getName()+".OwnerTerritory");
                        if (act != null){
                            sendActionBar(p, ChatColor.translateAlternateColorCodes('&', act));
                            return;

                        }
                    }else if (i.getMembers().contains(p.getUniqueId())) {
                        String act = config.getString("region_settings." + p.getWorld().getName() + ".MemberTerritory");
                        if (act != null) {
                            sendActionBar(p, ChatColor.translateAlternateColorCodes('&', act));
                            return;
                        }
                    } else {
                        String act = config.getString("region_settings." + p.getWorld().getName() + ".AnotherTerritory");
                        if (act != null) {
                            sendActionBar(p, ChatColor.translateAlternateColorCodes('&', act));
                            return;
                        }
                    }
                }
            }
            }else if (i.getId().equalsIgnoreCase("__global__")){
                String act = rgConfig.getString("__global__|"+p.getWorld().getName()+".name");
                if (act != null){
                act = ChatColor.translateAlternateColorCodes('&', act);
                sendActionBar(p, act);}
            }
        }

        //sendActionBar(p, new Message().getString("Free_region"));





    }



}
















