package devep;

import com.github.yannicklamprecht.worldborder.api.PersistentWorldBorderApi;
import devep.Actions.BroadcastRequiredPlayers;
import devep.Actions.GetSpawnLocation;
import devep.Actions.SummonLightning;
import devep.Game.GameSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldLoadEvent;

public class EventHooks implements Listener {

    private GameSettings gameSettings;
    private ScheduleTasks scheduleTasks;
    private BroadcastRequiredPlayers broadcastRequiredPlayers;

    public EventHooks(GameSettings gameSettings, ScheduleTasks scheduleTasks) {
        this.gameSettings = gameSettings;
        this.scheduleTasks = scheduleTasks;

        this.broadcastRequiredPlayers = new BroadcastRequiredPlayers(this.gameSettings, this.scheduleTasks);

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event, ScheduleTasks scheduleTasks) {

        SummonLightning sL = new SummonLightning();
        sL.executeAction(event);

    }

    @EventHandler
    public void WorldLoadEvent(WorldLoadEvent event) {

        GetSpawnLocation gSL = new GetSpawnLocation();
        gSL.executeAction(event);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {

        if (SalvosMCRPG.worldBorderAPI instanceof PersistentWorldBorderApi) {
            Bukkit.getScheduler().runTaskLater(SalvosMCRPG.plugin, () -> {
                WorldBorder.sendWorldPacket(playerJoinEvent.getPlayer(), gameSettings.getWorldBorderRadius());
            }, 20 * 3);
        }

        this.broadcastRequiredPlayers.executeAction(playerJoinEvent);
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {

        this.broadcastRequiredPlayers.executeAction(playerQuitEvent);

    }

}


