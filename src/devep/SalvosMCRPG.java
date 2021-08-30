package devep;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import devep.Game.GameSettings;
import org.apache.commons.io.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

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

        loadWorldBorderAPI();

        GameSettings gameSettings = new GameSettings(20);
        ScheduleTasks scheduleTasks = new ScheduleTasks(this.plugin);

        getServer().getPluginManager().registerEvents(new EventHooks(gameSettings, scheduleTasks), SalvosMCRPG.plugin);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Carga finalizada");

    }

    private void loadWorldBorderAPI() {
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
    }

    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }

}
