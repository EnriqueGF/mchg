package devep;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import devep.Game.GameCore;
import devep.Game.GameSettings;
import devep.Game.GameStatusEnum;
import devep.Hooks.EventHooks;
import devep.Hooks.GameStartedHooks;
import devep.Hooks.InvulnerabilityHooks;
import org.apache.commons.io.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

public class SalvosMCRPG extends JavaPlugin {

    public static DB db;
    public static Plugin plugin;
    public static Location spawnLocation;
    public static WorldBorderApi worldBorderAPI;

    @Override
    public void onEnable(){

        this.plugin = this;
        //db = new DB(this.getLogger());
        GameSettings gameSettings = new GameSettings(2);
        gameSettings.gameStatus = GameStatusEnum.BEFORE_START;

        try {
            if (gameSettings.deleteWorld) {
                getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Eliminando world");
                FileUtils.deleteDirectory(new File("world"));
                getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Mundo eliminado");
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        loadWorldBorderAPI();

        WorldBorder.lastEdgeCloses = Instant.now().getEpochSecond();

        ScheduleTasks scheduleTasks = new ScheduleTasks(plugin);
        GameCore gameCore = new GameCore(gameSettings, scheduleTasks);

        scheduleTasks.checkPlayersOutsideBorders();
        scheduleTasks.lookForGameFinish();

        getServer().getPluginManager().registerEvents(new EventHooks(gameSettings, scheduleTasks, gameCore), plugin);
        getServer().getPluginManager().registerEvents(new InvulnerabilityHooks(gameSettings), plugin);
        getServer().getPluginManager().registerEvents(new GameStartedHooks(gameSettings), plugin);

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
