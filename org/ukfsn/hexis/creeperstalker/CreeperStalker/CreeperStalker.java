package org.ukfsn.hexis.creeperstalker.CreeperStalker;

import java.io.File;
import java.util.HashMap;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CreeperStalker extends JavaPlugin {
    private final CreeperStalkerPlayerListener playerListener = new CreeperStalkerPlayerListener(this);
    private final CreeperStalkerEntityListener entityListener = new CreeperStalkerEntityListener(this);
    public String target = null;
    public HashMap creeperList = new HashMap();
    public HashMap creeperLastTarget = new HashMap();
    int creeperStatus = 1;

    public CreeperStalker(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader) {
        super(pluginLoader, instance, desc, folder, plugin, cLoader);
    }

    public void onDisable() {
        System.out.println("Stalker Creeper is off");
    }

    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvent(Event.Type.PLAYER_COMMAND, playerListener, Priority.Low, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Low, this);

        pm.registerEvent(Event.Type.ENTITY_TARGET, entityListener, Priority.Low, this);
        pm.registerEvent(Event.Type.EXPLOSION_PRIMED, entityListener, Priority.Low, this);
        pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Priority.Low, this);
        pm.registerEvent(Event.Type.ENTITY_DAMAGEDBY_ENTITY, entityListener, Priority.Low, this);
        pm.registerEvent(Event.Type.ENTITY_DAMAGEDBY_PROJECTILE, entityListener, Priority.Low, this);
        pm.registerEvent(Event.Type.ENTITY_DAMAGED, entityListener, Priority.Low, this);
        pm.registerEvent(Event.Type.ENTITY_DAMAGEDBY_BLOCK, entityListener, Priority.Low, this);

        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " is ready to roll");
    }
}