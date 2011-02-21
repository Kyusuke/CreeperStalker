package org.ukfsn.hexis.creeperstalker.CreeperStalker;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;

public class CreeperStalkerPlayerListener extends PlayerListener{
    private final CreeperStalker plugin;

    public CreeperStalkerPlayerListener(CreeperStalker instance){
        plugin = instance;
    }

    public void onPlayerCommand(PlayerChatEvent evt){
        Player p = evt.getPlayer();
        String msg[] = evt.getMessage().split(" ");

        if(msg.length >= 2){
            if(msg.length == 3 && msg[0].equalsIgnoreCase("/creep") && msg[1].equalsIgnoreCase(("target"))){
                plugin.target = msg[2];
                p.sendMessage("Player " + plugin.target + " is now targetted");
            }
            else if(msg[0].equalsIgnoreCase("/creep") && msg[1].equalsIgnoreCase("target"))
            {
                p.sendMessage("To target a person, type /creep target [person]");
            }

            else if(msg[0].equalsIgnoreCase("/creep") && msg[1].equalsIgnoreCase("current")){
                if(plugin.target == null){
                    p.sendMessage("No-one is currently targetted");
                }
                else{
                    p.sendMessage(plugin.target + " is the current target");
                }
            }
            else if(msg[0].equalsIgnoreCase("/creep")){
                p.sendMessage("Creeper Stalker Usage");
                p.sendMessage("/creep target [person] : Designates a target");
                p.sendMessage("/creep current : Gets the current target");
            }
        }
        else if(msg.length == 1){
            if(msg[0].equalsIgnoreCase("/creep")){
                p.sendMessage("Creeper Stalker Usage");
                p.sendMessage("/creep target [person] : Designates a target");
                p.sendMessage("/creep current : Gets the current target");
            }
        }
    }

    public void onPlayerQuit(PlayerEvent evt){
        Player p = evt.getPlayer();
        if(p.getDisplayName().equalsIgnoreCase(plugin.target)){
            plugin.creeperList.clear();
            plugin.creeperLastTarget.clear();
            plugin.creeperStatus = 1;
        }
    }
}