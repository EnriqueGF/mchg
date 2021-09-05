package devep;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import devep.Game.GameCore;
import devep.Game.GameSettings;
import devep.Game.Gui.KitGui;
import devep.Hooks.EventHooks;
import devep.Hooks.GameStartedHooks;
import devep.Hooks.InvulnerabilityHooks;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SalvosMCRPG extends JavaPlugin {

    public static Plugin plugin;
    public static Location spawnLocation;
    public static WorldBorderApi worldBorderAPI;

    @Override
    public void onEnable(){

        this.plugin = this;

        GameSettings gameSettings = new GameSettings();

        loadExternalPluginAPIS();

        ScheduleTasks scheduleTasks = new ScheduleTasks(plugin);
        GameCore gameCore = new GameCore(gameSettings, scheduleTasks);

        registerEvents(gameSettings, scheduleTasks, gameCore);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Carga finalizada");

    }

    private void registerEvents(GameSettings gameSettings, ScheduleTasks scheduleTasks, GameCore gameCore) {
        getServer().getPluginManager().registerEvents(new EventHooks(gameSettings, scheduleTasks, gameCore, new KitGui()), plugin);
        getServer().getPluginManager().registerEvents(new InvulnerabilityHooks(gameSettings), plugin);
        getServer().getPluginManager().registerEvents(new GameStartedHooks(gameSettings), plugin);
    }

    private void loadExternalPluginAPIS() {
        WorldBorder.loadWorldBorderAPI();
    }

    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }

}
