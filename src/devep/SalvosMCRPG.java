package devep;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalvosMCRPG extends JavaPlugin {

    public static DB db;
    public static Plugin plugin;
    public static Location spawnLocation;
    public static WorldBorderApi worldBorderAPI;

    @Override
    public void onEnable(){

        this.plugin = this;
        //db = new DB(this.getLogger());

        try {
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Eliminando world");
            FileUtils.deleteDirectory(new File("world"));
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Mundo eliminado");
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        new BukkitRunnable() {
            @Override
            public void run() {

                RegisteredServiceProvider<WorldBorderApi> worldBorderApiRegisteredServiceProvider = plugin.getServer().getServicesManager().getRegistration(WorldBorderApi.class);

                if (worldBorderApiRegisteredServiceProvider == null) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "[SalvosMC-RPG] No se encuentra la API de WorldBorder");
                    getServer().getPluginManager().disablePlugin(SalvosMCRPG.plugin);
                    return;
                }

                WorldBorderApi worldBorderAPI = worldBorderApiRegisteredServiceProvider.getProvider();
                if (worldBorderAPI != null) {
                    getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] API de WorldBorder Cargada");
                }

                SalvosMCRPG.worldBorderAPI = worldBorderAPI;

            }

        }.runTaskLater(this, 30);

        getServer().getPluginManager().registerEvents(new EventListener(), SalvosMCRPG.plugin);
        getServer().getPluginManager().registerEvents(new SwordAbilitySystem(), SalvosMCRPG.plugin);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Carga finalizada");
        loop();
    }

    void loop() {
        long sec = 1;

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    WorldBorder.sendWorldPacket(player, WorldBorder.borderDistance - 1);
                }
            }
        }, 10L * sec, 10L * sec);
    }

    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }

}
