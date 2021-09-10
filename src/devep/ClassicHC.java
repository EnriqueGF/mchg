package devep;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import devep.Game.GameCore;
import devep.Game.GameSettings;
import devep.Game.Gui.KitGui;
import devep.Hooks.EventHooks;
import devep.Hooks.GameStartedHooks;
import devep.Hooks.InvulnerabilityHooks;
import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class ClassicHC extends JavaPlugin implements PluginMessageListener {

    public static Plugin plugin;
    public static Location spawnLocation;
    public static WorldBorderApi worldBorderAPI;
    public static TitleManagerAPI titleManagerAPI;

    @Override
    public void onEnable(){

        this.plugin = this;

        titleManagerAPI = (TitleManagerAPI) Bukkit.getServer().getPluginManager().getPlugin("TitleManager");

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        GameSettings gameSettings = new GameSettings();

        loadExternalPluginAPIS();

        ScheduleTasks scheduleTasks = new ScheduleTasks(plugin);
        GameCore gameCore = new GameCore(gameSettings, scheduleTasks);

        registerEvents(gameSettings, scheduleTasks, gameCore);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Classic-HC] Carga finalizada");

    }

    private void registerEvents(GameSettings gameSettings, ScheduleTasks scheduleTasks, GameCore gameCore) {
        getServer().getPluginManager().registerEvents(new EventHooks(gameSettings, scheduleTasks, gameCore), plugin);
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

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
    }
}