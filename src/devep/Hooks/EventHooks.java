package devep.Hooks;

import com.github.yannicklamprecht.worldborder.api.PersistentWorldBorderApi;
import devep.Actions.BroadcastRequiredPlayers;
import devep.Actions.GetSpawnLocation;
import devep.Actions.SummonLightning;
import devep.Game.GameCore;
import devep.Game.GameSettings;
import devep.Game.GameStatusEnum;
import devep.Locale.LocaleFactory;
import devep.SalvosMCRPG;
import devep.ScheduleTasks;
import devep.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.world.WorldLoadEvent;

public class EventHooks implements Listener {

    private GameSettings gameSettings;
    private ScheduleTasks scheduleTasks;
    private BroadcastRequiredPlayers broadcastRequiredPlayers;
    private GameCore gameCore;

    public EventHooks(GameSettings gameSettings, ScheduleTasks scheduleTasks, GameCore gameCore) {
        this.gameSettings = gameSettings;
        this.scheduleTasks = scheduleTasks;
        this.gameCore = gameCore;
        this.broadcastRequiredPlayers = new BroadcastRequiredPlayers(this.gameSettings, this.scheduleTasks, this.gameCore);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

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
            }, 20 * 5);
        }

        this.broadcastRequiredPlayers.executeAction(playerJoinEvent);
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {

        this.broadcastRequiredPlayers.executeAction(playerQuitEvent);

    }

    // Invulnerability Hooks

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(gameSettings.gameStatus == GameStatusEnum.INVULNERABILITY) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        if(gameSettings.gameStatus == GameStatusEnum.INVULNERABILITY) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityPickupItemEvent(EntityPickupItemEvent event){
        if(gameSettings.gameStatus == GameStatusEnum.INVULNERABILITY) {
            event.setCancelled(true);
            event.getItem().remove();
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        if(gameSettings.gameStatus == GameStatusEnum.INVULNERABILITY) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event){
        if(gameSettings.gameStatus == GameStatusEnum.INVULNERABILITY) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onServerListPingEvent(ServerListPingEvent event)
    {
        String motd = "";

        switch(gameSettings.gameStatus) {
            case BEFORE_START:
                motd = ChatColor.GREEN + "             Classic HG" + " | " + ChatColor.DARK_PURPLE + "Waiting for players... \n        "+ ChatColor.RED +"   The true Hunger Games Experience";
            break;
            case INVULNERABILITY:
                motd = ChatColor.GREEN + "             Classic HG" + " | " + ChatColor.LIGHT_PURPLE + "Starting game \n        "+ ChatColor.RED +"   The true Hunger Games Experience";
            break;

            case STARTED:
                motd = ChatColor.GREEN + "             Classic HG" + " | " + ChatColor.DARK_AQUA + "Game in progress \n        "+ ChatColor.RED +"   The true Hunger Games Experience";
            break;

            default:
        }
        event.setMotd(motd);
    }

}


