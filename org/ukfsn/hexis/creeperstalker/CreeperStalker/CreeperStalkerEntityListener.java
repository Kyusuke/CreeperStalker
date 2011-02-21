package org.ukfsn.hexis.creeperstalker.CreeperStalker;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimedEvent;

public class CreeperStalkerEntityListener extends EntityListener{
    private final CreeperStalker plugin;

    public CreeperStalkerEntityListener(CreeperStalker instance) {
        plugin = instance;
    }

    public void onEntityTarget(EntityTargetEvent evt){
        Entity e = evt.getEntity();
        Player p = (Player)evt.getTarget();
        if((e.getClass().getSimpleName().equalsIgnoreCase("CraftCreeper")) && (p.getDisplayName().equalsIgnoreCase(plugin.target)) && (plugin.target != null)){
            plugin.creeperList.put(e.getEntityId(), plugin.target.toLowerCase());
            plugin.creeperLastTarget.put(e.getEntityId(), p.getDisplayName().toLowerCase());
            if(plugin.creeperStatus == 1){
                p.sendMessage("<Creeper> Hi " + plugin.target + "!");
                p.sendMessage("<Creeper> My name is Craig, let's have fun together!");
                plugin.creeperStatus = 0;
            }
            else if(plugin.creeperStatus == 2){
                p.sendMessage("<Creeper> Hi " + plugin.target + "!");
                p.sendMessage("<Creeper> I'm also Craig, let's have some more fun!");
                plugin.creeperStatus = 0;
            }
        }
    }

    public void onExplosionPrimed(ExplosionPrimedEvent evt){
        Entity e = evt.getEntity();
        if(plugin.target != null){
            Player p = plugin.getServer().getPlayer(plugin.target.toLowerCase());
            if(plugin.creeperList.containsKey(e.getEntityId()) && plugin.creeperLastTarget.get(e.getEntityId()).equals(plugin.target.toLowerCase())){
                evt.setCancelled(true);
                p.sendMessage("<Craig> *huff puff*");
            }
        }
    }

    public void onEntityDeath(EntityDeathEvent evt){
        Entity e = evt.getEntity();
        if((e.getClass().getSimpleName().equalsIgnoreCase("CraftCreeper")) && plugin.creeperLastTarget.get(e.getEntityId()).equals(plugin.target.toLowerCase()) && (plugin.target != null)){
            plugin.getServer().broadcastMessage("<Craig> I WILL NEVER FORGET YOU " + plugin.target.toUpperCase());
            plugin.creeperList.remove(e.getEntityId());
            plugin.creeperLastTarget.remove(e.getEntityId());
            plugin.creeperStatus = 2;
        }
    }

    public void onEntityDamageByEntity(EntityDamageByEntityEvent evt){
        Entity e = evt.getEntity();
        Player p = (Player)evt.getDamager();
        if((e.getClass().getSimpleName().equalsIgnoreCase("CraftCreeper")) && (p.getDisplayName().equalsIgnoreCase(plugin.target)) && (plugin.target != null)){
            plugin.creeperLastTarget.put(e.getEntityId(), p.getDisplayName().toLowerCase());
            p.sendMessage("<Craig> Ow! Don't hurt me :'(");
        }
    }

    public void onEntityDamageByProjectile(EntityDamageByProjectileEvent evt){
        Entity e = evt.getEntity();
        Player p = (Player)evt.getDamager();
        if((e.getClass().getSimpleName().equalsIgnoreCase("CraftCreeper")) && (p.getDisplayName().equalsIgnoreCase(plugin.target)) && (plugin.target != null)){
            plugin.creeperLastTarget.put(e.getEntityId(), p.getDisplayName().toLowerCase());
            p.sendMessage("<Craig> I know cupid arrows are lovely but THAT HURTS!");
        }
    }

    public void onEntityDamage(EntityDamageEvent evt){
        Entity e = evt.getEntity();
        if(e.getClass().getSimpleName().equalsIgnoreCase("CraftCreeper") && (plugin.target != null)){
            plugin.creeperLastTarget.put(e.getEntityId(), evt.getCause());
        }
    }

    public void onEntityDamageByBlock(EntityDamageByBlockEvent evt){
        Entity e = evt.getEntity();
        if(e.getClass().getSimpleName().equalsIgnoreCase("CraftCreeper") && (plugin.target != null)){
            plugin.creeperLastTarget.put(e.getEntityId(), evt.getDamager());
        }
    }
}
