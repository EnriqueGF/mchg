package devep;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import devep.Command.StartGameCommand;
import devep.Game.GameCore;
import devep.Game.GameSettings;
import devep.Hooks.EventHooks;
import devep.Hooks.GameStartedHooks;
import devep.Hooks.InvulnerabilityHooks;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassicHC extends JavaPlugin implements PluginMessageListener {

  public static Plugin plugin;
  public static Location spawnLocation;
  // public static TitleManagerAPI titleManagerAPI;
  public static WorldBorder worldBorder;
  public static Scoreboard scoreboard;
  public static Team team;

  @Override
  public void onEnable() {

    this.plugin = this;

    scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    team = scoreboard.registerNewTeam("default-team");
    team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

    // titleManagerAPI =
    // (TitleManagerAPI) Bukkit.getServer().getPluginManager().getPlugin("TitleManager");

    this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

    GameSettings gameSettings = new GameSettings();

    initWorldBorder(gameSettings);

    this.getCommand("forceStartGame").setExecutor(new StartGameCommand(gameSettings));

    ScheduleTasks scheduleTasks = new ScheduleTasks(plugin);
    GameCore gameCore = new GameCore(gameSettings, scheduleTasks);

    registerEvents(gameSettings, scheduleTasks, gameCore);

    if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
      new PlaceholdersExpansion().register();
    }

    log.info(ChatColor.GREEN + "Carga finalizada");
  }

  private void initWorldBorder(GameSettings gameSettings) {

    ClassicHC.worldBorder = ClassicHC.plugin.getServer().getWorld("world").getWorldBorder();
    ClassicHC.worldBorder.setSize(gameSettings.worldBorderSize);
    ClassicHC.worldBorder.setDamageAmount(1);
    ClassicHC.worldBorder.setWarningDistance(5);

    World world = Bukkit.getServer().getWorld("world");

    Location location = world.getHighestBlockAt(world.getSpawnLocation()).getLocation();
    location.setY(location.getY() + 2);
    ClassicHC.spawnLocation = location;

  }

  private void registerEvents(
      GameSettings gameSettings, ScheduleTasks scheduleTasks, GameCore gameCore) {
    getServer()
        .getPluginManager()
        .registerEvents(new EventHooks(gameSettings, scheduleTasks, gameCore), plugin);
    getServer().getPluginManager().registerEvents(new InvulnerabilityHooks(gameSettings), plugin);
    getServer().getPluginManager().registerEvents(new GameStartedHooks(gameSettings), plugin);
  }

  @Override
  public void onDisable() {
    // Fired when the server stops and disables all plugins
  }

  @Override
  public void onPluginMessageReceived(
      @NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
    if (!channel.equals("BungeeCord")) {
      return;
    }
  }
}
