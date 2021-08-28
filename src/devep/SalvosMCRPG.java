package devep;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SalvosMCRPG extends JavaPlugin {

    public static DB db;
    public static Plugin plugin;
    public static Location spawnLocation;

    @Override
    public void onEnable(){

        this.plugin = this;
        //db = new DB(this.getLogger());
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getPluginManager().registerEvents(new SwordAbilitySystem(), this);

        Bukkit.getLogger().info("[SalvosMC-RPG] Iniciado y cargado");
    }

    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }

}
